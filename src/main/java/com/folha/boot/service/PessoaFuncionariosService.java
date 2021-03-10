package com.folha.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.folha.boot.Reposytory.PessoaFuncionariosReposytory;
import com.folha.boot.domain.PessoaFuncionarios;

@Service
@Transactional(readOnly = false)
public class PessoaFuncionariosService {

	@Autowired
	private PessoaFuncionariosReposytory reposytory;
	
	public void salvar(PessoaFuncionarios pessoaFuncionarios) {
		// TODO Auto-generated method stub
		reposytory.save(pessoaFuncionarios);
	}

	
	public void editar(PessoaFuncionarios pessoaFuncionarios) {
		// TODO Auto-generated method stub
		reposytory.save(pessoaFuncionarios);
	}

	
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		reposytory.deleteById(id);
	}

	@Transactional(readOnly = true)
	
	public PessoaFuncionarios buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return reposytory.findById(id).get();
	}

	@Transactional(readOnly = true)
	
	public List<PessoaFuncionarios> buscarTodos() {
		// TODO Auto-generated method stub
		return reposytory.findAll();
	}

	
	public List<PessoaFuncionarios> buscarPorMatricula(String matricula) {
		// TODO Auto-generated method stub
		return reposytory.findByMatriculaContainingOrderByMatriculaAsc(matricula);
	}

}
