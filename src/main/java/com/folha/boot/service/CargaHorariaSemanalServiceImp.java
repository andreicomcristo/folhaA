package com.folha.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.folha.boot.Reposytory.CargaHorariaSemanalReposytory;
import com.folha.boot.domain.CargaHorariaSemanal;

@Service
@Transactional(readOnly = false)
public class CargaHorariaSemanalServiceImp implements CargaHorariaSemanalService{

	@Autowired
	private CargaHorariaSemanalReposytory reposytory;
	
	@Override
	public void salvar(CargaHorariaSemanal cargaHorariaSemanal) {
		// TODO Auto-generated method stub
		reposytory.save(cargaHorariaSemanal);
		
	}

	@Override
	public void editar(CargaHorariaSemanal cargaHorariaSemanal) {
		// TODO Auto-generated method stub
		reposytory.save(cargaHorariaSemanal);
		
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		reposytory.deleteById(id);
		
	}
	@Transactional(readOnly = true)
	@Override
	public CargaHorariaSemanal buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return reposytory.findById(id).get();
	}
	@Transactional(readOnly = true)
	@Override
	public List<CargaHorariaSemanal> buscarTodos() {
		// TODO Auto-generated method stub
		return reposytory.findAll();
	}

}