package com.example.algamoney.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.algamoney.api.model.Parcela;
import com.example.algamoney.api.repository.Parcelas;
import com.example.algamoney.api.repository.filter.ParcelaFilter;
import com.example.algamoney.api.service.ParcelaService;

@RestController
@RequestMapping("/parcelas")
public class ParcelaResource {

	@Autowired
	private Parcelas parcelas;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private ParcelaService parcelaService;

	@GetMapping
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PARCELA') and #oauth2.hasScope('read')")
	public Page<Parcela> Parcela(ParcelaFilter parcelaFilter, Pageable pageAble) {
		return parcelas.filtrar(parcelaFilter, pageAble);
	}

	@PostMapping
	//@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PARCELA') and #oauth2.hasScope('write')")
	public ResponseEntity<Parcela> salvar(@Valid @RequestBody Parcela parcela, HttpServletResponse response) {
		Parcela parcelaSalva = parcelas.save(parcela);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, parcela.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(parcelaSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	//@PreAuthorize("hasAuthority('ROLE_REMOVER_PARCELA') and #oauth2.hasScope('read')")
	public void remover(@PathVariable Long codigo) {
		parcelas.delete(codigo);
	}

	@GetMapping("/{codigo}")
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PARCELA') and #oauth2.hasScope('read')")
	public ResponseEntity<Parcela> buscarPeloCodigo(@PathVariable Long codigo) {
		Parcela parcela = parcelas.findOne(codigo);
		return parcela != null ? ResponseEntity.ok(parcela) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{codigo}")
	//@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PARCELA') and #oauth2.hasScope('read')")
	public ResponseEntity<Parcela> atualizar(@PathVariable Long codigo, @Valid @RequestBody Parcela parcela) {
		try {
			Parcela parcelaSalvo = parcelaService.atualizar(codigo, parcela);
			return ResponseEntity.ok(parcelaSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
