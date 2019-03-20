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
import com.example.algamoney.api.model.Mesa;
import com.example.algamoney.api.repository.Mesas;
import com.example.algamoney.api.repository.filter.MesaFilter;
import com.example.algamoney.api.service.MesaService;

@RestController
@RequestMapping("/mesas")
public class MesaResource {

	@Autowired
	private Mesas mesas;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private MesaService mesaService;
	
	@GetMapping("/listarTodasMesas")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MESA') and #oauth2.hasScope('read')")
	public List<Mesa> listar() {
		return mesas.findAll();
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MESA') and #oauth2.hasScope('read')")
	public Page<Mesa> Mesa(MesaFilter mesaFilter, Pageable pageAble) {
		return mesas.filtrar(mesaFilter, pageAble);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MESA') and #oauth2.hasScope('write')")
	public ResponseEntity<Mesa> salvar(@Valid @RequestBody Mesa mesa, HttpServletResponse response) {
		Mesa mesaSalva = mesas.save(mesa);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, mesa.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(mesaSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_MESA') and #oauth2.hasScope('read')")
	public void remover(@PathVariable Long codigo) {
		mesas.delete(codigo);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MESA') and #oauth2.hasScope('read')")
	public ResponseEntity<Mesa> buscarPeloCodigo(@PathVariable Long codigo) {
		Mesa mesa = mesas.findOne(codigo);
		return mesa != null ? ResponseEntity.ok(mesa) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MESA') and #oauth2.hasScope('read')")
	public ResponseEntity<Mesa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Mesa mesa) {
		try {
			Mesa mesaSalvo = mesaService.atualizar(codigo, mesa);
			return ResponseEntity.ok(mesaSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
