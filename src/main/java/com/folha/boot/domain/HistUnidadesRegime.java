package com.folha.boot.domain;

import java.util.Date;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "hist_unidades_regime")
public class HistUnidadesRegime extends AbstractEntity<Long> {

	@Temporal(TemporalType.DATE)
	@Column(name="dt_cadastro")
	private Date dtCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="dt_cancelamento")
	private Date dtCancelamento;

	@Column(name="motivo_cadastro")
	private String motivoCadastro;

	@Column(name="motivo_cancelamento")
	private String motivoCancelamento;

	//bi-directional many-to-one association to PessoaOperadore
	@ManyToOne
	@JoinColumn(name="id_operador_cadastro_fk")
	private PessoaOperadores pessoaOperadores1;

	//bi-directional many-to-one association to PessoaOperadore
	@ManyToOne
	@JoinColumn(name="id_operador_cancelamento_fk")
	private PessoaOperadores pessoaOperadores2;

	//bi-directional many-to-one association to Unidade
	@ManyToOne
	@JoinColumn(name="id_unidade_de_saude_fk")
	private Unidades unidades;

	//bi-directional many-to-one association to UnidadesRegime
	@ManyToOne
	@JoinColumn(name="id_unidade_regime_fk")
	private UnidadesRegime unidadesRegime;

	public HistUnidadesRegime() {
	}

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

	public String getMotivoCadastro() {
		return motivoCadastro;
	}

	public void setMotivoCadastro(String motivoCadastro) {
		this.motivoCadastro = motivoCadastro;
	}

	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}

	public PessoaOperadores getPessoaOperadores1() {
		return pessoaOperadores1;
	}

	public void setPessoaOperadores1(PessoaOperadores pessoaOperadores1) {
		this.pessoaOperadores1 = pessoaOperadores1;
	}

	public PessoaOperadores getPessoaOperadores2() {
		return pessoaOperadores2;
	}

	public void setPessoaOperadores2(PessoaOperadores pessoaOperadores2) {
		this.pessoaOperadores2 = pessoaOperadores2;
	}

	public Unidades getUnidades() {
		return unidades;
	}

	public void setUnidades(Unidades unidades) {
		this.unidades = unidades;
	}

	public UnidadesRegime getUnidadesRegime() {
		return unidadesRegime;
	}

	public void setUnidadesRegime(UnidadesRegime unidadesRegime) {
		this.unidadesRegime = unidadesRegime;
	}
	
}
