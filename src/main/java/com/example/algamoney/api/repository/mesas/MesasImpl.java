package com.example.algamoney.api.repository.mesas;

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

import com.example.algamoney.api.model.Mesa;
import com.example.algamoney.api.model.Mesa_;
import com.example.algamoney.api.repository.filter.MesaFilter;

public class MesasImpl implements MesasQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Mesa> filtrar(MesaFilter mesaFilter, Pageable pageAble) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Mesa> criteria = builder.createQuery(Mesa.class);
		Root<Mesa> root = criteria.from(Mesa.class);

		// criar as restrições
		Predicate[] predicates = criarRestricoes(mesaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Mesa> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacaoNaQuery(query, pageAble);

		return new PageImpl<>(query.getResultList(), pageAble, total(mesaFilter));
	}

	private Long total(MesaFilter mesaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Mesa> root = criteria.from(Mesa.class);

		Predicate[] predicates = criarRestricoes(mesaFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacaoNaQuery(TypedQuery<Mesa> query, Pageable pageAble) {
		int paginaAtual = pageAble.getPageNumber();
		int totalRegistrosPorPagina = pageAble.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(MesaFilter mesaFilter, CriteriaBuilder builder, Root<Mesa> root) {
		List<Predicate> predicates = new ArrayList<>();

		// WHERE descricao LIKE '%jogos%'
		if (!StringUtils.isEmpty(mesaFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Mesa_.nome)),
					"%" + mesaFilter.getNome().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
