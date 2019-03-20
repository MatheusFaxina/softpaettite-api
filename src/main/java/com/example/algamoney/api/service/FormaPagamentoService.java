package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.FormaPagamento;
import com.example.algamoney.api.repository.FormaPagamentos;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentos formaPagamentos;

	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentos.save(formaPagamento);
	}

	public FormaPagamento atualizar(Long codigo, FormaPagamento formaPagamento) {
		FormaPagamento formaPagamentoSalvo = buscarFormaPagamentoExistente(codigo);
		
		formaPagamentoSalvo.getParcelas().clear();
		formaPagamentoSalvo.getParcelas().addAll(formaPagamento.getParcelas());
		formaPagamentoSalvo.getParcelas().forEach(c -> c.setFormaPagamento(formaPagamentoSalvo));

		BeanUtils.copyProperties(formaPagamento, formaPagamentoSalvo, "codigo");
		BeanUtils.copyProperties(formaPagamento, formaPagamentoSalvo, "codigo", "parcelas");

		return formaPagamentos.save(formaPagamentoSalvo);
	}

	private FormaPagamento buscarFormaPagamentoExistente(Long codigo) {
		FormaPagamento formaPagamentoSalvo = formaPagamentos.findOne(codigo);
		if (formaPagamentoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return formaPagamentoSalvo;
	}

}
