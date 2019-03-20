package com.example.algamoney.api.model;

public enum StatusContasReceber {

	CANCELADO("Cancelado"), RECEBIDO("Recebido"), A_RECEBER("A Receber"), RECEBIDO_PARCIALMENTE(
			"Recebido Parcialmente");

	private String descricao;

	StatusContasReceber(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
