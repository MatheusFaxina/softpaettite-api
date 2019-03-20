package com.example.algamoney.api.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.AdicionalProduto;
import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.model.ContasReceber;
import com.example.algamoney.api.model.ItemVenda;
import com.example.algamoney.api.model.ObservacaoProduto;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.model.Produto;
import com.example.algamoney.api.model.RetirarProduto;
import com.example.algamoney.api.model.StatusPedido;
import com.example.algamoney.api.model.Venda;
import com.example.algamoney.api.repository.ContasRecebers;
import com.example.algamoney.api.repository.Produtos;
import com.example.algamoney.api.repository.Vendas;

@Service
public class VendaService {
	
	@Autowired
	private Vendas vendas;
	
	@Autowired
	private ContasRecebers contasRecebers;
	
	@Autowired
	private Produtos produtos;
	
	@Autowired
	private EntityManager manager;
	
	public Venda adicionar(Venda venda) {
		Venda vendaNovo = venda;
		System.out.println(">>>>>>>" + venda);
		vendaNovo.setCadastro(LocalDate.now());
		vendaNovo.getItens().forEach(i -> {
			i.setVenda(vendaNovo);
			i.setProduto(produtos.findOne(i.getProduto().getCodigo()));
		});
		
		/*BigDecimal totalItens = venda.getItens().stream()
				.map(i -> i.getProduto().getValor().multiply(new BigDecimal(i.getQuantidade())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		venda.setTotal(totalItens);*/
		
		for (ItemVenda item: vendaNovo.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
		
		vendas.save(vendaNovo);
		
		gerarContaReceber(vendaNovo);
		
		return vendaNovo;
		
	}

	private void gerarContaReceber(Venda venda) {
		ContasReceber contasReceber = new ContasReceber();
		contasReceber.setDataCriacao(venda.getCadastro());
		contasReceber.setDataVencimento(venda.getCadastro());
		contasReceber.setDataPagamento(venda.getCadastro());
		contasReceber.setValorAReceber(venda.getValorTotal());
		contasReceber.setValorDesconto(venda.getValorTotal());
		contasReceber.setValorJuros(venda.getValorTotal());
		contasReceber.setValorPago(venda.getValorTotal());
		contasReceber.setVenda(venda);
		System.out.println(contasReceber.getVenda().getCodigo());
		contasRecebers.save(contasReceber);
	}
	
	public void cancelarVenda(Long codigo) {
		Venda vendaSalva = buscarVendaPeloCodigo(codigo);
		vendaSalva.setStatus(StatusPedido.CANCELADO);
		
		for (ItemVenda item: vendaSalva.getItens()) {
			item.getProduto().aumentarEstoque(item.getQuantidade());
		}
		
		vendas.save(vendaSalva);
	}
	
	/*public Venda atualizar(Long codigo, Venda venda) {
		Venda vendaSalva = buscarPessoaPeloCodigo(codigo);
		
		vendaSalva.getAdicionai().clear();
		vendaSalva.getContatos().addAll(pessoa.getContatos());
		vendaSalva.getContatos().forEach(c -> c.setPessoa(pessoaSalva));

		BeanUtils.copyProperties(venda, vendaSalva, "codigo", "adicionais");
		BeanUtils.copyProperties(venda, vendaSalva, "codigo", "observacoes");
		BeanUtils.copyProperties(venda, vendaSalva, "codigo", "retirars");
		return vendas.save(vendaSalva);
	}*/
	
	public Venda buscarVendaPeloCodigo(Long codigo) {
		Venda vendaSalva = vendas.findOne(codigo);
		if (vendaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return vendaSalva;
	}
	
	public AdicionalProduto adicionalPorCodigo(Long codigo) {
		return this.manager.createQuery("from AdicionalProduto where codigo = :codigo", AdicionalProduto.class).setParameter("codigo", codigo)
				.getSingleResult();
	}
	
	public ObservacaoProduto observacaoPorCodigo(Long codigo) {
		return this.manager.createQuery("from ObservacaoProduto where codigo = :codigo", ObservacaoProduto.class).setParameter("codigo", codigo)
				.getSingleResult();
	}
	
	public RetirarProduto retirarPorCodigo(Long codigo) {
		return this.manager.createQuery("from RetirarProduto where codigo = :codigo", RetirarProduto.class).setParameter("codigo", codigo)
				.getSingleResult();
	}

	public List<Produto> produtosPorCategoria(Categoria categoria) {
		return this.manager.createQuery("from Produto where categoria.codigo = :codigo", Produto.class).setParameter("codigo", categoria.getCodigo())
				.getResultList();
	}
	
	public List<AdicionalProduto> adicionaisPorProduto(Produto produto) {
		return this.manager.createQuery("from AdicionalProduto where produto.codigo = :codigo", AdicionalProduto.class).setParameter("codigo", produto.getCodigo())
				.getResultList();
	}
	
	public List<ObservacaoProduto> observacoesPorProduto(Produto produto) {
		return this.manager.createQuery("from ObservacaoProduto where produto.codigo = :codigo", ObservacaoProduto.class).setParameter("codigo", produto.getCodigo())
				.getResultList();
	}
	
	public List<RetirarProduto> retirarsPorProduto(Produto produto) {
		return this.manager.createQuery("from RetirarProduto where produto.codigo = :codigo", RetirarProduto.class).setParameter("codigo", produto.getCodigo())
				.getResultList();
	}
}
