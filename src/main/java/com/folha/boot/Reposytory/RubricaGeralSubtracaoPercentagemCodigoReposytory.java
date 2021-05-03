package com.folha.boot.Reposytory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.folha.boot.domain.AtividadeEscala;
import com.folha.boot.domain.RubricaComplementoConstitucional;
import com.folha.boot.domain.RubricaComplementoConstitucionalCodigo;
import com.folha.boot.domain.RubricaGeralSomaCodigo;
import com.folha.boot.domain.RubricaGeralSomaPercentagemCodigo;
import com.folha.boot.domain.RubricaGeralSubtracaoPercentagemCodigo;
import com.folha.boot.domain.RubricaInsalubridadeCodigo;
import com.folha.boot.domain.Unidades;

@Repository
public interface RubricaGeralSubtracaoPercentagemCodigoReposytory extends JpaRepository<RubricaGeralSubtracaoPercentagemCodigo, Long>{

	public List<RubricaGeralSubtracaoPercentagemCodigo> findAllByOrderByCodigoAsc();

	public List<RubricaGeralSubtracaoPercentagemCodigo> findByCodigoContainingOrderByCodigoAsc(String nome);
	
	public List<RubricaGeralSubtracaoPercentagemCodigo> findByCodigoOrderByCodigoAsc(String nome);
	
	public Page<RubricaGeralSubtracaoPercentagemCodigo> findAllByOrderByCodigoAsc( final Pageable page);
	
	public Page<RubricaGeralSubtracaoPercentagemCodigo> findByCodigoContainingOrderByCodigoAsc(String nome, final Pageable page);
	
}