package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Permissao;
import com.example.algamoney.api.repository.Permissoes;

@Service
public class PermissaoService {

	@Autowired
	private Permissoes permissoes;

	public Permissao salvar(Permissao permissao) {
		return permissoes.save(permissao);
	}

	public Permissao atualizar(Long codigo, Permissao permissao) {
		Permissao permissaoSalvo = buscarPermissaoExistente(codigo);

		BeanUtils.copyProperties(permissao, permissaoSalvo, "codigo");

		return permissoes.save(permissaoSalvo);
	}

	private Permissao buscarPermissaoExistente(Long codigo) {
		Permissao permissaoSalvo = permissoes.findOne(codigo);
		if (permissaoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return permissaoSalvo;
	}

}
