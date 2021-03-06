package com.folha.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.folha.boot.Reposytory.UnidadesReposytory;
import com.folha.boot.domain.Unidades;

@Service
@Transactional(readOnly = false)
public class UnidadesService {

	@Autowired
	private UnidadesReposytory reposytory;
		
	public void salvar(Unidades unidades) {
		// TODO Auto-generated method stub
		reposytory.save(unidades);
	}
	
	public void editar(Unidades unidades) {
		// TODO Auto-generated method stub
		reposytory.save(unidades);
	}
	
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		reposytory.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Unidades buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return reposytory.findById(id).get();
	}

	@Transactional(readOnly = true)
	public List<Unidades> buscarTodos() {
		// TODO Auto-generated method stub
		return reposytory.findByDtCancelamentoIsNullOrderByNomeFantasiaAsc();
	}
	
	public List<Unidades> buscarPorNome(String nomeFantasia) {
		// TODO Auto-generated method stub
		return reposytory.findByNomeFantasiaContainingAndDtCancelamentoIsNullOrderByNomeFantasiaAsc(nomeFantasia);
	}

}
