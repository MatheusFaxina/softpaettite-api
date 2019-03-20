package com.example.algamoney.api.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Produto;
import com.example.algamoney.api.repository.Produtos;

@Service
public class ProdutoService {

	@Autowired
	private Produtos produtos;

	@Autowired
	private EntityManager manager;
	
	public Produto salvar(Produto produto) {
		produto.getAdicionais().forEach(c -> c.setProduto(produto));
		produto.getObservacoes().forEach(c -> c.setProduto(produto));
		produto.getRetirars().forEach(c -> c.setProduto(produto));
		return produtos.save(produto);
	}

	public List<Produto> produtosPorCategorias(Long codigo) {
		return this.manager.createQuery("from Produto where (codigo_categoria) like :codigo_categoria", Produto.class)
				.setParameter("codigo_categoria", codigo + "%").getResultList();
	}

	public Produto atualizar(Long codigo, Produto produto) {
		Produto produtoSalvo = buscarProdutoPeloCodigo(codigo);
		
		produtoSalvo.getAdicionais().clear();
		produtoSalvo.getAdicionais().addAll(produto.getAdicionais());
		produtoSalvo.getAdicionais().forEach(c -> c.setProduto(produtoSalvo));
		System.out.println("Adicionais: " + produtoSalvo.getAdicionais());
		
		produtoSalvo.getObservacoes().clear();
		produtoSalvo.getObservacoes().addAll(produto.getObservacoes());
		produtoSalvo.getObservacoes().forEach(c -> c.setProduto(produtoSalvo));
		System.out.println("Observacoes: " + produtoSalvo.getObservacoes());
		
		produtoSalvo.getRetirars().clear();
		produtoSalvo.getRetirars().addAll(produto.getRetirars());
		produtoSalvo.getRetirars().forEach(c -> c.setProduto(produtoSalvo));
		System.out.println("Retirars: " + produtoSalvo.getRetirars());

		BeanUtils.copyProperties(produto, produtoSalvo, "codigo", "adicionais");
		BeanUtils.copyProperties(produto, produtoSalvo, "codigo", "observacoes");
		BeanUtils.copyProperties(produto, produtoSalvo, "codigo", "retirars");

		return produtos.save(produtoSalvo);
	}

	public void atualizarPropiedadeAtivo(Long codigo, Boolean ativo) {
		Produto produtoSalvo = buscarProdutoPeloCodigo(codigo);

//		produtoSalvo.setAtivo(ativo);

		produtos.save(produtoSalvo);
	}

	public Produto buscarProdutoPeloCodigo(Long codigo) {
		Produto produtoSalvo = produtos.findOne(codigo);

		if (produtoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return produtoSalvo;
	}

}
