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

import com.folha.boot.domain.Conselhos;
import com.folha.boot.service.ConselhosServices;

@Controller
@RequestMapping("/conselhos")
public class ConselhosController {

	@Autowired
	private ConselhosServices service;

	@GetMapping("/cadastrar")
	public String cadastrar(Conselhos conselho) {
		return "/conselho/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("conselhos", service.buscarTodos());
		return "/conselho/lista"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(Conselhos conselho, RedirectAttributes attr) {
		service.salvar(conselho);
		attr.addFlashAttribute("success", "Inserido com sucesso.");
		return "redirect:/conselhos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("conselhos", service.buscarPorId(id));
		return "/conselho/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Conselhos conselho, RedirectAttributes attr) {
		service.editar(conselho);
		attr.addFlashAttribute("success", "Editado com sucesso.");
		return "redirect:/conselhos/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);  
		model.addAttribute("success", "Excluído com sucesso.");
		return listar(model);
	}
	
	@GetMapping("/buscar/descricao/conselho")
	public String getPorNome(@RequestParam("nomeConselho") String nomeConselho, ModelMap model) {		
		model.addAttribute("conselhos", service.buscarPorNome(nomeConselho.toUpperCase().trim()));
		return "/conselho/lista";
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
