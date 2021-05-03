package com.folha.boot.domain;

import java.util.List;
import javax.persistence.*;

import com.folha.boot.service.util.UtilidadesDeTexto;

@SuppressWarnings("serial")
@Entity
@Table(name = "rubrica_insalubridade")
public class RubricaInsalubridade extends AbstractEntity<Long> {

	@Column(name = "valor")
    private Double valor;
    @JoinColumn(name = "id_ano_mes_fk", referencedColumnName = "id")
    @ManyToOne
    private AnoMes idAnoMesFk;
    @JoinColumn(name = "id_codigo_fk", referencedColumnName = "id")
    @ManyToOne
    private RubricaInsalubridadeCodigo idCodigoFk;
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public AnoMes getIdAnoMesFk() {
		return idAnoMesFk;
	}
	public void setIdAnoMesFk(AnoMes idAnoMesFk) {
		this.idAnoMesFk = idAnoMesFk;
	}
	public RubricaInsalubridadeCodigo getIdCodigoFk() {
		return idCodigoFk;
	}
	public void setIdCodigoFk(RubricaInsalubridadeCodigo idCodigoFk) {
		this.idCodigoFk = idCodigoFk;
	}
    
    
    
	
}