package com.folha.boot.Reposytory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.folha.boot.domain.Cidades;
import com.folha.boot.domain.Uf;

@Repository
public interface CidadesReposytory extends JpaRepository<Cidades, Long> {

	public List<Cidades> findAllByOrderByNomeCidadeAsc();
	
	public Page<Cidades> findAllByOrderByNomeCidadeAsc(final Pageable page);

	public List<Cidades> findByNomeCidadeContainingOrderByNomeCidadeAsc(String nomeCidade);
	
	public Page<Cidades> findByNomeCidadeContainingOrderByNomeCidadeAsc(String nomeCidade, final Pageable page);
	
	public Page<Cidades> findByIdUfFkOrderByNomeCidadeAsc(Uf uf, final Pageable page);
	
	public List<Cidades> findByIdUfFk(Uf uf);
	
}
