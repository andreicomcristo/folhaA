package com.folha.boot.Reposytory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.folha.boot.domain.AnoMes;
import com.folha.boot.domain.FaixasValoresParametrosCalculoFolhasExtras;
import com.folha.boot.domain.FaixasValoresParametrosCalculoFolhasExtrasIndividual;
import com.folha.boot.domain.NiveisCargo;
import com.folha.boot.domain.RegimesDeTrabalho;
import com.folha.boot.domain.TiposDeFolha;
import com.folha.boot.domain.Unidades;

@Repository
public interface FaixasValoresParametrosCalculoFolhasExtrasIndividualReposytory extends JpaRepository<FaixasValoresParametrosCalculoFolhasExtrasIndividual, Long>{

	public List<FaixasValoresParametrosCalculoFolhasExtrasIndividual> findAllByOrderByIdAnoMesFkNomeAnoMesDescIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaAsc();

	public List<FaixasValoresParametrosCalculoFolhasExtrasIndividual> findByIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaContainingOrderByIdAnoMesFkNomeAnoMesDescIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaAsc(String nome);
	
	public List<FaixasValoresParametrosCalculoFolhasExtrasIndividual> findByIdAnoMesFkOrderByIdAnoMesFkNomeAnoMesDescIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaAsc(AnoMes anoMes);
	
	public List<FaixasValoresParametrosCalculoFolhasExtrasIndividual> findByIdAnoMesFkAndIdNivelFkAndIdRegimeDeTrabalhoFkAndIdTipoDeFolhaFkAndIdCodDiferenciadoFkIdUnidadeFkOrderByIdAnoMesFkNomeAnoMesDescIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaAsc(AnoMes anoMes, NiveisCargo nivel, RegimesDeTrabalho regime, TiposDeFolha folha, Unidades unidade);
	
	public Page<FaixasValoresParametrosCalculoFolhasExtrasIndividual> findAllByOrderByIdAnoMesFkNomeAnoMesDescIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaAsc(final Pageable page);
	
	public Page<FaixasValoresParametrosCalculoFolhasExtrasIndividual> findByIdAnoMesFkNomeAnoMesContainingOrderByIdAnoMesFkNomeAnoMesDescIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaAsc(String nome, final Pageable page);
	
	public Page<FaixasValoresParametrosCalculoFolhasExtrasIndividual> findByIdFuncionarioFkIdPessoaFkNomeContainingOrderByIdAnoMesFkNomeAnoMesDescIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaAsc(String nome, final Pageable page);
}
