package com.folha.boot.Reposytory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.folha.boot.domain.NiveisCargo;

@Repository
public interface NiveisCargoReposytory extends JpaRepository<NiveisCargo, Long> {
	
	public List<NiveisCargo> findAllByOrderByNomeNivelCargoAsc();
	
	public List<NiveisCargo> findByNomeNivelCargoContainingOrderByNomeNivelCargoAsc(String nomeNivelCargo);
	
}
