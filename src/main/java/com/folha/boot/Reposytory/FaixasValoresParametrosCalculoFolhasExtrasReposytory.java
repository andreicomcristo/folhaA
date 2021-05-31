package com.folha.boot.Reposytory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.folha.boot.domain.AnoMes;
import com.folha.boot.domain.FaixasValoresParametrosCalculoFolhasExtras;
import com.folha.boot.domain.NiveisCargo;
import com.folha.boot.domain.RegimesDeTrabalho;
import com.folha.boot.domain.TiposDeFolha;
import com.folha.boot.domain.Unidades;

@Repository
public interface FaixasValoresParametrosCalculoFolhasExtrasReposytory extends JpaRepository<FaixasValoresParametrosCalculoFolhasExtras, Long>{

	public List<FaixasValoresParametrosCalculoFolhasExtras> findAllByOrderByIdAnoMesFkNomeAnoMesDescIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaAsc();

	public List<FaixasValoresParametrosCalculoFolhasExtras> findByIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaContainingOrderByIdAnoMesFkNomeAnoMesDescIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaAsc(String nome);
	
	public List<FaixasValoresParametrosCalculoFolhasExtras> findByIdAnoMesFkOrderByIdAnoMesFkNomeAnoMesDescIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaAsc(AnoMes anoMes);
	
	public List<FaixasValoresParametrosCalculoFolhasExtras> findByIdAnoMesFkAndIdNivelFkAndIdRegimeDeTrabalhoFkAndIdTipoDeFolhaFkAndIdCodDiferenciadoFkIdUnidadeFkOrderByIdAnoMesFkNomeAnoMesDescIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaAsc(AnoMes anoMes, NiveisCargo nivel, RegimesDeTrabalho regime, TiposDeFolha folha, Unidades unidade);
	
	public Page<FaixasValoresParametrosCalculoFolhasExtras> findAllByOrderByIdAnoMesFkNomeAnoMesDescIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaAsc(final Pageable page);
	
	public Page<FaixasValoresParametrosCalculoFolhasExtras> findByIdAnoMesFkNomeAnoMesContainingOrderByIdAnoMesFkNomeAnoMesDescIdCodDiferenciadoFkIdUnidadeFkNomeFantasiaAsc(String nome, final Pageable page);
}
