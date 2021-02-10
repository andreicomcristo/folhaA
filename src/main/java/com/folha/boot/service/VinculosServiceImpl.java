package com.folha.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.folha.boot.Reposytory.VinculosReposytory;
import com.folha.boot.domain.Vinculos;

@Service
@Transactional(readOnly = false)
public class VinculosServiceImpl implements VinculosService{

	@Autowired
	private VinculosReposytory reposytory;
	
	@Override
	public void salvar(Vinculos vinculos) {
		// TODO Auto-generated method stub
		reposytory.save(vinculos);
	}

	@Override
	public void editar(Vinculos vinculos) {
		// TODO Auto-generated method stub
		reposytory.save(vinculos);
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		reposytory.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Vinculos buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return reposytory.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Vinculos> buscarTodos() {
		// TODO Auto-generated method stub
		return reposytory.findAll();
	}

}