package com.folha.boot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.folha.boot.Reposytory.PaisesReposytoty;
import com.folha.boot.domain.Paises;

@Service
@Transactional(readOnly = false)
public class PaisesSevice {
	
	@Autowired
	private PaisesReposytoty reposytory;
	
	
	public void salvar(Paises paises) {
		// TODO Auto-generated method stub
		reposytory.save(paises);
	}

	
	public void editar(Paises paises) {
		// TODO Auto-generated method stub
		reposytory.save(paises);
	}

	
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		reposytory.deleteById(id);
	}

	@Transactional(readOnly = true)
	
	public Paises buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return reposytory.findById(id).get();
	}

	@Transactional(readOnly = true)
	
	public List<Paises> buscarTodos() {
		// TODO Auto-generated method stub
		return reposytory.findAllByOrderByNomePaisAsc();
	}

	
	public List<Paises> buscarPorNome(String nomePais) {
		return reposytory.findByNomePaisContainingOrderByNomePaisAsc(nomePais);
	}

}
