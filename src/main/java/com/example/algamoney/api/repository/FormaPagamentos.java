package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.FormaPagamento;
import com.example.algamoney.api.repository.formaPagamentos.FormaPagamentosQuery;

public interface FormaPagamentos extends JpaRepository<FormaPagamento, Long>, FormaPagamentosQuery {

}
