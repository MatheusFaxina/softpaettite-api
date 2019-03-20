package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Permissao;
import com.example.algamoney.api.repository.permissoes.PermissoesQuery;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
