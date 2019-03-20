package com.example.algamoney.api.repository.mesas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.model.Mesa;
import com.example.algamoney.api.repository.filter.MesaFilter;

public interface MesasQuery {

	public Page<Mesa> filtrar(MesaFilter mesaFilter, Pageable pageAble);

}
