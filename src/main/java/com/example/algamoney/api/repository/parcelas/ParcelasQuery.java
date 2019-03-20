package com.example.algamoney.api.repository.parcelas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.model.Parcela;
import com.example.algamoney.api.repository.filter.ParcelaFilter;

public interface ParcelasQuery {

	public Page<Parcela> filtrar(ParcelaFilter parcelaFilter, Pageable pageAble);

}
