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

import com.folha.boot.domain.ClassesCarreira;
import com.folha.boot.service.ClassesCarreiraService;

@Controller
@RequestMapping("/classes/carreira")
public class ClassesCarreiraController {

	@Autowired
	private ClassesCarreiraService service;

	@GetMapping("/cadastrar")
	public String cadastrar(ClassesCarreira classesCarreira) {
		return "/classe/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("classesCarreira", service.buscarTodos());
		return "/classe/lista"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(ClassesCarreira classe, RedirectAttributes attr) {
		service.salvar(classe);
		attr.addFlashAttribute("success", "Inserido com sucesso.");
		return "redirect:/classes/carreira/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("classesCarreira", service.buscarPorId(id));
		return "/classe/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(ClassesCarreira classesCarreira, RedirectAttributes attr) {
		service.editar(classesCarreira);
		attr.addFlashAttribute("success", "Editado com sucesso.");
		return "redirect:/classes/carreira/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);  
		model.addAttribute("success", "Excluído com sucesso.");
		return listar(model);
	}
	
	@GetMapping("/buscar/nome/classe")
	public String getPorNome(@RequestParam("nomeClasse") String nomeClasse, ModelMap model) {		
		model.addAttribute("classesCarreira", service.buscarPorNome(nomeClasse.toUpperCase().trim()));
		return "/classe/lista";
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
