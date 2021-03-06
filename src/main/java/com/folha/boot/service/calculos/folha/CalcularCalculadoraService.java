package com.folha.boot.service.calculos.folha;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.folha.boot.domain.AnoMes;
import com.folha.boot.domain.Pessoa;
import com.folha.boot.domain.PessoaFuncionarios;
import com.folha.boot.domain.RubricaNatureza;
import com.folha.boot.domain.RubricaPensao;
import com.folha.boot.domain.RubricaPensaoObs;
import com.folha.boot.domain.RubricaVencimento;
import com.folha.boot.domain.models.calculos.RubricasVencimento;
import com.folha.boot.service.FatorPatronalService;
import com.folha.boot.service.NaoDescontaInssService;
import com.folha.boot.service.PessoaFuncionariosService;
import com.folha.boot.service.RubricaNaturezaService;
import com.folha.boot.service.RubricaPensaoObsService;
import com.folha.boot.service.RubricaPensaoService;
import com.folha.boot.service.RubricaVencimentoService;
import com.folha.boot.service.SalarioMinimoService;
import com.folha.boot.service.TipoBrutoLiquidoService;
import com.folha.boot.service.util.UtilidadesDeCalendarioEEscala;
import com.folha.boot.service.util.UtilidadesMatematicas;

@Service
@Transactional(readOnly = false)
public class CalcularCalculadoraService {
	
	@Autowired
	private  RubricaVencimentoService rubricaVencimentoService;
	@Autowired
	private  CalcularInssService calcularInssService;
	@Autowired
	private  CalcularIrService calcularIrService;
	@Autowired
	private  CalcularBrutoService calcularBrutoService;
	@Autowired
	private RubricaPensaoService rubricaPensaoService;
	@Autowired
	private RubricaPensaoObsService rubricaPensaoObsService;
	@Autowired
	private TipoBrutoLiquidoService tipoBrutoLiquidoService;
	@Autowired
	private FatorPatronalService fatorPatronalService;
	@Autowired
	private SalarioMinimoService salarioMinimoService;
	@Autowired
	private PessoaFuncionariosService pessoaFuncionariosService;
	@Autowired
	private UtilidadesDeCalendarioEEscala utilidadesDeCalendarioEEscala;
	
	
	
