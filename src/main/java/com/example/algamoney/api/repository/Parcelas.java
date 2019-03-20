package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Parcela;
import com.example.algamoney.api.repository.parcelas.ParcelasQuery;

public interface Parcelas extends JpaRepository<Parcela, Long>, ParcelasQuery {

}
