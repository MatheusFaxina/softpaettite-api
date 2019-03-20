package com.example.algamoney.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.model.AdicionalProduto;
import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.model.ObservacaoProduto;
import com.example.algamoney.api.model.Produto;
import com.example.algamoney.api.model.RetirarProduto;
import com.example.algamoney.api.model.Venda;
import com.example.algamoney.api.repository.Vendas;
import com.example.algamoney.api.repository.filter.VendaFilter;
import com.example.algamoney.api.service.VendaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/vendas")
public class VendasResource {
	
	@Autowired
	private Vendas vendas;
	
	@Autowired
	private VendaService vendaService;
	
	@GetMapping("/adicional/{codigo}")
	public AdicionalProduto adicionalPorCodigo(@PathVariable Long codigo) {
		return vendaService.adicionalPorCodigo(codigo);
	}
	
	@GetMapping("/retirar/{codigo}")
	public RetirarProduto retirarPorCodigo(@PathVariable Long codigo) {
		return vendaService.retirarPorCodigo(codigo);
	}
	
	@GetMapping("/observacao/{codigo}")
	public ObservacaoProduto observacaoPorCodigo(@PathVariable Long codigo) {
		return vendaService.observacaoPorCodigo(codigo);
	}

	@GetMapping("/produtoPorCategoria/{codigo}")
	public List<Produto> produtosPorCategoria(Categoria categoria) {
		return vendaService.produtosPorCategoria(categoria);
	}
	
	@GetMapping("/adicionaisPorProduto/{codigo}")
	public List<AdicionalProduto> adicionalPorProduto(Produto produto) {
		System.out.println("Adicional: " + produto.getCodigo());
		return vendaService.adicionaisPorProduto(produto);
	}
	
	@GetMapping("/observacoesPorProduto/{codigo}")
	public List<ObservacaoProduto> observacaoPorProduto(Produto produto) {
		System.out.println("Observação: " + produto.getCodigo());
		return vendaService.observacoesPorProduto(produto);
	}
	
	@GetMapping("/retirarsPorProduto/{codigo}")
	public List<RetirarProduto> retirarPorProduto(Produto produto) {
		System.out.println("Retirar: " + produto.getCodigo());
		return vendaService.retirarsPorProduto(produto);
	}
	
	@GetMapping
	public Page<Venda> Venda(VendaFilter vendaFilter, Pageable pageAble) {
		return vendas.filtrar(vendaFilter, pageAble);
	}
	
	@PostMapping
	public Venda adicionar(@RequestBody @Valid Venda venda) {
		return vendaService.adicionar(venda);
	}
	
	/*@PutMapping("/{codigo}")
	public ResponseEntity<Venda> atualizar(@PathVariable Long codigo, @Valid @RequestBody Venda venda) {
		Venda vendaSalva = vendaService.atualizar(codigo, venda);
		return ResponseEntity.ok(vendaSalva);
	}*/
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Venda> buscarPeloCodigo(@PathVariable Long codigo) {
		Venda venda = vendas.findOne(codigo);
		return venda != null ? ResponseEntity.ok(venda) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}/cancelar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelarVenda(@PathVariable Long codigo) {
		vendaService.cancelarVenda(codigo);
	}
}
