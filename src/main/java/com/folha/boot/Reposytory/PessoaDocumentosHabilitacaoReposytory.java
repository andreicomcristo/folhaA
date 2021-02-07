package com.folha.boot.Reposytory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.folha.boot.domain.PessoaDocumentosHabilitacao;

@Repository
public interface PessoaDocumentosHabilitacaoReposytory extends JpaRepository<PessoaDocumentosHabilitacao, Long> {

}