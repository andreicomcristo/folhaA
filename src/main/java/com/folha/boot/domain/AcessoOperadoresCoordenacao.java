package com.folha.boot.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "acesso_operadores_coordenacao")
public class AcessoOperadoresCoordenacao extends AbstractEntity<Long> {

	@JoinColumn(name = "id_operador_fk", referencedColumnName = "id")
	@ManyToOne
	private PessoaOperadores idOperadorFk;
	
	@JoinColumn(name = "id_coordenacao_fk", referencedColumnName = "id")
	@ManyToOne
	private CoordenacaoEscala idCoordenacaoFk;
	
	public PessoaOperadores getIdOperadorFk() {
		return idOperadorFk;
	}
	public void setIdOperadorFk(PessoaOperadores idOperadorFk) {
		this.idOperadorFk = idOperadorFk;
	}
	public CoordenacaoEscala getIdCoordenacaoFk() {
		return idCoordenacaoFk;
	}
	public void setIdCoordenacaoFk(CoordenacaoEscala idCoordenacaoFk) {
		this.idCoordenacaoFk = idCoordenacaoFk;
	}
	
	
}
