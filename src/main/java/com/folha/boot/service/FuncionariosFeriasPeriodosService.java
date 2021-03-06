package com.folha.boot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.folha.boot.Reposytory.FuncionariosFeriasPeriodosReposytory;
import com.folha.boot.domain.FuncionariosFerias;
import com.folha.boot.domain.FuncionariosFeriasPeriodos;
import com.folha.boot.domain.PessoaFuncionarios;
import com.folha.boot.domain.models.outros.FeriasPeriodosDias;
import com.folha.boot.service.seguranca.UsuarioService;

@Service
@Transactional(readOnly = false)
public class FuncionariosFeriasPeriodosService { 

	@Autowired
	private  FuncionariosFeriasPeriodosReposytory reposytory;
	@Autowired
	private  UsuarioService usuarioService;

	public FuncionariosFeriasPeriodos salvar(FuncionariosFeriasPeriodos funcionariosFeriasPeriodos) {
		return reposytory.save(funcionariosFeriasPeriodos);
	}

	public void editar(FuncionariosFeriasPeriodos funcionariosFeriasPeriodos) {
		reposytory.save(funcionariosFeriasPeriodos);

	}

	public void excluir(Long id) {
		reposytory.deleteById(id);

	}
	
	public void cancelar(FuncionariosFeriasPeriodos funcionariosFeriasPeriodos) {
		reposytory.save(funcionariosFeriasPeriodos);
	}
	
	public void cancelarPorAnoReferencia(FuncionariosFerias funcionariosFerias) {
		List<FuncionariosFeriasPeriodos> lista = buscarFerias(funcionariosFerias);
		for(FuncionariosFeriasPeriodos p : lista) {
			p.setDtCancelamento(new Date());
			p.setIdOperadorCancelamentoFk(usuarioService.pegarOperadorLogado());
			editar(p);
		}
	}
	
	@Transactional(readOnly = true)
	public FuncionariosFeriasPeriodos buscarPorId(Long id) {
		
		return reposytory.findById(id).get();
	}
	
	@Transactional(readOnly = true)
	public List<FuncionariosFeriasPeriodos> buscarTodos() {
		// TODO Auto-generated method stub
		return reposytory.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<FuncionariosFeriasPeriodos> buscarFerias(FuncionariosFerias ferias){
		// TODO Auto-generated method stub
		return reposytory.findByIdFeriasFkOrderByDtInicialAsc(ferias);
	}
	
	@Transactional(readOnly = true)
	public List<FeriasPeriodosDias> buscarPorFuncionarioComDias(PessoaFuncionarios funcionario){
		// TODO Auto-generated method stub
		List<FeriasPeriodosDias> lista = new ArrayList<>();
		List <FuncionariosFeriasPeriodos> listaInicial = reposytory.findByIdFeriasFkIdFuncionarioFkAndDtCancelamentoIsNullOrderByDtFinalDesc(funcionario);
		
		for(FuncionariosFeriasPeriodos p : listaInicial) {
			FeriasPeriodosDias feriasPeriodosDias = new FeriasPeriodosDias();
			feriasPeriodosDias.setId(p.getId());
			feriasPeriodosDias.setAnoReferencia(p.getIdFeriasFk().getAnoReferencia());
			feriasPeriodosDias.setDtAssinatura(p.getDtAssinatura());
			feriasPeriodosDias.setDtFinal(p.getDtFinal());
			feriasPeriodosDias.setDtInicial(p.getDtInicial());
			
			Long dias = 0l;
			if(feriasPeriodosDias.getDtInicial()!=null && feriasPeriodosDias.getDtFinal()!=null ) {
				long momentoInicial = p.getDtInicial().getTime();
				long momentoFinal = p.getDtFinal().getTime();
				dias = (momentoFinal-momentoInicial) / 1000 / 60 / 60 / 24 ;
				dias = dias +1;
			}
			feriasPeriodosDias.setDias(dias);
			
			lista.add(feriasPeriodosDias);
		}
		
		return lista;
	}
	
	
	
	@Transactional(readOnly = true)
	public List<FeriasPeriodosDias> buscarPorFeriasComDias(FuncionariosFerias funcionariosFerias){
		// TODO Auto-generated method stub
		List<FeriasPeriodosDias> lista = new ArrayList<>();
		List <FuncionariosFeriasPeriodos> listaInicial = reposytory.findByIdFeriasFkAndDtCancelamentoIsNullOrderByDtFinalDesc(funcionariosFerias);
		
		for(FuncionariosFeriasPeriodos p : listaInicial) {
			FeriasPeriodosDias feriasPeriodosDias = new FeriasPeriodosDias();
			feriasPeriodosDias.setId(p.getId());
			feriasPeriodosDias.setAnoReferencia(p.getIdFeriasFk().getAnoReferencia());
			feriasPeriodosDias.setDtAssinatura(p.getDtAssinatura());
			feriasPeriodosDias.setDtFinal(p.getDtFinal());
			feriasPeriodosDias.setDtInicial(p.getDtInicial());
			
			Long dias = 0l;
			if(feriasPeriodosDias.getDtInicial()!=null && feriasPeriodosDias.getDtFinal()!=null ) {
				long momentoInicial = p.getDtInicial().getTime();
				long momentoFinal = p.getDtFinal().getTime();
				dias = (momentoFinal-momentoInicial) / 1000 / 60 / 60 / 24 ;
				dias = dias +1;
			}
			feriasPeriodosDias.setDias(dias);
			
			lista.add(feriasPeriodosDias);
		}
		return lista;
	}
	
	
	public Long diasEmFeriasPorAnoReferencia(FuncionariosFerias funcionariosFerias) {
		Long resposta = 0L;
		List <FeriasPeriodosDias> listaInicial = buscarPorFeriasComDias(funcionariosFerias);
		for(FeriasPeriodosDias f: listaInicial) {
			resposta = resposta+f.getDias();
		}
		return resposta;
	}
	
}
