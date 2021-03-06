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

import com.folha.boot.domain.TiposDeFiliacao;
import com.folha.boot.service.TiposDeFiliacaoService;

@Controller
@RequestMapping("/tiposdefiliacoes")
public class TiposDeFiliacaoController {

	@Autowired
	private TiposDeFiliacaoService service;

	@GetMapping("/cadastrar")
	public String cadastrar(TiposDeFiliacao tiposDeFiliacao) {		
		return "/tipofiliacao/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("tiposDeFiliacao", service.buscarTodos());
		return "/tipofiliacao/lista"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(TiposDeFiliacao tiposDeFiliacao, RedirectAttributes attr) {
		service.salvar(tiposDeFiliacao);
		attr.addFlashAttribute("success", "Inserido com sucesso.");
		return "redirect:/tipostefiliacoes/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("tiposDeFiliacao", service.buscarPorId(id));
		return "/tipofiliacao/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(TiposDeFiliacao tiposDeFiliacao, RedirectAttributes attr) {
		service.editar(tiposDeFiliacao);
		attr.addFlashAttribute("success", "Editado com sucesso.");
		return "redirect:/tipostefiliacoes/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);  
		model.addAttribute("success", "Excluído com sucesso.");
		return listar(model);
	}
	
	@GetMapping("/buscar/nome/tipofiliacao")
	public String getPorNome(@RequestParam("nomeTipoFiliacao") String nomeTipoFiliacao, ModelMap model) {		
		model.addAttribute("tiposDeFiliacao", service.buscarPorNome(nomeTipoFiliacao.toUpperCase().trim()));
		return "/tipofiliacao/lista";
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
