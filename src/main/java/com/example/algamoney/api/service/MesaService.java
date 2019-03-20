package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Mesa;
import com.example.algamoney.api.repository.Mesas;

@Service
public class MesaService {

	@Autowired
	private Mesas mesas;

	public Mesa salvar(Mesa mesa) {
		return mesas.save(mesa);
	}

	public Mesa atualizar(Long codigo, Mesa mesa) {
		Mesa mesaSalvo = buscarMesaExistente(codigo);

		BeanUtils.copyProperties(mesa, mesaSalvo, "codigo");

		return mesas.save(mesaSalvo);
	}

	private Mesa buscarMesaExistente(Long codigo) {
		Mesa mesaSalvo = mesas.findOne(codigo);
		if (mesaSalvo == null) {
			throw new IllegalArgumentException();
		}
		return mesaSalvo;
	}

}
