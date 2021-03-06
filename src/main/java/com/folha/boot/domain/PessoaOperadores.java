package com.folha.boot.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.folha.boot.domain.seguranca.Perfil;

@SuppressWarnings("serial")
@Entity
@Table(name = "pessoa_operadores")
public class PessoaOperadores extends AbstractEntity<Long> {
	    
    @Column(name = "dt_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dtCadastro;
    @Column(name = "dt_cancelamento")
    @Temporal(TemporalType.DATE)
    private Date dtCancelamento;
    @Column(name = "motivo_cancelamento")
    private String motivoCancelamento;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private Boolean enabled;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<FuncionariosCapacitacoes> funcionariosCapacitacoesList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<FuncionariosCapacitacoes> funcionariosCapacitacoesList1;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<FuncionariosLicencas> funcionariosLicencasList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<FuncionariosLicencas> funcionariosLicencasList1;
    @OneToMany(mappedBy = "idOperadorUltimaAlteracaoFk")
    private List<FuncionariosLicencas> funcionariosLicencasList2;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<HistFuncionariosNiveisCarreira> histFuncionariosNiveisCarreiraList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HistFuncionariosNiveisCarreira> histFuncionariosNiveisCarreiraList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperadorCadastroFk")
    private List<HistFuncionariosAutorizacao> histFuncionariosAutorizacaoList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HistFuncionariosAutorizacao> histFuncionariosAutorizacaoList1;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<FuncionariosAnexos> funcionariosAnexosList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<FuncionariosAnexos> funcionariosAnexosList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperadorCadastroFk")
    private List<HistFuncionariosClasse> histFuncionariosClasseList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HistFuncionariosClasse> histFuncionariosClasseList1;
    @OneToMany(mappedBy = "idOperadorFk")
    private List<AcessoOperadoresCoordenacao> acessoOperadoresCoordenacaoList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HistFuncionariosCargaHoraria> histFuncionariosCargaHorariaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperadorCadastroFk")
    private List<HistFuncionariosCargaHoraria> histFuncionariosCargaHorariaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperadorCadastroFk")
    private List<HistFuncionariosCargos> histFuncionariosCargosList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HistFuncionariosCargos> histFuncionariosCargosList1;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<PessoaFilhos> pessoaFilhosList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<PessoaFilhos> pessoaFilhosList1;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<FuncionariosFeriasPeriodos> funcionariosFeriasPeriodosList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<FuncionariosFeriasPeriodos> funcionariosFeriasPeriodosList1;
    @JoinColumn(name = "id_pessoa_fk", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa idPessoaFk;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<PessoaOperadores> pessoaOperadoresList;
    @JoinColumn(name = "id_operador_cadastro_fk", referencedColumnName = "id")
    @ManyToOne
    private PessoaOperadores idOperadorCadastroFk;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<PessoaOperadores> pessoaOperadoresList1;
    @JoinColumn(name = "id_operador_cancelamento_fk", referencedColumnName = "id")
    @ManyToOne
    private PessoaOperadores idOperadorCancelamentoFk;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<Unidades> unidadesList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<Unidades> unidadesList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperadorCadastroFk")
    private List<HistUnidadesNaturezaJuridica> histUnidadesNaturezaJuridicaList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HistUnidadesNaturezaJuridica> histUnidadesNaturezaJuridicaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperadorCadastroFk")
    private List<HistUnidadesRegime> histUnidadesRegimeList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HistUnidadesRegime> histUnidadesRegimeList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperadorFk")
    private List<AcessoOperadoresUnidades> acessoOperadoresUnidadesList;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<Pessoa> pessoaList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<Pessoa> pessoaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperadorCadastroFk")
    private List<HistFuncionariosCarreira> histFuncionariosCarreiraList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HistFuncionariosCarreira> histFuncionariosCarreiraList1;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<FuncionariosFerias> funcionariosFeriasList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<FuncionariosFerias> funcionariosFeriasList1;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HistFuncionariosSituacoes> histFuncionariosSituacoesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperadorCadastroFk")
    private List<HistFuncionariosSituacoes> histFuncionariosSituacoesList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperadorCriacaoFk")
    private List<Autorizacoes> autorizacoesList;
    //CONFERIR RELACIONAMENTOS NO BANCO E NO JAVA
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPessoaOperadoresFk")
    private List<UsersOperador> usersOperadorList;*/
    
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<LiberacaoIndividualEscala> liberacaoIndividualEscalaList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<LiberacaoIndividualEscala> liberacaoIndividualEscalaList1;
    
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<FuncionariosLicencasCid> funcionariosLicencasCidList;
    
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<PessoaIncrementoDeRiscoSede> pessoaIncrementoDeRiscoSedeList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<PessoaIncrementoDeRiscoSede> pessoaIncrementoDeRiscoSedeList1;
    
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<FaixasValoresLicencaMaternidade> faixasValoresLicencaMaternidadeList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<FaixasValoresLicencaMaternidade> faixasValoresLicencaMaternidadeList1;
    @OneToMany(mappedBy = "idOperadorUltimaMudancaFk")
    private List<FaixasValoresLicencaMaternidade> faixasValoresLicencaMaternidadeList2;
    
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<PessoaComplementoDePlantaoSede> pessoaComplementoDePlantaoSedeList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<PessoaComplementoDePlantaoSede> pessoaComplementoDePlantaoSedeList1;
    
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<Escala> escalaList;
    @OneToMany(mappedBy = "idOperadorMudancaFk")
    private List<Escala> escalaList1;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<EscalaPosTransparencia> escalaPosTransparenciaList;
    @OneToMany(mappedBy = "idOperadorMudancaFk")
    private List<EscalaPosTransparencia> escalaPosTransparenciaList1;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<PessoaBancos> pessoaBancosList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<PessoaBancos> pessoaBancosList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperadorCadastroFk")
    private List<HistFuncionariosVinculos> histFuncionariosVinculosList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HistFuncionariosVinculos> histFuncionariosVinculosList1;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<HistUnidadesDiretor> histUnidadesDiretorList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HistUnidadesDiretor> histUnidadesDiretorList1;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<PessoaFuncionarios> pessoaFuncionariosList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<PessoaFuncionarios> pessoaFuncionariosList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperadorCadastroFk")
    private List<HistFuncionariosUnidadeLotacao> histFuncionariosUnidadeLotacaoList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HistFuncionariosUnidadeLotacao> histFuncionariosUnidadeLotacaoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperadorCadastroFk")
    private List<HistFuncionariosUnidadeAtuacao> histFuncionariosUnidadeAtuacaoList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HistFuncionariosUnidadeAtuacao> histFuncionariosUnidadeAtuacaoList1;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<PessoaComplementoDePlantao> pessoaComplementoDePlantaoList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<PessoaComplementoDePlantao> pessoaComplementoDePlantaoList1;
    
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<EscalaAlteracoes> escalaAlteracoesList;
    @OneToMany(mappedBy = "idOperadorMudancaFk")
    private List<EscalaAlteracoes> escalaAlteracoesList1;
    
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<CodigoDiferenciado> codigoDiferenciadoList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<CodigoDiferenciado> codigoDiferenciadoList1;
    
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<HorasFaltasFolhasVariaveis> horasFaltasFolhasVariaveisList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<HorasFaltasFolhasVariaveis> horasFaltasFolhasVariaveisList1;
    
    
    @OneToMany(mappedBy = "idOperadorFk")
    private List<Perfil> perfilList;
    
    
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<PessoaCodDiferenciado> pessoaCodDiferenciadoList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<PessoaCodDiferenciado> pessoaCodDiferenciadoList1;
    @OneToMany(mappedBy = "idOperadorConfirmacaoSedeFk")
    private List<PessoaCodDiferenciado> pessoaCodDiferenciadoList2;
    
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<PessoaChDif> pessoaChDifList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<PessoaChDif> pessoaChDifList1;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<PessoaIncrementoDeRisco> pessoaIncrementoDeRiscoList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<PessoaIncrementoDeRisco> pessoaIncrementoDeRiscoList1;
    
    @OneToMany(mappedBy = "idOperadorAvaliacaoSedeFk")
    private List<PessoaLimiteHoras> pessoaLimiteHorasList;
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<PessoaLimiteHoras> pessoaLimiteHorasList1;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<PessoaLimiteHoras> pessoaLimiteHorasList2;
    
    @OneToMany(mappedBy = "idOperadorCadastroFk")
    private List<EscalaCodDiferenciado> escalaCodDiferenciadoList;
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<EscalaCodDiferenciado> escalaCodDiferenciadoList1;
    
    @OneToMany(mappedBy = "idOperadorCancelamentoFk")
    private List<RubricaPensao> rubricaPensaoList;
    
    
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public Date getDtCancelamento() {
		return dtCancelamento;
	}
	public void setDtCancelamento(Date dtCancelamento) {
		this.dtCancelamento = dtCancelamento;
	}
	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}
	public void setMotivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public List<FuncionariosCapacitacoes> getFuncionariosCapacitacoesList() {
		return funcionariosCapacitacoesList;
	}
	public void setFuncionariosCapacitacoesList(List<FuncionariosCapacitacoes> funcionariosCapacitacoesList) {
		this.funcionariosCapacitacoesList = funcionariosCapacitacoesList;
	}
	public List<FuncionariosCapacitacoes> getFuncionariosCapacitacoesList1() {
		return funcionariosCapacitacoesList1;
	}
	public void setFuncionariosCapacitacoesList1(List<FuncionariosCapacitacoes> funcionariosCapacitacoesList1) {
		this.funcionariosCapacitacoesList1 = funcionariosCapacitacoesList1;
	}
	public List<FuncionariosLicencas> getFuncionariosLicencasList() {
		return funcionariosLicencasList;
	}
	public void setFuncionariosLicencasList(List<FuncionariosLicencas> funcionariosLicencasList) {
		this.funcionariosLicencasList = funcionariosLicencasList;
	}
	public List<FuncionariosLicencas> getFuncionariosLicencasList1() {
		return funcionariosLicencasList1;
	}
	public void setFuncionariosLicencasList1(List<FuncionariosLicencas> funcionariosLicencasList1) {
		this.funcionariosLicencasList1 = funcionariosLicencasList1;
	}
	public List<FuncionariosLicencas> getFuncionariosLicencasList2() {
		return funcionariosLicencasList2;
	}
	public void setFuncionariosLicencasList2(List<FuncionariosLicencas> funcionariosLicencasList2) {
		this.funcionariosLicencasList2 = funcionariosLicencasList2;
	}
	public List<HistFuncionariosNiveisCarreira> getHistFuncionariosNiveisCarreiraList() {
		return histFuncionariosNiveisCarreiraList;
	}
	public void setHistFuncionariosNiveisCarreiraList(
			List<HistFuncionariosNiveisCarreira> histFuncionariosNiveisCarreiraList) {
		this.histFuncionariosNiveisCarreiraList = histFuncionariosNiveisCarreiraList;
	}
	public List<HistFuncionariosNiveisCarreira> getHistFuncionariosNiveisCarreiraList1() {
		return histFuncionariosNiveisCarreiraList1;
	}
	public void setHistFuncionariosNiveisCarreiraList1(
			List<HistFuncionariosNiveisCarreira> histFuncionariosNiveisCarreiraList1) {
		this.histFuncionariosNiveisCarreiraList1 = histFuncionariosNiveisCarreiraList1;
	}
	public List<HistFuncionariosAutorizacao> getHistFuncionariosAutorizacaoList() {
		return histFuncionariosAutorizacaoList;
	}
	public void setHistFuncionariosAutorizacaoList(List<HistFuncionariosAutorizacao> histFuncionariosAutorizacaoList) {
		this.histFuncionariosAutorizacaoList = histFuncionariosAutorizacaoList;
	}
	public List<HistFuncionariosAutorizacao> getHistFuncionariosAutorizacaoList1() {
		return histFuncionariosAutorizacaoList1;
	}
	public void setHistFuncionariosAutorizacaoList1(List<HistFuncionariosAutorizacao> histFuncionariosAutorizacaoList1) {
		this.histFuncionariosAutorizacaoList1 = histFuncionariosAutorizacaoList1;
	}
	public List<FuncionariosAnexos> getFuncionariosAnexosList() {
		return funcionariosAnexosList;
	}
	public void setFuncionariosAnexosList(List<FuncionariosAnexos> funcionariosAnexosList) {
		this.funcionariosAnexosList = funcionariosAnexosList;
	}
	public List<FuncionariosAnexos> getFuncionariosAnexosList1() {
		return funcionariosAnexosList1;
	}
	public void setFuncionariosAnexosList1(List<FuncionariosAnexos> funcionariosAnexosList1) {
		this.funcionariosAnexosList1 = funcionariosAnexosList1;
	}
	public List<HistFuncionariosClasse> getHistFuncionariosClasseList() {
		return histFuncionariosClasseList;
	}
	public void setHistFuncionariosClasseList(List<HistFuncionariosClasse> histFuncionariosClasseList) {
		this.histFuncionariosClasseList = histFuncionariosClasseList;
	}
	public List<HistFuncionariosClasse> getHistFuncionariosClasseList1() {
		return histFuncionariosClasseList1;
	}
	public void setHistFuncionariosClasseList1(List<HistFuncionariosClasse> histFuncionariosClasseList1) {
		this.histFuncionariosClasseList1 = histFuncionariosClasseList1;
	}
	public List<AcessoOperadoresCoordenacao> getAcessoOperadoresCoordenacaoList() {
		return acessoOperadoresCoordenacaoList;
	}
	public void setAcessoOperadoresCoordenacaoList(List<AcessoOperadoresCoordenacao> acessoOperadoresCoordenacaoList) {
		this.acessoOperadoresCoordenacaoList = acessoOperadoresCoordenacaoList;
	}
	public List<HistFuncionariosCargaHoraria> getHistFuncionariosCargaHorariaList() {
		return histFuncionariosCargaHorariaList;
	}
	public void setHistFuncionariosCargaHorariaList(List<HistFuncionariosCargaHoraria> histFuncionariosCargaHorariaList) {
		this.histFuncionariosCargaHorariaList = histFuncionariosCargaHorariaList;
	}
	public List<HistFuncionariosCargaHoraria> getHistFuncionariosCargaHorariaList1() {
		return histFuncionariosCargaHorariaList1;
	}
	public void setHistFuncionariosCargaHorariaList1(List<HistFuncionariosCargaHoraria> histFuncionariosCargaHorariaList1) {
		this.histFuncionariosCargaHorariaList1 = histFuncionariosCargaHorariaList1;
	}
	public List<HistFuncionariosCargos> getHistFuncionariosCargosList() {
		return histFuncionariosCargosList;
	}
	public void setHistFuncionariosCargosList(List<HistFuncionariosCargos> histFuncionariosCargosList) {
		this.histFuncionariosCargosList = histFuncionariosCargosList;
	}
	public List<HistFuncionariosCargos> getHistFuncionariosCargosList1() {
		return histFuncionariosCargosList1;
	}
	public void setHistFuncionariosCargosList1(List<HistFuncionariosCargos> histFuncionariosCargosList1) {
		this.histFuncionariosCargosList1 = histFuncionariosCargosList1;
	}
	public List<PessoaFilhos> getPessoaFilhosList() {
		return pessoaFilhosList;
	}
	public void setPessoaFilhosList(List<PessoaFilhos> pessoaFilhosList) {
		this.pessoaFilhosList = pessoaFilhosList;
	}
	public List<PessoaFilhos> getPessoaFilhosList1() {
		return pessoaFilhosList1;
	}
	public void setPessoaFilhosList1(List<PessoaFilhos> pessoaFilhosList1) {
		this.pessoaFilhosList1 = pessoaFilhosList1;
	}
	public List<FuncionariosFeriasPeriodos> getFuncionariosFeriasPeriodosList() {
		return funcionariosFeriasPeriodosList;
	}
	public void setFuncionariosFeriasPeriodosList(List<FuncionariosFeriasPeriodos> funcionariosFeriasPeriodosList) {
		this.funcionariosFeriasPeriodosList = funcionariosFeriasPeriodosList;
	}
	public List<FuncionariosFeriasPeriodos> getFuncionariosFeriasPeriodosList1() {
		return funcionariosFeriasPeriodosList1;
	}
	public void setFuncionariosFeriasPeriodosList1(List<FuncionariosFeriasPeriodos> funcionariosFeriasPeriodosList1) {
		this.funcionariosFeriasPeriodosList1 = funcionariosFeriasPeriodosList1;
	}
	public Pessoa getIdPessoaFk() {
		return idPessoaFk;
	}
	public void setIdPessoaFk(Pessoa idPessoaFk) {
		this.idPessoaFk = idPessoaFk;
	}
	public List<PessoaOperadores> getPessoaOperadoresList() {
		return pessoaOperadoresList;
	}
	public void setPessoaOperadoresList(List<PessoaOperadores> pessoaOperadoresList) {
		this.pessoaOperadoresList = pessoaOperadoresList;
	}
	public PessoaOperadores getIdOperadorCadastroFk() {
		return idOperadorCadastroFk;
	}
	public void setIdOperadorCadastroFk(PessoaOperadores idOperadorCadastroFk) {
		this.idOperadorCadastroFk = idOperadorCadastroFk;
	}
	public List<PessoaOperadores> getPessoaOperadoresList1() {
		return pessoaOperadoresList1;
	}
	public void setPessoaOperadoresList1(List<PessoaOperadores> pessoaOperadoresList1) {
		this.pessoaOperadoresList1 = pessoaOperadoresList1;
	}
	public PessoaOperadores getIdOperadorCancelamentoFk() {
		return idOperadorCancelamentoFk;
	}
	public void setIdOperadorCancelamentoFk(PessoaOperadores idOperadorCancelamentoFk) {
		this.idOperadorCancelamentoFk = idOperadorCancelamentoFk;
	}
	public List<Unidades> getUnidadesList() {
		return unidadesList;
	}
	public void setUnidadesList(List<Unidades> unidadesList) {
		this.unidadesList = unidadesList;
	}
	public List<Unidades> getUnidadesList1() {
		return unidadesList1;
	}
	public void setUnidadesList1(List<Unidades> unidadesList1) {
		this.unidadesList1 = unidadesList1;
	}
	public List<HistUnidadesNaturezaJuridica> getHistUnidadesNaturezaJuridicaList() {
		return histUnidadesNaturezaJuridicaList;
	}
	public void setHistUnidadesNaturezaJuridicaList(List<HistUnidadesNaturezaJuridica> histUnidadesNaturezaJuridicaList) {
		this.histUnidadesNaturezaJuridicaList = histUnidadesNaturezaJuridicaList;
	}
	public List<HistUnidadesNaturezaJuridica> getHistUnidadesNaturezaJuridicaList1() {
		return histUnidadesNaturezaJuridicaList1;
	}
	public void setHistUnidadesNaturezaJuridicaList1(List<HistUnidadesNaturezaJuridica> histUnidadesNaturezaJuridicaList1) {
		this.histUnidadesNaturezaJuridicaList1 = histUnidadesNaturezaJuridicaList1;
	}
	public List<HistUnidadesRegime> getHistUnidadesRegimeList() {
		return histUnidadesRegimeList;
	}
	public void setHistUnidadesRegimeList(List<HistUnidadesRegime> histUnidadesRegimeList) {
		this.histUnidadesRegimeList = histUnidadesRegimeList;
	}
	public List<HistUnidadesRegime> getHistUnidadesRegimeList1() {
		return histUnidadesRegimeList1;
	}
	public void setHistUnidadesRegimeList1(List<HistUnidadesRegime> histUnidadesRegimeList1) {
		this.histUnidadesRegimeList1 = histUnidadesRegimeList1;
	}
	public List<AcessoOperadoresUnidades> getAcessoOperadoresUnidadesList() {
		return acessoOperadoresUnidadesList;
	}
	public void setAcessoOperadoresUnidadesList(List<AcessoOperadoresUnidades> acessoOperadoresUnidadesList) {
		this.acessoOperadoresUnidadesList = acessoOperadoresUnidadesList;
	}
	public List<Pessoa> getPessoaList() {
		return pessoaList;
	}
	public void setPessoaList(List<Pessoa> pessoaList) {
		this.pessoaList = pessoaList;
	}
	public List<Pessoa> getPessoaList1() {
		return pessoaList1;
	}
	public void setPessoaList1(List<Pessoa> pessoaList1) {
		this.pessoaList1 = pessoaList1;
	}
	public List<HistFuncionariosCarreira> getHistFuncionariosCarreiraList() {
		return histFuncionariosCarreiraList;
	}
	public void setHistFuncionariosCarreiraList(List<HistFuncionariosCarreira> histFuncionariosCarreiraList) {
		this.histFuncionariosCarreiraList = histFuncionariosCarreiraList;
	}
	public List<HistFuncionariosCarreira> getHistFuncionariosCarreiraList1() {
		return histFuncionariosCarreiraList1;
	}
	public void setHistFuncionariosCarreiraList1(List<HistFuncionariosCarreira> histFuncionariosCarreiraList1) {
		this.histFuncionariosCarreiraList1 = histFuncionariosCarreiraList1;
	}
	public List<FuncionariosFerias> getFuncionariosFeriasList() {
		return funcionariosFeriasList;
	}
	public void setFuncionariosFeriasList(List<FuncionariosFerias> funcionariosFeriasList) {
		this.funcionariosFeriasList = funcionariosFeriasList;
	}
	public List<FuncionariosFerias> getFuncionariosFeriasList1() {
		return funcionariosFeriasList1;
	}
	public void setFuncionariosFeriasList1(List<FuncionariosFerias> funcionariosFeriasList1) {
		this.funcionariosFeriasList1 = funcionariosFeriasList1;
	}
	public List<HistFuncionariosSituacoes> getHistFuncionariosSituacoesList() {
		return histFuncionariosSituacoesList;
	}
	public void setHistFuncionariosSituacoesList(List<HistFuncionariosSituacoes> histFuncionariosSituacoesList) {
		this.histFuncionariosSituacoesList = histFuncionariosSituacoesList;
	}
	public List<HistFuncionariosSituacoes> getHistFuncionariosSituacoesList1() {
		return histFuncionariosSituacoesList1;
	}
	public void setHistFuncionariosSituacoesList1(List<HistFuncionariosSituacoes> histFuncionariosSituacoesList1) {
		this.histFuncionariosSituacoesList1 = histFuncionariosSituacoesList1;
	}
	public List<Autorizacoes> getAutorizacoesList() {
		return autorizacoesList;
	}
	public void setAutorizacoesList(List<Autorizacoes> autorizacoesList) {
		this.autorizacoesList = autorizacoesList;
	}
	/*public List<UsersOperador> getUsersOperadorList() {
		return usersOperadorList;
	}
	public void setUsersOperadorList(List<UsersOperador> usersOperadorList) {
		this.usersOperadorList = usersOperadorList;
	}*/
	public List<Escala> getEscalaList() {
		return escalaList;
	}
	public void setEscalaList(List<Escala> escalaList) {
		this.escalaList = escalaList;
	}
	public List<Escala> getEscalaList1() {
		return escalaList1;
	}
	public void setEscalaList1(List<Escala> escalaList1) {
		this.escalaList1 = escalaList1;
	}
	public List<PessoaBancos> getPessoaBancosList() {
		return pessoaBancosList;
	}
	public void setPessoaBancosList(List<PessoaBancos> pessoaBancosList) {
		this.pessoaBancosList = pessoaBancosList;
	}
	public List<PessoaBancos> getPessoaBancosList1() {
		return pessoaBancosList1;
	}
	public void setPessoaBancosList1(List<PessoaBancos> pessoaBancosList1) {
		this.pessoaBancosList1 = pessoaBancosList1;
	}
	public List<HistFuncionariosVinculos> getHistFuncionariosVinculosList() {
		return histFuncionariosVinculosList;
	}
	public void setHistFuncionariosVinculosList(List<HistFuncionariosVinculos> histFuncionariosVinculosList) {
		this.histFuncionariosVinculosList = histFuncionariosVinculosList;
	}
	public List<HistFuncionariosVinculos> getHistFuncionariosVinculosList1() {
		return histFuncionariosVinculosList1;
	}
	public void setHistFuncionariosVinculosList1(List<HistFuncionariosVinculos> histFuncionariosVinculosList1) {
		this.histFuncionariosVinculosList1 = histFuncionariosVinculosList1;
	}
	public List<HistUnidadesDiretor> getHistUnidadesDiretorList() {
		return histUnidadesDiretorList;
	}
	public void setHistUnidadesDiretorList(List<HistUnidadesDiretor> histUnidadesDiretorList) {
		this.histUnidadesDiretorList = histUnidadesDiretorList;
	}
	public List<HistUnidadesDiretor> getHistUnidadesDiretorList1() {
		return histUnidadesDiretorList1;
	}
	public void setHistUnidadesDiretorList1(List<HistUnidadesDiretor> histUnidadesDiretorList1) {
		this.histUnidadesDiretorList1 = histUnidadesDiretorList1;
	}
	public List<PessoaFuncionarios> getPessoaFuncionariosList() {
		return pessoaFuncionariosList;
	}
	public void setPessoaFuncionariosList(List<PessoaFuncionarios> pessoaFuncionariosList) {
		this.pessoaFuncionariosList = pessoaFuncionariosList;
	}
	public List<PessoaFuncionarios> getPessoaFuncionariosList1() {
		return pessoaFuncionariosList1;
	}
	public void setPessoaFuncionariosList1(List<PessoaFuncionarios> pessoaFuncionariosList1) {
		this.pessoaFuncionariosList1 = pessoaFuncionariosList1;
	}
	public List<HistFuncionariosUnidadeLotacao> getHistFuncionariosUnidadeLotacaoList() {
		return histFuncionariosUnidadeLotacaoList;
	}
	public void setHistFuncionariosUnidadeLotacaoList(
			List<HistFuncionariosUnidadeLotacao> histFuncionariosUnidadeLotacaoList) {
		this.histFuncionariosUnidadeLotacaoList = histFuncionariosUnidadeLotacaoList;
	}
	public List<HistFuncionariosUnidadeLotacao> getHistFuncionariosUnidadeLotacaoList1() {
		return histFuncionariosUnidadeLotacaoList1;
	}
	public void setHistFuncionariosUnidadeLotacaoList1(
			List<HistFuncionariosUnidadeLotacao> histFuncionariosUnidadeLotacaoList1) {
		this.histFuncionariosUnidadeLotacaoList1 = histFuncionariosUnidadeLotacaoList1;
	}
	public List<HistFuncionariosUnidadeAtuacao> getHistFuncionariosUnidadeAtuacaoList() {
		return histFuncionariosUnidadeAtuacaoList;
	}
	public void setHistFuncionariosUnidadeAtuacaoList(
			List<HistFuncionariosUnidadeAtuacao> histFuncionariosUnidadeAtuacaoList) {
		this.histFuncionariosUnidadeAtuacaoList = histFuncionariosUnidadeAtuacaoList;
	}
	public List<HistFuncionariosUnidadeAtuacao> getHistFuncionariosUnidadeAtuacaoList1() {
		return histFuncionariosUnidadeAtuacaoList1;
	}
	public void setHistFuncionariosUnidadeAtuacaoList1(
			List<HistFuncionariosUnidadeAtuacao> histFuncionariosUnidadeAtuacaoList1) {
		this.histFuncionariosUnidadeAtuacaoList1 = histFuncionariosUnidadeAtuacaoList1;
	}
	public List<EscalaPosTransparencia> getEscalaPosTransparenciaList() {
		return escalaPosTransparenciaList;
	}
	public void setEscalaPosTransparenciaList(List<EscalaPosTransparencia> escalaPosTransparenciaList) {
		this.escalaPosTransparenciaList = escalaPosTransparenciaList;
	}
	public List<EscalaPosTransparencia> getEscalaPosTransparenciaList1() {
		return escalaPosTransparenciaList1;
	}
	public void setEscalaPosTransparenciaList1(List<EscalaPosTransparencia> escalaPosTransparenciaList1) {
		this.escalaPosTransparenciaList1 = escalaPosTransparenciaList1;
	}
	public List<EscalaAlteracoes> getEscalaAlteracoesList() {
		return escalaAlteracoesList;
	}
	public void setEscalaAlteracoesList(List<EscalaAlteracoes> escalaAlteracoesList) {
		this.escalaAlteracoesList = escalaAlteracoesList;
	}
	public List<EscalaAlteracoes> getEscalaAlteracoesList1() {
		return escalaAlteracoesList1;
	}
	public void setEscalaAlteracoesList1(List<EscalaAlteracoes> escalaAlteracoesList1) {
		this.escalaAlteracoesList1 = escalaAlteracoesList1;
	}
	public List<Perfil> getPerfilList() {
		return perfilList;
	}
	public void setPerfilList(List<Perfil> perfilList) {
		this.perfilList = perfilList;
	}
	public List<CodigoDiferenciado> getCodigoDiferenciadoList() {
		return codigoDiferenciadoList;
	}
	public void setCodigoDiferenciadoList(List<CodigoDiferenciado> codigoDiferenciadoList) {
		this.codigoDiferenciadoList = codigoDiferenciadoList;
	}
	public List<CodigoDiferenciado> getCodigoDiferenciadoList1() {
		return codigoDiferenciadoList1;
	}
	public void setCodigoDiferenciadoList1(List<CodigoDiferenciado> codigoDiferenciadoList1) {
		this.codigoDiferenciadoList1 = codigoDiferenciadoList1;
	}
	public List<PessoaCodDiferenciado> getPessoaCodDiferenciadoList() {
		return pessoaCodDiferenciadoList;
	}
	public void setPessoaCodDiferenciadoList(List<PessoaCodDiferenciado> pessoaCodDiferenciadoList) {
		this.pessoaCodDiferenciadoList = pessoaCodDiferenciadoList;
	}
	public List<PessoaCodDiferenciado> getPessoaCodDiferenciadoList1() {
		return pessoaCodDiferenciadoList1;
	}
	public void setPessoaCodDiferenciadoList1(List<PessoaCodDiferenciado> pessoaCodDiferenciadoList1) {
		this.pessoaCodDiferenciadoList1 = pessoaCodDiferenciadoList1;
	}
	public List<PessoaCodDiferenciado> getPessoaCodDiferenciadoList2() {
		return pessoaCodDiferenciadoList2;
	}
	public void setPessoaCodDiferenciadoList2(List<PessoaCodDiferenciado> pessoaCodDiferenciadoList2) {
		this.pessoaCodDiferenciadoList2 = pessoaCodDiferenciadoList2;
	}
	public List<PessoaChDif> getPessoaChDifList() {
		return pessoaChDifList;
	}
	public void setPessoaChDifList(List<PessoaChDif> pessoaChDifList) {
		this.pessoaChDifList = pessoaChDifList;
	}
	public List<PessoaChDif> getPessoaChDifList1() {
		return pessoaChDifList1;
	}
	public void setPessoaChDifList1(List<PessoaChDif> pessoaChDifList1) {
		this.pessoaChDifList1 = pessoaChDifList1;
	}
	public List<PessoaIncrementoDeRisco> getPessoaIncrementoDeRiscoList() {
		return pessoaIncrementoDeRiscoList;
	}
	public void setPessoaIncrementoDeRiscoList(List<PessoaIncrementoDeRisco> pessoaIncrementoDeRiscoList) {
		this.pessoaIncrementoDeRiscoList = pessoaIncrementoDeRiscoList;
	}
	public List<PessoaIncrementoDeRisco> getPessoaIncrementoDeRiscoList1() {
		return pessoaIncrementoDeRiscoList1;
	}
	public void setPessoaIncrementoDeRiscoList1(List<PessoaIncrementoDeRisco> pessoaIncrementoDeRiscoList1) {
		this.pessoaIncrementoDeRiscoList1 = pessoaIncrementoDeRiscoList1;
	}
	public List<PessoaLimiteHoras> getPessoaLimiteHorasList() {
		return pessoaLimiteHorasList;
	}
	public void setPessoaLimiteHorasList(List<PessoaLimiteHoras> pessoaLimiteHorasList) {
		this.pessoaLimiteHorasList = pessoaLimiteHorasList;
	}
	public List<PessoaLimiteHoras> getPessoaLimiteHorasList1() {
		return pessoaLimiteHorasList1;
	}
	public void setPessoaLimiteHorasList1(List<PessoaLimiteHoras> pessoaLimiteHorasList1) {
		this.pessoaLimiteHorasList1 = pessoaLimiteHorasList1;
	}
	public List<PessoaLimiteHoras> getPessoaLimiteHorasList2() {
		return pessoaLimiteHorasList2;
	}
	public void setPessoaLimiteHorasList2(List<PessoaLimiteHoras> pessoaLimiteHorasList2) {
		this.pessoaLimiteHorasList2 = pessoaLimiteHorasList2;
	}
	public List<EscalaCodDiferenciado> getEscalaCodDiferenciadoList() {
		return escalaCodDiferenciadoList;
	}
	public void setEscalaCodDiferenciadoList(List<EscalaCodDiferenciado> escalaCodDiferenciadoList) {
		this.escalaCodDiferenciadoList = escalaCodDiferenciadoList;
	}
	public List<EscalaCodDiferenciado> getEscalaCodDiferenciadoList1() {
		return escalaCodDiferenciadoList1;
	}
	public void setEscalaCodDiferenciadoList1(List<EscalaCodDiferenciado> escalaCodDiferenciadoList1) {
		this.escalaCodDiferenciadoList1 = escalaCodDiferenciadoList1;
	}
	public List<HorasFaltasFolhasVariaveis> getHorasFaltasFolhasVariaveisList() {
		return horasFaltasFolhasVariaveisList;
	}
	public void setHorasFaltasFolhasVariaveisList(List<HorasFaltasFolhasVariaveis> horasFaltasFolhasVariaveisList) {
		this.horasFaltasFolhasVariaveisList = horasFaltasFolhasVariaveisList;
	}
	public List<HorasFaltasFolhasVariaveis> getHorasFaltasFolhasVariaveisList1() {
		return horasFaltasFolhasVariaveisList1;
	}
	public void setHorasFaltasFolhasVariaveisList1(List<HorasFaltasFolhasVariaveis> horasFaltasFolhasVariaveisList1) {
		this.horasFaltasFolhasVariaveisList1 = horasFaltasFolhasVariaveisList1;
	}
	public List<RubricaPensao> getRubricaPensaoList() {
		return rubricaPensaoList;
	}
	public void setRubricaPensaoList(List<RubricaPensao> rubricaPensaoList) {
		this.rubricaPensaoList = rubricaPensaoList;
	}
	public List<PessoaComplementoDePlantao> getPessoaComplementoDePlantaoList() {
		return pessoaComplementoDePlantaoList;
	}
	public void setPessoaComplementoDePlantaoList(List<PessoaComplementoDePlantao> pessoaComplementoDePlantaoList) {
		this.pessoaComplementoDePlantaoList = pessoaComplementoDePlantaoList;
	}
	public List<PessoaComplementoDePlantao> getPessoaComplementoDePlantaoList1() {
		return pessoaComplementoDePlantaoList1;
	}
	public void setPessoaComplementoDePlantaoList1(List<PessoaComplementoDePlantao> pessoaComplementoDePlantaoList1) {
		this.pessoaComplementoDePlantaoList1 = pessoaComplementoDePlantaoList1;
	}
	public List<PessoaIncrementoDeRiscoSede> getPessoaIncrementoDeRiscoSedeList() {
		return pessoaIncrementoDeRiscoSedeList;
	}
	public void setPessoaIncrementoDeRiscoSedeList(List<PessoaIncrementoDeRiscoSede> pessoaIncrementoDeRiscoSedeList) {
		this.pessoaIncrementoDeRiscoSedeList = pessoaIncrementoDeRiscoSedeList;
	}
	public List<PessoaIncrementoDeRiscoSede> getPessoaIncrementoDeRiscoSedeList1() {
		return pessoaIncrementoDeRiscoSedeList1;
	}
	public void setPessoaIncrementoDeRiscoSedeList1(List<PessoaIncrementoDeRiscoSede> pessoaIncrementoDeRiscoSedeList1) {
		this.pessoaIncrementoDeRiscoSedeList1 = pessoaIncrementoDeRiscoSedeList1;
	}
	public List<PessoaComplementoDePlantaoSede> getPessoaComplementoDePlantaoSedeList() {
		return pessoaComplementoDePlantaoSedeList;
	}
	public void setPessoaComplementoDePlantaoSedeList(
			List<PessoaComplementoDePlantaoSede> pessoaComplementoDePlantaoSedeList) {
		this.pessoaComplementoDePlantaoSedeList = pessoaComplementoDePlantaoSedeList;
	}
	public List<PessoaComplementoDePlantaoSede> getPessoaComplementoDePlantaoSedeList1() {
		return pessoaComplementoDePlantaoSedeList1;
	}
	public void setPessoaComplementoDePlantaoSedeList1(
			List<PessoaComplementoDePlantaoSede> pessoaComplementoDePlantaoSedeList1) {
		this.pessoaComplementoDePlantaoSedeList1 = pessoaComplementoDePlantaoSedeList1;
	}
	public List<FuncionariosLicencasCid> getFuncionariosLicencasCidList() {
		return funcionariosLicencasCidList;
	}
	public void setFuncionariosLicencasCidList(List<FuncionariosLicencasCid> funcionariosLicencasCidList) {
		this.funcionariosLicencasCidList = funcionariosLicencasCidList;
	}
	public List<FaixasValoresLicencaMaternidade> getFaixasValoresLicencaMaternidadeList() {
		return faixasValoresLicencaMaternidadeList;
	}
	public void setFaixasValoresLicencaMaternidadeList(
			List<FaixasValoresLicencaMaternidade> faixasValoresLicencaMaternidadeList) {
		this.faixasValoresLicencaMaternidadeList = faixasValoresLicencaMaternidadeList;
	}
	public List<FaixasValoresLicencaMaternidade> getFaixasValoresLicencaMaternidadeList1() {
		return faixasValoresLicencaMaternidadeList1;
	}
	public void setFaixasValoresLicencaMaternidadeList1(
			List<FaixasValoresLicencaMaternidade> faixasValoresLicencaMaternidadeList1) {
		this.faixasValoresLicencaMaternidadeList1 = faixasValoresLicencaMaternidadeList1;
	}
	public List<FaixasValoresLicencaMaternidade> getFaixasValoresLicencaMaternidadeList2() {
		return faixasValoresLicencaMaternidadeList2;
	}
	public void setFaixasValoresLicencaMaternidadeList2(
			List<FaixasValoresLicencaMaternidade> faixasValoresLicencaMaternidadeList2) {
		this.faixasValoresLicencaMaternidadeList2 = faixasValoresLicencaMaternidadeList2;
	}
	public List<LiberacaoIndividualEscala> getLiberacaoIndividualEscalaList() {
		return liberacaoIndividualEscalaList;
	}
	public void setLiberacaoIndividualEscalaList(List<LiberacaoIndividualEscala> liberacaoIndividualEscalaList) {
		this.liberacaoIndividualEscalaList = liberacaoIndividualEscalaList;
	}
	public List<LiberacaoIndividualEscala> getLiberacaoIndividualEscalaList1() {
		return liberacaoIndividualEscalaList1;
	}
	public void setLiberacaoIndividualEscalaList1(List<LiberacaoIndividualEscala> liberacaoIndividualEscalaList1) {
		this.liberacaoIndividualEscalaList1 = liberacaoIndividualEscalaList1;
	}
	
	
    
	
	
}