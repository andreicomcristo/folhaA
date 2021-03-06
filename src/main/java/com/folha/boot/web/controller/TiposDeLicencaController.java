package com.folha.boot.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.folha.boot.domain.TiposDeLicenca;
import com.folha.boot.service.TiposDeLicencaService;

@Controller
@RequestMapping("/tiposdelicencas")
public class TiposDeLicencaController {

	@Autowired
	private TiposDeLicencaService service;

	@GetMapping("/cadastrar")
	public String cadastrar(TiposDeLicenca tiposDeLicenca) {		
		return "/tipolicenca/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("tiposDeLicenca", service.buscarTodos());
		return "/tipolicenca/lista"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(TiposDeLicenca tiposDeLicenca, RedirectAttributes attr) {
		service.salvar(tiposDeLicenca);
		attr.addFlashAttribute("success", "Inserido com sucesso.");
		return "redirect:/tiposdelicencas/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("tiposDeLicenca", service.buscarPorId(id));
		return "/tipolicenca/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(TiposDeLicenca tiposDeLicenca, RedirectAttributes attr) {
		service.editar(tiposDeLicenca);
		attr.addFlashAttribute("success", "Editado com sucesso.");
		return "redirect:/tiposdelicencas/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);  
		model.addAttribute("success", "Excluído com sucesso.");
		return listar(model);
	}
	
	@GetMapping("/buscar/nome/tipolicenca")
	public String getPorNome(@RequestParam("descricaoTipoLicenca") String descricaoTipoLicenca, ModelMap model) {		
		model.addAttribute("tiposDeLicenca", service.buscarPorNome(descricaoTipoLicenca.toUpperCase().trim()));
		return "/tipolicenca/lista";
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
	
	
}
