package com.example.algamoney.api.repository.parcelas;

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

import com.example.algamoney.api.model.Parcela;
import com.example.algamoney.api.model.Parcela_;
import com.example.algamoney.api.repository.filter.ParcelaFilter;

public class ParcelasImpl implements ParcelasQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Parcela> filtrar(ParcelaFilter parcelaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Parcela> criteria = builder.createQuery(Parcela.class);
		Root<Parcela> root = criteria.from(Parcela.class);
		
		Predicate[] predicates = criarRestricoes(parcelaFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Parcela> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(parcelaFilter));
	}

	private Predicate[] criarRestricoes(ParcelaFilter parcelaFilter, CriteriaBuilder builder,
			Root<Parcela> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(parcelaFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Parcela_.nome)), "%" + parcelaFilter.getNome().toLowerCase() + "%"));
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
	
	private Long total(ParcelaFilter parcelaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Parcela> root = criteria.from(Parcela.class);
		
		Predicate[] predicates = criarRestricoes(parcelaFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
