package com.folha.boot.domain;

import java.util.List;

import javax.persistence.*;

import com.folha.boot.service.util.UtilidadesDeTexto;

@SuppressWarnings("serial")
@Entity
@Table(name = "niveis_cargo")
public class NiveisCargo extends AbstractEntity<Long> {

	@Column(name = "sigla_nivel_cargo", nullable = false, length = 10)
	private String siglaNivelCargo;

	@Column(name = "nome_nivel_cargo", nullable = false, length = 150)
	private String nomeNivelCargo;

	@Column(name = "descricao_nivel_cargo", length = 300)
	private String descricaoNivelCargo;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idNivelCargoFk")
	private List<Cargos> cargosList;

	@OneToMany(mappedBy = "idNivelFk")
	private List<FaixasValoresParametrosCalculoFolhasExtras> faixasValoresParametrosCalculoFolhasExtrasList;
	
	@OneToMany(mappedBy = "idNivelCargoFk")
    private List<TiposDeFolhaNivelCargo> tiposDeFolhaNivelCargoList;
	
	@OneToMany(mappedBy = "idNivelCargoFk")
    private List<FaixasValoresGpfMedica> faixasValoresGpfMedicaList;
	
	@OneToMany(mappedBy = "idNivelCargoFk")
    private List<FaixasValoresGpf> faixasValoresGpfList;
	
	@OneToMany(mappedBy = "idNivelCargoFk")
    private List<FaixasValoresGpfMedicaDiferenciada> faixasValoresGpfMedicaDiferenciadaList;
	
	@OneToMany(mappedBy = "idNivelCargoFk")
    private List<FaixasValoresGpfDiferenciada> faixasValoresGpfDiferenciadaList;
	
	@OneToMany(mappedBy = "idNivelCargoFk")
    private List<FaixasValoresPss> faixasValoresPssList;
	
	@OneToMany(mappedBy = "idNivelCargoFk")
    private List<FaixasValoresGpfMedicaDiferenciadaDiarista> faixasValoresGpfMedicaDiferenciadaDiaristaList;
	@OneToMany(mappedBy = "idNivelCargoFk")
    private List<FaixasValoresFolhExt> faixasValoresFolhExtList;
	@OneToMany(mappedBy = "idNivelCargoFk")
    private List<FaixasValoresResidente> faixasValoresResidenteList;
	
	@OneToMany(mappedBy = "idNivelFk")
    private List<FaixasValoresParametrosCalculoFolhasExtrasIndividual> faixasValoresParametrosCalculoFolhasExtrasIndividualList;
    @OneToMany(mappedBy = "idNivelCargoFk")
    private List<FaixasValoresGpfCedido> faixasValoresGpfCedidoList;

	public String getSiglaNivelCargo() {
		return siglaNivelCargo;
	}

	public void setSiglaNivelCargo(String siglaNivelCargo) {
		this.siglaNivelCargo = UtilidadesDeTexto.retiraEspacosDuplosAcentosEConverteEmMaiusculo(siglaNivelCargo);
	}

	public String getNomeNivelCargo() {
		return nomeNivelCargo;
	}

	public void setNomeNivelCargo(String nomeNivelCargo) {
		this.nomeNivelCargo = UtilidadesDeTexto.retiraEspacosDuplosAcentosEConverteEmMaiusculo(nomeNivelCargo);
	}

	public String getDescricaoNivelCargo() {
		return descricaoNivelCargo;
	}

	public void setDescricaoNivelCargo(String descricaoNivelCargo) {
		this.descricaoNivelCargo = UtilidadesDeTexto
				.retiraEspacosDuplosAcentosEConverteEmMaiusculo(descricaoNivelCargo);
	}

	public List<Cargos> getCargosList() {
		return cargosList;
	}

	public void setCargosList(List<Cargos> cargosList) {
		this.cargosList = cargosList;
	}

	public List<FaixasValoresParametrosCalculoFolhasExtras> getFaixasValoresParametrosCalculoFolhasExtrasList() {
		return faixasValoresParametrosCalculoFolhasExtrasList;
	}

	public void setFaixasValoresParametrosCalculoFolhasExtrasList(
			List<FaixasValoresParametrosCalculoFolhasExtras> faixasValoresParametrosCalculoFolhasExtrasList) {
		this.faixasValoresParametrosCalculoFolhasExtrasList = faixasValoresParametrosCalculoFolhasExtrasList;
	}

	public List<TiposDeFolhaNivelCargo> getTiposDeFolhaNivelCargoList() {
		return tiposDeFolhaNivelCargoList;
	}