	public void calcularTudo(AnoMes anoMes) {
		//List<PessoaFuncionarios> listaFuncionarios = listarFuncionariosComRubrica(anoMes);
		List<Pessoa> listaPessoasComRubricaVencimento = listarPessoasComRubrica(anoMes);
		
		
		Double salarioMimomo = 0.0;
		if(salarioMinimoService.buscarPorMesExato(anoMes).get(0).getValor()!= null) {salarioMimomo = salarioMinimoService.buscarPorMesExato(anoMes).get(0).getValor();}
		 
		
		for(int i=0;i<listaPessoasComRubricaVencimento.size();i++) {
			
			
			//Identificando o Vinculo
			boolean vinculoEfetivo = false;
			List<PessoaFuncionarios> listaFuncionariosVinculo = pessoaFuncionariosService.buscarPorPessoa(listaPessoasComRubricaVencimento.get(i));
			for(PessoaFuncionarios f: listaFuncionariosVinculo) {
				if(f.getIdVinculoAtualFk().getNomeVinculo().equalsIgnoreCase("EFETIVO")  ||  f.getIdVinculoAtualFk().getNomeVinculo().equalsIgnoreCase("CEDIDO") ) {
					vinculoEfetivo = true; break;
				}
			}
			
			
			
			
			Double valorPensao = 0.0;
			
			List<RubricaVencimento> listaVencimentos = buscarRubricasPorPessoa2(anoMes, listaPessoasComRubricaVencimento.get(i));
			
				boolean contemRemuneracaoLiquida = false;
				for(int j=0;j<listaVencimentos.size();j++) {
					if(listaVencimentos.get(j).getIdTipoBrutoLiquidoFk().getNome().equalsIgnoreCase("L")) {contemRemuneracaoLiquida = true;}
				}
				
				
				Double liquidoVantagens=0.0;
				Double liquidoDecontos=0.0;
				Double vantagens=0.0;
				Double descontos=0.0;
				Double pensao = 0.0;
				Double inss=0.0;
				Double ir=0.0;
				Double patronal=0.0;
				
				//Calculando quando só tem verbas brutas.
				if(contemRemuneracaoLiquida==false) {
					//Colocando valores nas variáveis
					for(int j=0;j<listaVencimentos.size();j++) {
						//Vantagens
						if(listaVencimentos.get(j).getIdNaturezaFk().getSigla().equalsIgnoreCase("V")) {
							vantagens = vantagens+listaVencimentos.get(j).getValorBruto();
							liquidoVantagens = liquidoVantagens+listaVencimentos.get(j).getValorLiquido();
						}
						//Descontos
						if(listaVencimentos.get(j).getIdNaturezaFk().getSigla().equalsIgnoreCase("D")) {
							descontos = descontos+listaVencimentos.get(j).getValorBruto();
							liquidoDecontos = liquidoDecontos+listaVencimentos.get(j).getValorLiquido();
						}
					}
				
					
					//Calculando Inss
					inss = calcularInssService.calcularValorInss(vantagens-descontos, anoMes, listaPessoasComRubricaVencimento.get(i));
					
					//Calculando pensao alimenticia
					List<RubricaPensao> listaPensao = rubricaPensaoService.buscarPensoesDoMesAtual( listaPessoasComRubricaVencimento.get(i), anoMes );
					for(int k=0;k<listaPensao.size();k++) {
						if(listaPensao.get(k).getIdEfetuarCalculoSimNaoFk().getSigla().equalsIgnoreCase("S")){
							RubricaPensaoObs r = new RubricaPensaoObs();
							r.setId(null);
							r.setIdAnoMesFk(anoMes);
							r.setIdRubricaPensaoFk(listaPensao.get(k));
							
							//Para calculo sobre o proprio valor depois do INSS
							if(listaPensao.get(k).getIdIncidenciaFk().getSigla().equalsIgnoreCase("A")) {
								valorPensao = listaPensao.get(k).getValor();
								if(valorPensao<=0) {valorPensao = (listaPensao.get(k).getPercentagem()/100)*(vantagens-(descontos+inss)); }
							}
							
							//Para calculo sobre o proprio valor Antes do INSS
							if(listaPensao.get(k).getIdIncidenciaFk().getSigla().equalsIgnoreCase("B")) {
								valorPensao = listaPensao.get(k).getValor();
								if(valorPensao<=0) {valorPensao = (listaPensao.get(k).getPercentagem()/100)*(vantagens-(descontos)); }
							}
							
							//Para calculo sobre o SALARIO MINIMO depois do INSS
							if(listaPensao.get(k).getIdIncidenciaFk().getSigla().equalsIgnoreCase("C")) {
								valorPensao = (listaPensao.get(k).getPercentagem() /100) *salarioMimomo;
							}
							
							//Ajustando Proporçao de dias quando tem data final definida.
							if(listaPensao.get(k).getDtFinal()!=null) {
								Date dtFinalPensao = listaPensao.get(k).getDtFinal();
								if( String.valueOf((dtFinalPensao.getYear()+1900)).equalsIgnoreCase(anoMes.getNomeAnoMes().substring(0, 4))   &&    String.valueOf((dtFinalPensao.getMonth()+1)).equalsIgnoreCase(anoMes.getNomeAnoMes().substring(4, 6))   ) {
									int dias = dtFinalPensao.getDate();
									valorPensao = (valorPensao / utilidadesDeCalendarioEEscala.quantidadeDeDiasNoMes(anoMes.getNomeAnoMes())) * dias   ;
								}
							}
							
							
							if( (vantagens-(descontos+inss+pensao))-valorPensao <0 ) { r.setObservacao("O VALOR PENSAO EM FAVOR DE "+listaPensao.get(k).getNomeBeneficiario()+", CPF: "+listaPensao.get(k).getCpfBeneficiario()+ " NAO DESCONTADO.");  valorPensao = 0.0; }
							
							valorPensao = UtilidadesMatematicas.ajustaValorDecimal(valorPensao, 2);
							r.setValorDescontado(valorPensao);
							pensao = pensao + valorPensao;
							rubricaPensaoObsService.salvar(r);
						}
					}
					
					//Calculando Ir
					ir = calcularIrService.calcularValorIr(vantagens-descontos-pensao-inss, anoMes, listaPessoasComRubricaVencimento.get(i));
					//ir = calcularIrService.valorIr(vantagens-descontos-pensao-inss, anoMes);
					//Calculando patronal
					Double fatorPatronal = 0.0;
					if(!fatorPatronalService.buscarPorMesExato(anoMes).isEmpty()) {
						if(fatorPatronalService.buscarPorMesExato(anoMes).get(0).getFator()!=null) {
							fatorPatronal = fatorPatronalService.buscarPorMesExato(anoMes).get(0).getFator()/100;
						}
					}
					if( vinculoEfetivo == false ) {patronal = (vantagens-descontos)*fatorPatronal; }
					if(inss==0) {patronal = 0.0;}
					
					//Calculando as proporções dos impostos
					for(int j=0;j<listaVencimentos.size();j++) {
						if(listaVencimentos.get(j).getIdNaturezaFk().getSigla().equalsIgnoreCase("V")) {
							double proporcao = listaVencimentos.get(j).getValorLiquido()/liquidoVantagens;
							listaVencimentos.get(j).setValorPrevidencia(inss*proporcao);
							listaVencimentos.get(j).setValorIr(ir*proporcao);
							listaVencimentos.get(j).setValorPatronal(patronal*proporcao);
							listaVencimentos.get(j).setPercentagem(proporcao*100);
						}
					}
				
				}
		
				
				
				
				
				
				//Calculando quando tem alguma verba liquida.
				if(contemRemuneracaoLiquida==true) {
					//Colocando valores nas variáveis
					for(int j=0;j<listaVencimentos.size();j++) {
						//Vantagens
						if(listaVencimentos.get(j).getIdNaturezaFk().getSigla().equalsIgnoreCase("V")) {
							liquidoVantagens = liquidoVantagens+listaVencimentos.get(j).getValorLiquido();
						}
						//Descontos
						if(listaVencimentos.get(j).getIdNaturezaFk().getSigla().equalsIgnoreCase("D")) {
							descontos = descontos+listaVencimentos.get(j).getValorBruto();
							liquidoDecontos = liquidoDecontos+listaVencimentos.get(j).getValorLiquido();
						}
					}
				
					//Calcular bruto total
					vantagens = calcularBrutoService.calcularBruto(liquidoVantagens, listaPessoasComRubricaVencimento.get(i), anoMes);
					
					//Calculando Inss
					inss = calcularInssService.calcularValorInss(vantagens-descontos, anoMes, listaPessoasComRubricaVencimento.get(i));
					
					//Calculando pensao alimenticia
					List<RubricaPensao> listaPensao = rubricaPensaoService.buscarPensoesDoMesAtual( listaPessoasComRubricaVencimento.get(i), anoMes );
					for(int k=0;k<listaPensao.size();k++) {
						if(listaPensao.get(k).getIdEfetuarCalculoSimNaoFk().getSigla().equalsIgnoreCase("S")){
							RubricaPensaoObs r = new RubricaPensaoObs();
							r.setId(null);
							r.setIdAnoMesFk(anoMes);
							r.setIdRubricaPensaoFk(listaPensao.get(k));
							
							//Para calculo sobre o proprio valor depois do INSS
							if(listaPensao.get(k).getIdIncidenciaFk().getSigla().equalsIgnoreCase("A")) {
								valorPensao = listaPensao.get(k).getValor();
								if(valorPensao<=0) {valorPensao = (listaPensao.get(k).getPercentagem()/100)*(vantagens-(descontos+inss)); }
							}
							
							//Para calculo sobre o proprio valor Antes do INSS
							if(listaPensao.get(k).getIdIncidenciaFk().getSigla().equalsIgnoreCase("B")) {
								valorPensao = listaPensao.get(k).getValor();
								if(valorPensao<=0) {valorPensao = (listaPensao.get(k).getPercentagem()/100)*(vantagens-(descontos)); }
							}
							
							//Para calculo sobre o SALARIO MINIMO depois do INSS
							if(listaPensao.get(k).getIdIncidenciaFk().getSigla().equalsIgnoreCase("C")) {
								valorPensao = (listaPensao.get(k).getPercentagem() /100) *salarioMimomo;
							}
							
							//Ajustando Proporçao de dias quando tem data final definida.
							if(listaPensao.get(k).getDtFinal()!=null) {
								Date dtFinalPensao = listaPensao.get(k).getDtFinal();
								if( String.valueOf((dtFinalPensao.getYear()+1900)).equalsIgnoreCase(anoMes.getNomeAnoMes().substring(0, 4))   &&    String.valueOf((dtFinalPensao.getMonth()+1)).equalsIgnoreCase(anoMes.getNomeAnoMes().substring(4, 6))   ) {
									int dias = dtFinalPensao.getDate();
									valorPensao = (valorPensao / utilidadesDeCalendarioEEscala.quantidadeDeDiasNoMes(anoMes.getNomeAnoMes())) * dias   ;
								}
							}
							
							
							if( (vantagens-(descontos+inss+pensao))-valorPensao <0 ) { r.setObservacao("O VALOR PENSAO EM FAVOR DE "+listaPensao.get(k).getNomeBeneficiario()+", CPF: "+listaPensao.get(k).getCpfBeneficiario()+ " NAO DESCONTADO.");  valorPensao = 0.0; }
							
							valorPensao = UtilidadesMatematicas.ajustaValorDecimal(valorPensao, 2);
							r.setValorDescontado(valorPensao);
							pensao = pensao + valorPensao;
							rubricaPensaoObsService.salvar(r);
						}
					}
					
					//Calculando Ir
					ir = calcularIrService.calcularValorIr(vantagens-descontos-pensao-inss, anoMes, listaPessoasComRubricaVencimento.get(i));
					//ir = calcularIrService.valorIr(vantagens-descontos-pensao-inss, anoMes);
					//Calculando patronal
					Double fatorPatronal = 0.0;
					if(!fatorPatronalService.buscarPorMesExato(anoMes).isEmpty()) {
						if(fatorPatronalService.buscarPorMesExato(anoMes).get(0).getFator()!=null) {
							fatorPatronal = fatorPatronalService.buscarPorMesExato(anoMes).get(0).getFator()/100;
						}
					}
					if(vinculoEfetivo == false) {patronal = (vantagens-descontos)*fatorPatronal; }
					if(inss==0) {patronal = 0.0;}
					
					//Calculando as proporções dos impostos
					for(int j=0;j<listaVencimentos.size();j++) {
						if(listaVencimentos.get(j).getIdNaturezaFk().getSigla().equalsIgnoreCase("V")) {
							double proporcao = listaVencimentos.get(j).getValorLiquido()/liquidoVantagens;
							listaVencimentos.get(j).setValorBruto(vantagens*proporcao); 
							listaVencimentos.get(j).setValorPrevidencia(inss*proporcao);
							listaVencimentos.get(j).setValorIr(ir*proporcao);
							listaVencimentos.get(j).setValorPatronal(patronal*proporcao);
							listaVencimentos.get(j).setPercentagem(proporcao*100);
						}
					}
				
				}
				
				
				
				
				
				
		}
	
		//Recolocando valor bruto nas verbas que sao liquidas
		reconstruirBrutoDasVerbasOriginalmenteLiquidas(anoMes);
		//Colocando as proporcoes dos descontos nas rubricas
		colocarProporcoesDescontosNasRubricas(anoMes);
		//Colocando as proporcoes das pensoes nas rubricas
		colocarProporcoesPensoesNasRubricas(anoMes);
		//Recalculando valor liquido
		recalcularLiquidoDasRubricasDeVantagens(anoMes);
		
		//Arredondando os Valores
		arredondarValoresVencimentos(anoMes);
	
	}
	
	
	
