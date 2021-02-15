package com.folha.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.folha.boot.domain.Unidades;
import com.folha.boot.service.UnidadesService;

@Controller
@RequestMapping("/unidades")
public class UnidadesController {

	@Autowired
	private UnidadesService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Unidades unidades) {		
		return "/unidade/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("unidades", service.buscarTodos());
		return "/unidade/lista"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(Unidades unidades, RedirectAttributes attr) {
		
		service.salvar(unidades);
		attr.addFlashAttribute("success", "Inserido com sucesso.");
		return "redirect:/unidades/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("unidades", service.buscarPorId(id));
		return "/unidade/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Unidades unidades, RedirectAttributes attr) {
		service.editar(unidades);
		attr.addFlashAttribute("success", "Editado com sucesso.");
		return "redirect:/unidades/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);  
		model.addAttribute("success", "Excluído com sucesso.");
		return listar(model);
	}
}
