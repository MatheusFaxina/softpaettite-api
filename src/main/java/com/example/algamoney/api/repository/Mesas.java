package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Mesa;
import com.example.algamoney.api.repository.mesas.MesasQuery;

public interface Mesas extends JpaRepository<Mesa, Long>, MesasQuery {

}
