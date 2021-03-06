package com.folha.boot.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.folha.boot.domain.AnoMes;
import com.folha.boot.domain.Bancos;
import com.folha.boot.domain.Pessoa;
import com.folha.boot.domain.PessoaFuncionarios;
import com.folha.boot.domain.RubricaPensao;
import com.folha.boot.domain.RubricaPensaoDependente;
import com.folha.boot.domain.RubricaPensaoIncidencia;
import com.folha.boot.domain.SimNao;
import com.folha.boot.service.AnoMesService;
import com.folha.boot.service.BancosService;
import com.folha.boot.service.CargaHorariaSemanalService;
import com.folha.boot.service.ClassesCarreiraService;
import com.folha.boot.service.FonteService;
import com.folha.boot.service.PessoaFuncionariosService;
import com.folha.boot.service.PessoaService;
import com.folha.boot.service.RubricaPensaoDependenteService;
import com.folha.boot.service.RubricaPensaoIncidenciaService;
import com.folha.boot.service.RubricaPensaoService;
import com.folha.boot.service.SimNaoService;
import com.folha.boot.service.TipoBrutoLiquidoService;
import com.folha.boot.service.seguranca.UsuarioService;

@Controller
@RequestMapping("/rubricaPensao")
public class RubricaPensaoController {

	String ultimoNome = ""; 
	String ultimaBuscaNome = ""; 
	
	@Autowired
	private RubricaPensaoService service;
	@Autowired
	private RubricaPensaoDependenteService dependenteService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private BancosService bancosService;
	@Autowired
	private ClassesCarreiraService classesCarreiraService;
	@Autowired
	private CargaHorariaSemanalService cargaHorariaSemanalService;
	@Autowired
	private AnoMesService anoMesService;
	@Autowired
	private RubricaPensaoIncidenciaService rubricaPensaoIncidenciaService;
	@Autowired
	private SimNaoService simNaoService;
	@Autowired
	private TipoBrutoLiquidoService tipoBrutoLiquidoService;
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private PessoaFuncionariosService pessoaFuncionariosService;
	@Autowired
    ObjectFactory<HttpSession> httpSessionFactory;
	
	/*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	Paginação de funcionários */
	@GetMapping("/paginar/funcionarios/{pageNo}")
	public String getPorNomePaginadoInclusao(@PathVariable(value = "pageNo") int pageNo, ModelMap model) {

		if ((ultimaBuscaNome.equals(""))) {
			return "redirect:/rubricaPensao/funcionarios/listar/{pageNo}";
		} else {
			if (!ultimaBuscaNome.equals("")) {
				return this.findPaginatedFuncionario(pageNo, ultimaBuscaNome, model);
			} else {
				return "redirect:/rubricaPensao/funcionarios/listar/{pageNo}";
			}
		}
	}

	@GetMapping("/funcionarios/listar")
	public String listarFuncionarios(ModelMap model) {
		ultimaBuscaNome = "";
		return this.findPaginatedFuncionario(1, model);
	}

	@GetMapping("/funcionarios/listar/{pageNo}")
	public String findPaginatedFuncionario(@PathVariable(value = "pageNo") int pageNo, ModelMap model) {
		int pageSeze = 50;
		Page<PessoaFuncionarios> page = pessoaFuncionariosService.findPaginatedDeTodasAsUnidades(pageNo, pageSeze,
				"ATIVO");
		List<PessoaFuncionarios> listaFuncionarios = page.getContent();
		return paginarFuncionario(pageNo, page, listaFuncionarios, model);
	}

	public String paginarFuncionario(int pageNo, Page<PessoaFuncionarios> page, List<PessoaFuncionarios> lista,
			ModelMap model) {
		model.addAttribute("currentePage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listaFuncionarios", lista);
		return "/rubricaPensao/listafuncionario";
	}

	@GetMapping("/buscar/funcionarios/nome")
	public String getPorNomeFuncionario(@RequestParam("nome") String nome, ModelMap model) {
		this.ultimaBuscaNome = nome;
		// this.ultimaBuscaTurma = null;
		return this.findPaginatedFuncionario(1, nome, model);
	}

	public String findPaginatedFuncionario(@PathVariable(value = "pageNo") int pageNo, String nome, ModelMap model) {
		int pageSeze = 50;
		Page<PessoaFuncionarios> page = pessoaFuncionariosService.findPaginatedNomeDeTodasAsUnidades(pageNo, pageSeze,
				"ATIVO", nome);
		List<PessoaFuncionarios> lista = page.getContent();
		// ultimaBuscaNome = "";
		// ultimaBuscaTurma = null;
		return paginarFuncionario(pageNo, page, lista, model);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* Fim da Paginação de funcionários */

	/*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	Paginação de RubricasPensão */
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		this.ultimoNome = "";
		return this.findPaginated(1, model);
	}

