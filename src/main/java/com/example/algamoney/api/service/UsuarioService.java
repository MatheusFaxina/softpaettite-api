package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Usuario;
import com.example.algamoney.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarios;

	public Usuario salvar(Usuario usuario) {
		return usuarios.save(usuario);
	}

	public Usuario atualizar(Long codigo, Usuario usuario) {
		Usuario usuarioSalvo = buscarUsuarioExistente(codigo);

		BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo");

		return usuarios.save(usuarioSalvo);
	}

	private Usuario buscarUsuarioExistente(Long codigo) {
		Usuario usuarioSalvo = usuarios.findOne(codigo);
		if (usuarioSalvo == null) {
			throw new IllegalArgumentException();
		}
		return usuarioSalvo;
	}

}
