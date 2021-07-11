package com.folha.boot.Reposytory;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.folha.boot.domain.AnoMes;
import com.folha.boot.domain.IncompatibilidadeFolhaFolhaExcessao;
import com.folha.boot.domain.PessoaFuncionarios;
import com.folha.boot.domain.TiposDeFolha;

@Repository
public interface IncompatibilidadeFolhaFolhaExcessaoReposytory extends JpaRepository<IncompatibilidadeFolhaFolhaExcessao, Long> {
	
	public List<IncompatibilidadeFolhaFolhaExcessao> findAllByOrderByIdAnoMesFkNomeAnoMesDescIdFuncionarioFkIdPessoaFkNomeAscIdFolhaFkNomeTipoFolhaAscIdFolhaIncompativelFkNomeTipoFolhaAsc( );
	
	public List<IncompatibilidadeFolhaFolhaExcessao> findByIdFuncionarioFkIdPessoaFkNomeContainingOrderByIdAnoMesFkNomeAnoMesDescIdFuncionarioFkIdPessoaFkNomeAscIdFolhaFkNomeTipoFolhaAscIdFolhaIncompativelFkNomeTipoFolhaAsc (String nome);
	
	public List<IncompatibilidadeFolhaFolhaExcessao> findByIdAnoMesFkOrderByIdAnoMesFkNomeAnoMesDescIdFuncionarioFkIdPessoaFkNomeAscIdFolhaFkNomeTipoFolhaAscIdFolhaIncompativelFkNomeTipoFolhaAsc(AnoMes anoMes);
	
	public List<IncompatibilidadeFolhaFolhaExcessao> findByIdAnoMesFkAndIdFuncionarioFkOrderByIdAnoMesFkNomeAnoMesDescIdFuncionarioFkIdPessoaFkNomeAscIdFolhaFkNomeTipoFolhaAscIdFolhaIncompativelFkNomeTipoFolhaAsc(AnoMes anoMes, PessoaFuncionarios funcionario);
	
	public List<IncompatibilidadeFolhaFolhaExcessao> findByIdFolhaFkAndIdFolhaIncompativelFkOrderByIdAnoMesFkNomeAnoMesDescIdFuncionarioFkIdPessoaFkNomeAscIdFolhaFkNomeTipoFolhaAscIdFolhaIncompativelFkNomeTipoFolhaAsc(TiposDeFolha tiposDeFolha, TiposDeFolha tiposDeFolhaIncompativel);
	
	public List<IncompatibilidadeFolhaFolhaExcessao> findByIdAnoMesFkAndIdFuncionarioFkAndIdFolhaFkAndIdFolhaIncompativelFkOrderByIdAnoMesFkNomeAnoMesDescIdFuncionarioFkIdPessoaFkNomeAscIdFolhaFkNomeTipoFolhaAscIdFolhaIncompativelFkNomeTipoFolhaAsc( AnoMes anoMes, PessoaFuncionarios funcionario, TiposDeFolha tiposDeFolha, TiposDeFolha tiposDeFolhaIncompativel);
	
	
	public Page<IncompatibilidadeFolhaFolhaExcessao> findAllByOrderByIdAnoMesFkNomeAnoMesDescIdFuncionarioFkIdPessoaFkNomeAscIdFolhaFkNomeTipoFolhaAscIdFolhaIncompativelFkNomeTipoFolhaAsc( final Pageable pageable);
	
	public Page<IncompatibilidadeFolhaFolhaExcessao> findByIdAnoMesFkNomeAnoMesContainingOrderByIdAnoMesFkNomeAnoMesDescIdFuncionarioFkIdPessoaFkNomeAscIdFolhaFkNomeTipoFolhaAscIdFolhaIncompativelFkNomeTipoFolhaAsc(String nome, final Pageable pageable);
	
	
}