	@GetMapping("/listar/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, ModelMap model) {
		int pageSeze = 10;
		Page<RubricaPensao> page = service.findPaginated(pageNo, pageSeze);
		List<RubricaPensao> listaCidades = page.getContent();
		return paginar(pageNo, page, listaCidades, model);
	}
	
	public String findPaginatedNome(@PathVariable(value = "pageNo") int pageNo, String nome, ModelMap model) {
		int pageSeze = 10;
		Page<RubricaPensao> page = service.findPaginatedNome(pageNo, pageSeze, nome);
		List<RubricaPensao> lista = page.getContent();
		return paginar(pageNo, page, lista, model);
	}

	public String paginar(int pageNo, Page<RubricaPensao> page, List<RubricaPensao> lista, ModelMap model) {
		model.addAttribute("currentePage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("rubricaPensao", lista);
		return "/rubricaPensao/lista";
	}

	
	@GetMapping("/buscar/nome/nome")
	public String getPorNomeNome(@RequestParam("nome") String nome, ModelMap model) {
		this.ultimoNome = nome;
		return this.findPaginatedNome(1, nome, model);
	}

	@GetMapping("/paginar/{pageNo}")
	public String getPorCnesPaginado(@PathVariable(value = "pageNo") int pageNo, ModelMap model) {
		if (pageNo < 1) {
			pageNo = 1;
		}
		if ( (ultimoNome.equals(""))) {
			return "redirect:/rubricaPensao/listar/{pageNo}";
		} else {
				return this.findPaginatedNome(pageNo, ultimoNome, model);
		}

	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* Fim da Paginação de RubricasPensão */
	
	//Recebe o id do funcionário da tela de lista de funcionários para encontrar a pessoa correspondente
	@GetMapping("/funcionario/{id}")
	public String cadastrarPensao(@PathVariable("id") Long id, Long idPessoa) {	
		idPessoa = pessoaFuncionariosService.buscarPorId(id).getIdPessoaFk().getId();
		//Guarda o id de Pessoa na Session
		HttpSession session = httpSessionFactory.getObject();
        session.setAttribute("idPessoa", idPessoa  );     
		return "redirect:/rubricaPensao/pessoa/cadastrar";				
	}

	@GetMapping("/pessoa/cadastrar")
	public String cadastrarPessoaPensao(RubricaPensao rubricaPensao,  ModelMap model) {
		//relaciona as penssões a pessoa
		Pessoa pessoa = pessoaService.buscarPorId(getIdPessoaSession());
		rubricaPensao.setIdPessoaFk(pessoa);						
		model.addAttribute("pessoa", pessoa); 
		model.addAttribute("pensao", service.buscarPorPessoa(pessoa));		
		return "/rubricaPensao/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(RubricaPensao rubricaPensao, RedirectAttributes attr) {

		if (rubricaPensao.getValor() == null) { 
			rubricaPensao.setValor(0.0);
		}
		if (rubricaPensao.getPercentagem() == null) {
			rubricaPensao.setPercentagem(0.0);
		}	
		service.salvar(rubricaPensao);
		attr.addFlashAttribute("success", "Inserido com sucesso.");
		return "redirect:/rubricaPensao/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {		
		//Pessoa pessoa = service.buscarPorId(id).getIdPessoaFk();		
		// ENVIANDO O OBJETO INTEIRO PARA O HTML EM VEZ DE IR AS PARTES DELE
		RubricaPensao rubricaPensao = service.buscarPorId(id);
		Pessoa pessoa = rubricaPensao.getIdPessoaFk();
		model.addAttribute("rubricaPensao", rubricaPensao);
		model.addAttribute("pessoa", pessoa);
		model.addAttribute("pensao", rubricaPensao);
		return "/rubricaPensao/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(RubricaPensao rubricaPensao, RedirectAttributes attr) {

		if (rubricaPensao.getValor() == null) {
			rubricaPensao.setValor(0.0);
		}
		if (rubricaPensao.getPercentagem() == null) {
			rubricaPensao.setPercentagem(0.0);
		}

		service.editar(rubricaPensao);
		attr.addFlashAttribute("success", "Editado com sucesso.");
		return "redirect:/rubricaPensao/listar";
	}
	
	@GetMapping("/cancelar/{id}")
	public String cancelar(@PathVariable("id") Long id, ModelMap model) {
		RubricaPensao rubricaPensao = service.buscarPorId(id);
		//Pessoa pessoa = rubricaPensao.getIdPessoaFk();
		rubricaPensao.setDtCancelamento(new Date());
		rubricaPensao.setIdOperadorCancelamentoFk(usuarioService.pegarOperadorLogado());
		service.salvar(rubricaPensao);
		//model.addAttribute("success", "Excluído com sucesso.");
		//model.addAttribute("rubricaPensao", rubricaPensao);
		//model.addAttribute("pessoa", pessoa);
		//model.addAttribute("pensao", rubricaPensao);		
		return "redirect:/rubricaPensao/listar";
	}	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Controle dos dependentes das pensões
	
	@GetMapping("/dependente/cadastrar/{id}")
	public String cadastrarDependente(@PathVariable("id") Long id, RubricaPensaoDependente rubricaPensaoDependente, ModelMap model) {
		RubricaPensao rubricaPensao = service.buscarPorId(id);
		//relaciona as penssões a pessoa
		rubricaPensaoDependente.setIdRubricaPensaoFk(rubricaPensao);		
		model.addAttribute("pensao", rubricaPensao); 
		model.addAttribute("dependentes",dependenteService.buscarPensao(rubricaPensao));
		return "/rubricaPensaoDependente/cadastro";
	}
	
	@PostMapping("/dependentes/salvar")
	public String salvar(RubricaPensaoDependente rubricaPensaoDependente , RedirectAttributes attr) {		
		dependenteService.salvar(rubricaPensaoDependente);
		attr.addFlashAttribute("success", "Inserido com sucesso.");
		return "redirect:/rubricaPensao/dependente/cadastrar/" + rubricaPensaoDependente.getIdRubricaPensaoFk().getId();
	}
	
	@GetMapping("/dependentes/editar/{id}")
	public String dependentesPreEditar(@PathVariable("id") Long id, ModelMap model) {				
		RubricaPensaoDependente rubricaPensaoDependente = dependenteService.buscarPorId(id);
		RubricaPensao rubricaPensao = rubricaPensaoDependente.getIdRubricaPensaoFk();		
		model.addAttribute("rubricaPensaoDependente", rubricaPensaoDependente);
		model.addAttribute("dependentes", rubricaPensaoDependente);
		model.addAttribute("pensao", rubricaPensao);
		return "/rubricaPensaoDependente/cadastro";
	}
	
	@PostMapping("/dependentes/editar")
	public String dependentesEditar(RubricaPensaoDependente rubricaPensaoDependente, RedirectAttributes attr) {
		dependenteService.editar(rubricaPensaoDependente);
		attr.addFlashAttribute("success", "Editado com sucesso.");
		return "redirect:/rubricaPensao/listar";
	}
	/*@PostMapping("/salvar/{id}")
	public String salvar(@PathVariable("id") Long id, RubricaPensao rubricaPensao, RedirectAttributes attr) {

		if (rubricaPensao.getValor() == null) { 
			rubricaPensao.setValor(0.0);
		}
		if (rubricaPensao.getPercentagem() == null) {
			rubricaPensao.setPercentagem(0.0);
		}

		service.salvar(rubricaPensao);
		attr.addFlashAttribute("success", "Inserido com sucesso.");
		return "redirect:/rubricaPensao/cadastrar/" + id;
	}*/
	

	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("cnesUnidade") String nome, ModelMap model) {
		model.addAttribute("rubricaPensao", service.buscarPorNome(nome.toUpperCase().trim()));
		return "/rubricaPensao/lista";
	}

	@GetMapping("/exporta/excel")
	public void downloadExcel(HttpServletResponse response, ModelMap model) throws IOException {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=dados.xlsx");
		ByteArrayInputStream stream = service.exportarExcel(service.buscarTodos());
		IOUtils.copy(stream, response.getOutputStream());
	}

	@GetMapping(value = "/exporta/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> employeeReports(HttpServletResponse response) throws IOException {
		ByteArrayInputStream bis = service.exportarPdf(service.buscarTodos());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=dados.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
	

	@ModelAttribute("idBancoFk")
	public List<Bancos> getIdBancoFk() {
		return bancosService.buscarTodos();
	}

	@ModelAttribute("idPessoaFk")
	public List<Pessoa> getIdPessoaFk() {
		return pessoaService.buscarTodos();
	}

	@ModelAttribute("idFuncionarioFk")
	public List<PessoaFuncionarios> getPessoaFuncionarios() {
		return pessoaFuncionariosService.buscarTodos();
	}
	
	@ModelAttribute("idIncidenciaFk")
	public List<RubricaPensaoIncidencia> getIdIncidenciaFk() {
		return rubricaPensaoIncidenciaService.buscarTodos();
	}
	
	@ModelAttribute("idEfetuarCalculoSimNaoFk")
	public List<SimNao> getIdEfetuarCalculoSimNaoFk() {
		return simNaoService.buscarTodos();
	}

	@Autowired
	HttpServletRequest request;

	@ModelAttribute("nomeOperadorLogado")
	public String operadorLogado() {
		return request.getSession().getAttribute("operador").toString();
	}

	@ModelAttribute("nomeUnidadeLogada")
	public String unidadeLogada() {
		return request.getSession().getAttribute("unidade").toString();
	}

	//Recupera um valor da Session
	public Long getIdPessoaSession() {
		return Long.valueOf(request.getSession().getAttribute("idPessoa").toString()) ;
	}
}