	public void setTiposDeFolhaNivelCargoList(List<TiposDeFolhaNivelCargo> tiposDeFolhaNivelCargoList) {
		this.tiposDeFolhaNivelCargoList = tiposDeFolhaNivelCargoList;
	}

	public List<FaixasValoresGpf> getFaixasValoresGpfList() {
		return faixasValoresGpfList;
	}

	public void setFaixasValoresGpfList(List<FaixasValoresGpf> faixasValoresGpfList) {
		this.faixasValoresGpfList = faixasValoresGpfList;
	}

	public List<FaixasValoresGpfMedica> getFaixasValoresGpfMedicaList() {
		return faixasValoresGpfMedicaList;
	}

	public void setFaixasValoresGpfMedicaList(List<FaixasValoresGpfMedica> faixasValoresGpfMedicaList) {
		this.faixasValoresGpfMedicaList = faixasValoresGpfMedicaList;
	}

	public List<FaixasValoresGpfMedicaDiferenciada> getFaixasValoresGpfMedicaDiferenciadaList() {
		return faixasValoresGpfMedicaDiferenciadaList;
	}

	public void setFaixasValoresGpfMedicaDiferenciadaList(
			List<FaixasValoresGpfMedicaDiferenciada> faixasValoresGpfMedicaDiferenciadaList) {
		this.faixasValoresGpfMedicaDiferenciadaList = faixasValoresGpfMedicaDiferenciadaList;
	}

	public List<FaixasValoresGpfDiferenciada> getFaixasValoresGpfDiferenciadaList() {
		return faixasValoresGpfDiferenciadaList;
	}

	public void setFaixasValoresGpfDiferenciadaList(List<FaixasValoresGpfDiferenciada> faixasValoresGpfDiferenciadaList) {
		this.faixasValoresGpfDiferenciadaList = faixasValoresGpfDiferenciadaList;
	}

	public List<FaixasValoresPss> getFaixasValoresPssList() {
		return faixasValoresPssList;
	}

	public void setFaixasValoresPssList(List<FaixasValoresPss> faixasValoresPssList) {
		this.faixasValoresPssList = faixasValoresPssList;
	}

	public List<FaixasValoresGpfMedicaDiferenciadaDiarista> getFaixasValoresGpfMedicaDiferenciadaDiaristaList() {
		return faixasValoresGpfMedicaDiferenciadaDiaristaList;
	}

	public void setFaixasValoresGpfMedicaDiferenciadaDiaristaList(
			List<FaixasValoresGpfMedicaDiferenciadaDiarista> faixasValoresGpfMedicaDiferenciadaDiaristaList) {
		this.faixasValoresGpfMedicaDiferenciadaDiaristaList = faixasValoresGpfMedicaDiferenciadaDiaristaList;
	}

	public List<FaixasValoresFolhExt> getFaixasValoresFolhExtList() {
		return faixasValoresFolhExtList;
	}

	public void setFaixasValoresFolhExtList(List<FaixasValoresFolhExt> faixasValoresFolhExtList) {
		this.faixasValoresFolhExtList = faixasValoresFolhExtList;
	}

	public List<FaixasValoresResidente> getFaixasValoresResidenteList() {
		return faixasValoresResidenteList;
	}

	public void setFaixasValoresResidenteList(List<FaixasValoresResidente> faixasValoresResidenteList) {
		this.faixasValoresResidenteList = faixasValoresResidenteList;
	}

	public List<FaixasValoresParametrosCalculoFolhasExtrasIndividual> getFaixasValoresParametrosCalculoFolhasExtrasIndividualList() {
		return faixasValoresParametrosCalculoFolhasExtrasIndividualList;
	}

	public void setFaixasValoresParametrosCalculoFolhasExtrasIndividualList(
			List<FaixasValoresParametrosCalculoFolhasExtrasIndividual> faixasValoresParametrosCalculoFolhasExtrasIndividualList) {
		this.faixasValoresParametrosCalculoFolhasExtrasIndividualList = faixasValoresParametrosCalculoFolhasExtrasIndividualList;
	}

	public List<FaixasValoresGpfCedido> getFaixasValoresGpfCedidoList() {
		return faixasValoresGpfCedidoList;
	}

	public void setFaixasValoresGpfCedidoList(List<FaixasValoresGpfCedido> faixasValoresGpfCedidoList) {
		this.faixasValoresGpfCedidoList = faixasValoresGpfCedidoList;
	}
	
	
	
}
