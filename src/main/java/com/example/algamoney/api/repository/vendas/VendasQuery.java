package com.example.algamoney.api.repository.vendas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.model.Venda;
import com.example.algamoney.api.repository.filter.VendaFilter;

public interface VendasQuery {

	public Page<Venda> filtrar(VendaFilter vendaFilter, Pageable pageAble);

}
