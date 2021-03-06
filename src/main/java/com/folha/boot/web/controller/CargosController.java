package com.folha.boot.web.controller;

import java.util.List;

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
import com.folha.boot.domain.Cargos;
import com.folha.boot.domain.CargosEspecialidade;
import com.folha.boot.domain.NiveisCargo;
import com.folha.boot.service.AreaDoCargoService;
import com.folha.boot.service.CargosEspecialidadeService;
import com.folha.boot.service.CargosService;
import com.folha.boot.service.NiveisCargoService;

@Controller
@RequestMapping("/cargos")
public class CargosController {

	
	@Autowired
	private CargosService service;	
	@Autowired
	private NiveisCargoService niveisCargoService;
	@Autowired
	private CargosEspecialidadeService cargosEspecialidadeService;
	@Autowired
	private AreaDoCargoService areaDoCargoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cargos cargos) {
		return "/cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos", service.buscarTodos());
		return "/cargo/lista"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(Cargos cargos, String areaMeio, RedirectAttributes attr) {	
		
		
		Boolean novoSalvamento = false;
		if(cargos.getId()==null) {novoSalvamento = true;}
		Cargos cargoSalvo = service.salvar(cargos);
		
		//Salvando a especialidade nao se aplica
		if(novoSalvamento == true) {
			CargosEspecialidade cargosEspecialidade = new CargosEspecialidade();
			if(areaMeio == null) {cargosEspecialidade.setIdAreaDoCargoFk(areaDoCargoService.buscarPorNomePrimeiro("FIM"));}else {cargosEspecialidade.setIdAreaDoCargoFk(areaDoCargoService.buscarPorNomePrimeiro("MEIO"));}
			cargosEspecialidade.setDescricaoEspecialidadeCargo("NAO SE APLICA");
			cargosEspecialidade.setNomeEspecialidadeCargo("NAO SE APLICA");
			cargosEspecialidade.setIdCargoFk(cargoSalvo);
			cargosEspecialidade.setId(null);
			cargosEspecialidadeService.salvar(cargosEspecialidade);
		}
		
		
		attr.addFlashAttribute("success", "Inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cargos", service.buscarPorId(id));
		return "/cargo/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Cargos cargos, RedirectAttributes attr) {		
		service.editar(cargos);
		attr.addFlashAttribute("success", "Editado com sucesso.");
		return "redirect:/cargos/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id); 
		model.addAttribute("success", "Excluído com sucesso.");
		return listar(model);
	}
	
	@GetMapping("/buscar/nome/cargo")
	public String getPorNome(@RequestParam("nomeCargo") String nomeCargo, ModelMap model) {	
		model.addAttribute("cargos", service.buscarPorNome(nomeCargo.toUpperCase().trim()));
		return "/cargo/lista";
	}
	
	@ModelAttribute("idNivelCargoFk")
	public List<NiveisCargo> listaDeNiveisCargo() {
		return niveisCargoService.buscarTodos();
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
	
	
	
	/*@ModelAttribute("cargosEspecialidadeList")
	public List<CargosEspecialidade> listaDeEspecialidadeCargo() {
		return cargosEspecialidadeService.buscarTodos();
	}*/
} 
