package com.folha.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.folha.boot.domain.Cidades;
import com.folha.boot.service.CidadesService;

@Controller
@RequestMapping("/cidades")
public class CidadesController {

	@Autowired
	private CidadesService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Cidades cidade) {
		return "/cidade/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cidades", service.buscarTodos());
		return "/cidade/lista"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(Cidades cidade, RedirectAttributes attr) {
		service.salvar(cidade);
		attr.addFlashAttribute("success", "Inserido com sucesso.");
		return "redirect:/cidades/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cidades", service.buscarPorId(id));
		return "/cidade/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Cidades cidades, RedirectAttributes attr) {
		service.editar(cidades);
		attr.addFlashAttribute("success", "Editado com sucesso.");
		return "redirect:/cidades/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id); 
		model.addAttribute("success", "Excluído com sucesso.");
		return listar(model);
	}
	
	@GetMapping("/buscar/nome/cidade")
	public String getPorNome(@RequestParam("nomeCidade") String nomeCidade, ModelMap model) {		
		model.addAttribute("cidades", service.buscarPorNome(nomeCidade.toUpperCase().trim()));
		return "/cidade/lista";
	}
}
