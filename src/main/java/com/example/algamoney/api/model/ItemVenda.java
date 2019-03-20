package com.example.algamoney.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "item_venda")
public class ItemVenda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne
	@JoinColumn(name = "codigo_venda")
	private Venda venda;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_produto")
	private Produto produto;

	@NotNull
	private Integer quantidade;

	@Column(name = "valor_unitario")
	private BigDecimal valorUnitario;

	@JsonIgnoreProperties("itemVenda")
	@Valid
	@NotEmpty
	@OneToMany(mappedBy = "itemVenda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdicionaisItemVenda> adicionais;

	@JsonIgnoreProperties("itemVenda")
	@Valid
	@NotEmpty
	@OneToMany(mappedBy = "itemVenda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ObservacoesItemVenda> observacoes;

	@JsonIgnoreProperties("itemVenda")
	@Valid
	@NotEmpty
	@OneToMany(mappedBy = "itemVenda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RetirarsItemVenda> retirars;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public List<AdicionaisItemVenda> getAdicionais() {
		return adicionais;
	}

	public void setAdicionais(List<AdicionaisItemVenda> adicionais) {
		this.adicionais = adicionais;
	}

	public List<ObservacoesItemVenda> getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(List<ObservacoesItemVenda> observacoes) {
		this.observacoes = observacoes;
	}

	public List<RetirarsItemVenda> getRetirars() {
		return retirars;
	}

	public void setRetirars(List<RetirarsItemVenda> retirars) {
		this.retirars = retirars;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVenda other = (ItemVenda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
