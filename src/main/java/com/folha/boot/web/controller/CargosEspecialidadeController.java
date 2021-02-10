package com.folha.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.folha.boot.domain.CargosEspecialidade;
import com.folha.boot.service.CargosEspecialidadeService;

@Controller
@RequestMapping("/especialidades")
public class CargosEspecialidadeController {
	
	@Autowired
	private CargosEspecialidadeService service;

	@GetMapping("/cadastrar")
	public String cadastrar(CargosEspecialidade especialidade) {
		
		return "/especialidade/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("especialidades", service.buscarTodos());
		return "/especialidade/lista"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(CargosEspecialidade especialidade, RedirectAttributes attr) {
		
		service.salvar(especialidade);
		attr.addFlashAttribute("success", "Especialidade inserida com sucesso.");
		return "redirect:/especialidade/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("especialidade", service.buscarPorId(id));
		return "/especialidade/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(CargosEspecialidade especialidade, RedirectAttributes attr) {
		service.editar(especialidade);
		attr.addFlashAttribute("success", "Especialidade editada com sucesso.");
		return "redirect:/especialidade/cadastrar";
	}
	//Implementação futura
	/*@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		
		if (service.depertamentoTemCargos(id)) {
			model.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
		} else {
			service.excluir(id);
			model.addAttribute("success", "Departamento excluído com sucesso.");
		}
		
		return listar(model);
	}*/
	
}