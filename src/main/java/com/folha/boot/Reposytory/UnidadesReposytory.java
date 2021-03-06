package com.folha.boot.Reposytory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.folha.boot.domain.Unidades;

@Repository
public interface UnidadesReposytory extends JpaRepository<Unidades, Long> {

	public List<Unidades> findByDtCancelamentoIsNullOrderByNomeFantasiaAsc();

	public List<Unidades> findByNomeFantasiaContainingAndDtCancelamentoIsNullOrderByNomeFantasiaAsc(String nomeFantasia);
}
