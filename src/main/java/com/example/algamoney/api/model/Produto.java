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
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private String nome;

	private Integer estoque;

	@Column(name = "estoque_minimo")
	private Integer estoqueMinimo;

	@ManyToOne
	@JoinColumn(name = "codigo_categoria")
	private Categoria categoria;

	@Column(name = "valor_custo")
	private BigDecimal valorCusto;

	private BigDecimal valor;

	@JsonIgnoreProperties("produto")
	@Valid
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL,	orphanRemoval = true)
	private List<ObservacaoProduto> observacoes;

	@JsonIgnoreProperties("produto")
	@Valid
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdicionalProduto> adicionais;

	@JsonIgnoreProperties("produto")
	@Valid
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RetirarProduto> retirars;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}

	public List<ObservacaoProduto> getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(List<ObservacaoProduto> observacoes) {
		this.observacoes = observacoes;
	}

	public List<AdicionalProduto> getAdicionais() {
		return adicionais;
	}

	public void setAdicionais(List<AdicionalProduto> adicionais) {
		this.adicionais = adicionais;
	}

	public List<RetirarProduto> getRetirars() {
		return retirars;
	}

	public void setRetirars(List<RetirarProduto> retirars) {
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Transactional
	public void baixarEstoque(Integer quantidade) {
		Integer novaQuantidade = this.getEstoque() - quantidade;
		System.out.println(novaQuantidade);

		this.setEstoque(novaQuantidade);
		System.out.println(this.getEstoque());
	}

	public void aumentarEstoque(Integer quantidade) {
		Integer novaQuantidade = this.getEstoque() + quantidade;

		this.setEstoque(novaQuantidade);

	}

}
