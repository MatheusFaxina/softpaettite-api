package com.example.algamoney.api.repository.formaPagamentos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.model.FormaPagamento;
import com.example.algamoney.api.repository.filter.FormaPagamentoFilter;

public interface FormaPagamentosQuery {

	public Page<FormaPagamento> filtrar(FormaPagamentoFilter formaPagamentoFilter, Pageable pageAble);

}
