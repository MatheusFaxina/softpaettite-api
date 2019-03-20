package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Parcela;
import com.example.algamoney.api.repository.Parcelas;

@Service
public class ParcelaService {

	@Autowired
	private Parcelas parcelas;

	public Parcela salvar(Parcela parcela) {
		return parcelas.save(parcela);
	}

	public Parcela atualizar(Long codigo, Parcela parcela) {
		Parcela parcelaSalvo = buscarParcelaExistente(codigo);

		BeanUtils.copyProperties(parcela, parcelaSalvo, "codigo");

		return parcelas.save(parcelaSalvo);
	}

	private Parcela buscarParcelaExistente(Long codigo) {
		Parcela parcelaSalvo = parcelas.findOne(codigo);
		if (parcelaSalvo == null) {
			throw new IllegalArgumentException();
		}
		return parcelaSalvo;
	}

}