	public void colocarProporcoesDescontosNasRubricas(AnoMes anoMes) {
		List<RubricaVencimento> listaDescontos =  buscarRubricasPorAnoMesDescontoOuVantagem(anoMes, "D");
		for(int i=0;i<listaDescontos.size();i++) {
			List<RubricaVencimento> listaVantagem =  buscarRubricasPorAnoMesDescontoOuVantagemPorFuncionario(anoMes, "V", listaDescontos.get(i).getIdFuncionarioFk());
				for(int j=0;j<listaVantagem.size();j++) {
					listaVantagem.get(j).setDescontoProp(listaVantagem.get(j).getDescontoProp() + (listaDescontos.get(i).getValorLiquido()*(listaVantagem.get(j).getPercentagem()/100) ) );
				}
		}
	}
	
	
	
	public void colocarProporcoesPensoesNasRubricas(AnoMes anoMes) {
		List<RubricaPensaoObs> listaPensao =  rubricaPensaoObsService.buscarPorMes(anoMes);
		for(int i=0;i<listaPensao.size();i++) {
			List<RubricaVencimento> listaVantagem =  buscarRubricasPorAnoMesDescontoOuVantagemPorPessoa(anoMes, "V", listaPensao.get(i).getIdRubricaPensaoFk().getIdPessoaFk());
				for(int j=0;j<listaVantagem.size();j++) {
					listaVantagem.get(j).setPensaoProp(listaVantagem.get(j).getPensaoProp() + (listaPensao.get(i).getValorDescontado()*(listaVantagem.get(j).getPercentagem()/100) ) );
				}
		}
	}
	
