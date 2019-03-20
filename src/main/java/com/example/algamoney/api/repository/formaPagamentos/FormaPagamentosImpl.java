package com.example.algamoney.api.repository.formaPagamentos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.algamoney.api.model.FormaPagamento;
import com.example.algamoney.api.model.FormaPagamento_;
import com.example.algamoney.api.repository.filter.FormaPagamentoFilter;

public class FormaPagamentosImpl implements FormaPagamentosQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<FormaPagamento> filtrar(FormaPagamentoFilter formaPagamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<FormaPagamento> criteria = builder.createQuery(FormaPagamento.class);
		Root<FormaPagamento> root = criteria.from(FormaPagamento.class);
		
		Predicate[] predicates = criarRestricoes(formaPagamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<FormaPagamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(formaPagamentoFilter));
	}

	private Predicate[] criarRestricoes(FormaPagamentoFilter formaPagamentoFilter, CriteriaBuilder builder,
			Root<FormaPagamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(formaPagamentoFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(FormaPagamento_.nome)), "%" + formaPagamentoFilter.getNome().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(FormaPagamentoFilter formaPagamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<FormaPagamento> root = criteria.from(FormaPagamento.class);
		
		Predicate[] predicates = criarRestricoes(formaPagamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
