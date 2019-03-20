package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Venda;
import com.example.algamoney.api.repository.vendas.VendasQuery;

public interface Vendas extends JpaRepository<Venda, Long>, VendasQuery {

}
