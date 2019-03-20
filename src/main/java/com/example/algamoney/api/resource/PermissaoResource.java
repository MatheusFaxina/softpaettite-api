package com.example.algamoney.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.model.Permissao;
import com.example.algamoney.api.repository.PermissaoRepository;
import com.example.algamoney.api.repository.filter.PermissaoFilter;

@RestController
@RequestMapping("/permissoes")
public class PermissaoResource {

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PERMISSAO') and #oauth2.hasScope('read')")
	public List<Permissao> listar() {
		return permissaoRepository.findAll();
	}
	
	/*@GetMapping("/filtrar")
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PARCELA') and #oauth2.hasScope('read')")
	public Page<Permissao> Permissao(PermissaoFilter permissaoFilter, Pageable pageAble) {
		return permissaoRepository.filtrar(permissaoFilter, pageAble);
	}*/

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PERMISSAO') and #oauth2.hasScope('read')")
	public ResponseEntity<Permissao> buscarPeloCodigo(@PathVariable Long codigo) {
		Permissao permissao = permissaoRepository.findOne(codigo);
		return permissao != null ? ResponseEntity.ok(permissao) : ResponseEntity.notFound().build();
	}

}
