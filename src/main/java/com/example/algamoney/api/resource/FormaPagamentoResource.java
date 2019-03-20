package com.example.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.FormaPagamento;
import com.example.algamoney.api.repository.FormaPagamentos;
import com.example.algamoney.api.repository.filter.FormaPagamentoFilter;
import com.example.algamoney.api.service.FormaPagamentoService;

@RestController
@RequestMapping("/formaPagamentos")
public class FormaPagamentoResource {

	@Autowired
	private FormaPagamentos formaPagamentos;

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping("/listarTodos")
	public List<FormaPagamento> listar() {
		return formaPagamentos.findAll();
	}
	
	@GetMapping
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PARCELA') and #oauth2.hasScope('read')")
	public Page<FormaPagamento> FormaPagamento(FormaPagamentoFilter formaPagamentoFilter, Pageable pageAble) {
		return formaPagamentos.filtrar(formaPagamentoFilter, pageAble);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_FORMA_PAGAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<FormaPagamento> salvar(@Valid @RequestBody FormaPagamento formaPagamento,
			HttpServletResponse response) {
		FormaPagamento formaPagamentoSalva = formaPagamentos.save(formaPagamento);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, formaPagamento.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_FORMA_PAGAMENTO') and #oauth2.hasScope('read')")
	public void remover(@PathVariable Long codigo) {
		formaPagamentos.delete(codigo);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_FORMA_PAGAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<FormaPagamento> buscarPeloCodigo(@PathVariable Long codigo) {
		FormaPagamento formaPagamento = formaPagamentos.findOne(codigo);
		return formaPagamento != null ? ResponseEntity.ok(formaPagamento) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_FORMA_PAGAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long codigo,
			@Valid @RequestBody FormaPagamento formaPagamento) {
		try {
			FormaPagamento formaPagamentoSalvo = formaPagamentoService.atualizar(codigo, formaPagamento);
			return ResponseEntity.ok(formaPagamentoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
