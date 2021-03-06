package com.folha.boot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.folha.boot.Reposytory.PessoaDocumentosRgReposytory;
import com.folha.boot.domain.Pessoa;
import com.folha.boot.domain.PessoaDocumentosRg;

@Service
@Transactional(readOnly = false)
public class PessoaDocumentosRgService {

	@Autowired
	private PessoaDocumentosRgReposytory reposytory;
	
	public void salvar(PessoaDocumentosRg pessoaDocumentosRg) {
		// TODO Auto-generated method stub
		reposytory.save(pessoaDocumentosRg);
	}

	
	public void editar(PessoaDocumentosRg pessoaDocumentosRg) {
		// TODO Auto-generated method stub
		reposytory.save(pessoaDocumentosRg);
	}

	
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		reposytory.deleteById(id);
	}

	@Transactional(readOnly = true)
	
	public PessoaDocumentosRg buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return reposytory.findById(id).get();
	}

	@Transactional(readOnly = true)
	
	public List<PessoaDocumentosRg> buscarTodos() {
		// TODO Auto-generated method stub
		return reposytory.findAll();
	}

	
	public List<PessoaDocumentosRg> buscarPorNumero(String rgNumero) {
		// TODO Auto-generated method stub
		return reposytory.findByRgNumeroContainingOrderByRgNumeroAsc(rgNumero);
	}
	
	
	public List<PessoaDocumentosRg> buscarPorPessoa(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return reposytory.findByIdPessoaFk(pessoa);
	}

}
