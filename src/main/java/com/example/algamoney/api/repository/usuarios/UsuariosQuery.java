package com.example.algamoney.api.repository.usuarios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.model.Usuario;
import com.example.algamoney.api.repository.filter.UsuarioFilter;

public interface UsuariosQuery {

	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageAble);

}
