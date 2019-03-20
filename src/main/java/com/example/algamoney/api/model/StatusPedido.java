package com.example.algamoney.api.model;

public enum StatusPedido {
	
	EM_ABERTO("Venda em aberto"), 
	MANDOU_COZINHA("Venda mandado para a cozinha"), 
	PRONTO_ENTREGA_MESA("Venda pronto para entregar"),
	CANCELADO("Cancelado");
	
	private String descricao;
	
	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}


}