	public void recalcularLiquidoDasRubricasDeVantagens(AnoMes anoMes) {
		List<RubricaVencimento> listaVantagem =  buscarRubricasPorAnoMesDescontoOuVantagem(anoMes, "V");
			for(int j=0;j<listaVantagem.size();j++) {
				listaVantagem.get(j).setValorLiquido( listaVantagem.get(j).getValorBruto() - ( listaVantagem.get(j).getDescontoProp()+listaVantagem.get(j).getPensaoProp()+listaVantagem.get(j).getValorIr()+listaVantagem.get(j).getValorPrevidencia() ) );
			}
		
	}
	
	
	public void reconstruirBrutoDasVerbasOriginalmenteLiquidas(AnoMes anoMes) {
		List<RubricaVencimento> lista =  buscarRubricasPorAnoMes(anoMes);
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getIdTipoBrutoLiquidoFk().getNome().equalsIgnoreCase("L")) {
				lista.get(i).setValorBruto(lista.get(i).getValorLiquido()+lista.get(i).getValorIr()+lista.get(i).getValorPrevidencia());
			}
		}
	}
	
	
	
	
	
	public void arredondarValoresVencimentos(AnoMes anoMes) {
		List<RubricaVencimento> lista =  buscarRubricasPorAnoMes(anoMes);
		for(int i=0;i<lista.size();i++) {
			//Restituindo IR dos residentes
			if(lista.get(i).getCodigo().equalsIgnoreCase("RESIDENTE")) {lista.get(i).setValorLiquido(lista.get(i).getValorLiquido()+lista.get(i).getValorIr()); lista.get(i).setValorIr(0.0);}
			//Arredondando Valores
			lista.get(i).setValorBruto(UtilidadesMatematicas.ajustaValorDecimal(lista.get(i).getValorBruto(), 2));
			lista.get(i).setValorIr(UtilidadesMatematicas.ajustaValorDecimal(lista.get(i).getValorIr(), 2));
			lista.get(i).setValorLiquido(UtilidadesMatematicas.ajustaValorDecimal(lista.get(i).getValorLiquido(), 2));
			lista.get(i).setValorPatronal(UtilidadesMatematicas.ajustaValorDecimal(lista.get(i).getValorPatronal(), 2));
			lista.get(i).setValorPrevidencia(UtilidadesMatematicas.ajustaValorDecimal(lista.get(i).getValorPrevidencia(), 2));
			lista.get(i).setPercentagem(UtilidadesMatematicas.ajustaValorDecimal(lista.get(i).getPercentagem(), 4));
			lista.get(i).setDescontoProp(UtilidadesMatematicas.ajustaValorDecimal(lista.get(i).getDescontoProp(), 2));
			lista.get(i).setPensaoProp(UtilidadesMatematicas.ajustaValorDecimal(lista.get(i).getPensaoProp(), 2));
		}
	}
	
	
	public List<PessoaFuncionarios> listarFuncionariosComRubrica(AnoMes anoMes){
		List<PessoaFuncionarios> lista = new ArrayList<>();
		
		List<RubricaVencimento> listaVencimentos = rubricaVencimentoService.buscarPorMes(anoMes);
		for(int i=0; i<listaVencimentos.size();i++) {
			if(!lista.contains(listaVencimentos.get(i).getIdFuncionarioFk())) {
				lista.add(listaVencimentos.get(i).getIdFuncionarioFk());
			}
		}
		return lista;
	}
	
	
	public List<Pessoa> listarPessoasComRubrica(AnoMes anoMes){
		List<Pessoa> lista = new ArrayList<>();
		
		List<RubricaVencimento> listaVencimentos = rubricaVencimentoService.buscarPorMes(anoMes);
		for(int i=0; i<listaVencimentos.size();i++) {
			if(!lista.contains(listaVencimentos.get(i).getIdFuncionarioFk().getIdPessoaFk())) {
				lista.add(listaVencimentos.get(i).getIdFuncionarioFk().getIdPessoaFk());
			}
		}
		return lista;
	}
	
	
	
	public List<RubricaVencimento> buscarRubricasPorPessoa(AnoMes anoMes, PessoaFuncionarios funcionario){
		return rubricaVencimentoService.buscarPorMesEFuncionario(anoMes, funcionario);
	}
	
	public List<RubricaVencimento> buscarRubricasPorPessoa2(AnoMes anoMes, Pessoa pessoa){
		return rubricaVencimentoService.buscarPorMesEPessoa(anoMes, pessoa);
	}
	
	public List<RubricaVencimento> buscarRubricasPorAnoMes(AnoMes anoMes){
		return rubricaVencimentoService.buscarPorMes(anoMes);
	}
	
	public List<RubricaVencimento> buscarRubricasPorAnoMesDescontoOuVantagem(AnoMes anoMes, String natureza){
		return rubricaVencimentoService.buscarPorMesDescontoOuVantagem(anoMes, natureza );
	}
	
	public List<RubricaVencimento> buscarRubricasPorAnoMesDescontoOuVantagemPorFuncionario(AnoMes anoMes, String natureza, PessoaFuncionarios funcionario){
		return rubricaVencimentoService.buscarPorMesDescontoOuVantagemPorFuncionario(anoMes, natureza, funcionario );
	}
	
	public List<RubricaVencimento> buscarRubricasPorAnoMesDescontoOuVantagemPorPessoa(AnoMes anoMes, String natureza, Pessoa pessoa){
		return rubricaVencimentoService.buscarPorMesDescontoOuVantagemPorPessoa(anoMes, natureza, pessoa );
	}
	
	
}
	