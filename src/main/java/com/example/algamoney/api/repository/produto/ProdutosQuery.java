package com.example.algamoney.api.repository.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.model.Produto;
import com.example.algamoney.api.repository.filter.ProdutoFilter;

public interface ProdutosQuery {

	public abstract Page<Produto> filtrar(ProdutoFilter lancamentoFilter, Pageable pageAble);

}
