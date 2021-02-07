package com.folha.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.folha.boot.Reposytory.PessoaDocumentosConselhoReposytory;
import com.folha.boot.domain.PessoaDocumentosConselho;

@Service
@Transactional(readOnly = false)
public class PessoaDocumentosConselhoServiceImpl implements PessoaDocumentosConselhoService {

	@Autowired
	private PessoaDocumentosConselhoReposytory reposytory;
	
	@Override
	public void salvar(PessoaDocumentosConselho pessoaDocumentosConselho) {
		// TODO Auto-generated method stub
		reposytory.save(pessoaDocumentosConselho);
	}

	@Override
	public void editar(PessoaDocumentosConselho pessoaDocumentosConselho) {
		// TODO Auto-generated method stub
		reposytory.save(pessoaDocumentosConselho);
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		reposytory.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public PessoaDocumentosConselho buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return reposytory.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<PessoaDocumentosConselho> buscarTodos() {
		// TODO Auto-generated method stub
		return reposytory.findAll();
	}

}