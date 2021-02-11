package com.folha.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.folha.boot.domain.HabilitacaoCategorias;
import com.folha.boot.service.HabilitacaoCategoriasService;

@Controller
@RequestMapping("/habilitacaocategorias")
public class HabilitacaoCategoriasController {

	@Autowired
	private HabilitacaoCategoriasService service;

	@GetMapping("/cadastrar")
	public String cadastrar(HabilitacaoCategorias habilitacaoCategoria) {
		
		return "/habilitacaocategoria/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("habilitacaocategorias", service.buscarTodos());
		return "/habilitacaocategoria/lista"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(HabilitacaoCategorias habilitacaoCategoria, RedirectAttributes attr) {
		
		service.salvar(habilitacaoCategoria);
		attr.addFlashAttribute("success", "Inserido com sucesso.");
		return "redirect:/habilitacaocategorias/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("habilitacaocategorias", service.buscarPorId(id));
		return "/habilitacaocategoria/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(HabilitacaoCategorias habilitacaoCategoria, RedirectAttributes attr) {
		service.editar(habilitacaoCategoria);
		attr.addFlashAttribute("success", "Editado com sucesso.");
		return "redirect:/habilitacaocategorias/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);  
		model.addAttribute("success", "Excluído com sucesso.");
		return listar(model);
	}
}
