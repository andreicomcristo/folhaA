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
import com.folha.boot.domain.TiposDeFolha;
import com.folha.boot.service.TiposDeFolhaService;

@Controller
@RequestMapping("/tiposdefolhas")
public class TiposDeFolhaController {

	@Autowired
	private TiposDeFolhaService service;

	@GetMapping("/cadastrar")
	public String cadastrar(TiposDeFolha tiposDeFolha) {		
		return "/tipofolha/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("tiposDeFolha", service.buscarTodos());
		return "/tipofolha/lista"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(TiposDeFolha tiposDefolha, RedirectAttributes attr) {
		service.salvar(tiposDefolha);
		attr.addFlashAttribute("success", "Inserido com sucesso.");
		return "redirect:/tiposdefolhas/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("tiposDeFolha", service.buscarPorId(id));
		return "/tipofolha/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(TiposDeFolha tiposDeFolha, RedirectAttributes attr) {
		service.editar(tiposDeFolha);
		attr.addFlashAttribute("success", "Editado com sucesso.");
		return "redirect:/tiposdefolhas/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);  
		model.addAttribute("success", "Excluído com sucesso.");
		return listar(model);
	}
	
	@GetMapping("/buscar/nome/tipofolha")
	public String getPorNome(@RequestParam("nomeTipoFolha") String nomeTipoFolha, ModelMap model) {		
		model.addAttribute("tiposDeFolha", service.buscarPorNome(nomeTipoFolha.toUpperCase().trim()));
		return "/tipofolha/lista";
	}
	
}