package com.folha.boot.domain;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Table(name="funcionarios_ferias_periodos")
public class FuncionariosFeriasPeriodos extends AbstractEntity<Long> {
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_assinatura")
	private Date dtAssinatura;

	@Temporal(TemporalType.DATE)
	@Column(name="dt_cadastro")
	private Date dtCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="dt_cancelamento")
	private Date dtCancelamento;

	@Temporal(TemporalType.DATE)
	@Column(name="dt_final")
	private Date dtFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="dt_inicial")
	private Date dtInicial;

	@Column(name="motivo_cancelamento")
	private String motivoCancelamento;

	//bi-directional many-to-one association to FuncionariosFeria
	@ManyToOne
	@JoinColumn(name="id_ferias_fk")
	private FuncionariosFerias funcionariosFerias;

	//bi-directional many-to-one association to PessoaOperadore
	@ManyToOne
	@JoinColumn(name="id_operador_cadastro_fk")
	private PessoaOperadores pessoaOperadores1;

	//bi-directional many-to-one association to PessoaOperadore
	@ManyToOne
	@JoinColumn(name="id_operador_cancelamento_fk")
	private PessoaOperadores pessoaOperadores2;

	public FuncionariosFeriasPeriodos() {
	}

	public Date getDtAssinatura() {
		return dtAssinatura;
	}

	public void setDtAssinatura(Date dtAssinatura) {
		this.dtAssinatura = dtAssinatura;
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

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}

	public FuncionariosFerias getFuncionariosFerias() {
		return funcionariosFerias;
	}

	public void setFuncionariosFerias(FuncionariosFerias funcionariosFerias) {
		this.funcionariosFerias = funcionariosFerias;
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

}