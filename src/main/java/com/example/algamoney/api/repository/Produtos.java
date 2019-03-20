package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Produto;
import com.example.algamoney.api.repository.produto.ProdutosQuery;


public interface Produtos extends JpaRepository<Produto, Long>, ProdutosQuery {

}
