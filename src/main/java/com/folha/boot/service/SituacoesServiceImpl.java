package com.folha.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.folha.boot.Reposytory.SituacoesReposytory;
import com.folha.boot.domain.Situacoes;

@Service
@Transactional(readOnly = false)
public class SituacoesServiceImpl implements SituacoesService {

	@Autowired
	private SituacoesReposytory reposytory;
	@Override
	public void salvar(Situacoes situacoes) {
		// TODO Auto-generated method stub
		reposytory.save(situacoes);
	}

	@Override
	public void editar(Situacoes situacoes) {
		// TODO Auto-generated method stub
		reposytory.save(situacoes);
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		reposytory.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Situacoes buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return reposytory.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Situacoes> buscarTodos() {
		// TODO Auto-generated method stub
		return reposytory.findAll();
	}

}