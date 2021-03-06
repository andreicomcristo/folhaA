package com.folha.boot.domain;

import java.util.List;
import javax.persistence.*;

import com.folha.boot.service.util.UtilidadesDeTexto;

@SuppressWarnings("serial")
@Entity
@Table(name = "fator_patronal")
public class FatorPatronal extends AbstractEntity<Long> {

	@Column(name = "fator")
    private Double fator;
    @JoinColumn(name = "id_ano_mes_fk", referencedColumnName = "id")
    @ManyToOne
    private AnoMes idAnoMesFk;
    
    public FatorPatronal() {
    }

	public Double getFator() {
		return fator;
	}

	public void setFator(Double fator) {
		this.fator = fator;
	}

	public AnoMes getIdAnoMesFk() {
		return idAnoMesFk;
	}

	public void setIdAnoMesFk(AnoMes idAnoMesFk) {
		this.idAnoMesFk = idAnoMesFk;
	}

	    
    
}
