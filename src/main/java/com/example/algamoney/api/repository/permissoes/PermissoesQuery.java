package com.example.algamoney.api.repository.permissoes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.model.Permissao;
import com.example.algamoney.api.repository.filter.PermissaoFilter;

public interface PermissoesQuery {

	public Page<Permissao> filtrar(PermissaoFilter permissaoFilter, Pageable pageAble);

}
