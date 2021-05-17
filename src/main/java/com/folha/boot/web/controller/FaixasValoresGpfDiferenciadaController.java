package com.folha.boot.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.folha.boot.domain.AnoMes;
import com.folha.boot.domain.CargaHorariaSemanal;
import com.folha.boot.domain.CargosEspecialidade;
import com.folha.boot.domain.Carreiras;
import com.folha.boot.domain.ClassesCarreira;
import com.folha.boot.domain.FaixasValoresGpfDiferenciada;
import com.folha.boot.domain.Fonte;
import com.folha.boot.domain.NiveisCargo;
import com.folha.boot.domain.NiveisCarreira;
import com.folha.boot.domain.TipoBrutoLiquido;
import com.folha.boot.domain.Unidades;
import com.folha.boot.domain.UnidadesRegime;
import com.folha.boot.service.AnoMesService;
import com.folha.boot.service.CargaHorariaSemanalService;
import com.folha.boot.service.CargosEspecialidadeService;
import com.folha.boot.service.CarreirasService;
import com.folha.boot.service.ClassesCarreiraService;
import com.folha.boot.service.FaixasValoresGpfDiferenciadaService;
import com.folha.boot.service.FonteService;
import com.folha.boot.service.NiveisCargoService;
import com.folha.boot.service.NiveisCarreiraService;
import com.folha.boot.service.TipoBrutoLiquidoService;
import com.folha.boot.service.UnidadesRegimeService;
import com.folha.boot.service.UnidadesService;


@Controller
@RequestMapping("/faixasValoresGpfDiferenciada")
public class FaixasValoresGpfDiferenciadaController {

	String ultimoAnoMes = "";
	
	@Autowired
	private FaixasValoresGpfDiferenciadaService service;
	@Autowired
	private NiveisCarreiraService niveisCarreiraService;
	@Autowired
	private CarreirasService carreirasService;
	@Autowired
	private UnidadesService unidadesService;
	@Autowired
	private ClassesCarreiraService classesCarreiraService;
	@Autowired
	private CargaHorariaSemanalService cargaHorariaSemanalService;
	@Autowired
	private AnoMesService anoMesService;
	@Autowired
	private FonteService fonteService;
	@Autowired
	private TipoBrutoLiquidoService tipoBrutoLiquidoService;
	@Autowired
	private NiveisCargoService niveisCargoService;
	@Autowired
	private CargosEspecialidadeService cargosEspecialidadeService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(FaixasValoresGpfDiferenciada faixasValoresGpfDiferenciada) {
		
		return "/faixasValoresGpfDiferenciada/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		this.ultimoAnoMes = "";
		return this.findPaginated(1, model); 
	}
	
	@GetMapping("/listar/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, ModelMap model) {
		int pageSeze = 10;
		Page<FaixasValoresGpfDiferenciada> page = service.findPaginated(pageNo, pageSeze);
		List<FaixasValoresGpfDiferenciada> listaCidades = page.getContent();
		return paginar(pageNo, page, listaCidades, model);
	}
	
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, String cnes, ModelMap model) {
		int pageSeze = 10;
		Page<FaixasValoresGpfDiferenciada> page = service.findPaginatedAnoMes(pageNo, pageSeze, cnes);
		List<FaixasValoresGpfDiferenciada> lista = page.getContent();
		return paginar(pageNo, page, lista, model);
	}
	
	public String paginar(int pageNo, Page<FaixasValoresGpfDiferenciada> page, List<FaixasValoresGpfDiferenciada> lista, ModelMap model) {	
		model.addAttribute("currentePage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements()); 
		model.addAttribute("faixasValoresGpfDiferenciada", lista);
		return "/faixasValoresGpfDiferenciada/lista";	
	}
	
	@GetMapping("/paginar/{pageNo}")
	public String getPorCnesPaginado(@PathVariable (value = "pageNo") int pageNo, ModelMap model) {
		if(pageNo<1) {pageNo = 1;}
		if( (ultimoAnoMes.equals("")) ){
			return "redirect:/faixasValoresGpfDiferenciada/listar/{pageNo}" ;}
		else {return this.findPaginated(pageNo, ultimoAnoMes, model);}
	}
	
	@GetMapping("/buscar/nome/anomes")
	public String getPorAnoMes(@RequestParam("anoMes") String anoMes, ModelMap model) {
		this.ultimoAnoMes = anoMes;
		return this.findPaginated(1, anoMes, model);
	}
	
	@PostMapping("/salvar")
	public String salvar(FaixasValoresGpfDiferenciada faixasValoresGpfDiferenciada, RedirectAttributes attr) {
		
		if(faixasValoresGpfDiferenciada.getValor()==null) {
			faixasValoresGpfDiferenciada.setValor(0.0);
		}
		
		service.salvar(faixasValoresGpfDiferenciada);
		attr.addFlashAttribute("success", "Inserido com sucesso.");
		return "redirect:/faixasValoresGpfDiferenciada/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("faixasValoresGpfDiferenciada", service.buscarPorId(id));
		return "/faixasValoresGpfDiferenciada/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(FaixasValoresGpfDiferenciada faixasValoresGpfDiferenciada, RedirectAttributes attr) {	
		
		if(faixasValoresGpfDiferenciada.getValor()==null) {
			faixasValoresGpfDiferenciada.setValor(0.0);
		}
		
		service.editar(faixasValoresGpfDiferenciada);
		attr.addFlashAttribute("success", "Editado com sucesso.");
		return "redirect:/faixasValoresGpfDiferenciada/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);  
		model.addAttribute("success", "Excluído com sucesso.");
		return listar(model);
	}
	
	
	@GetMapping("/herdar/de/mes") 
	public String herdarDeMes( Long anoMesInicial,  Long anoMesFinal,  ModelMap model) {		
		service.herdarDeUmMesParaOOutro(anoMesInicial, anoMesFinal);
		return "redirect:/faixasValoresGpfDiferenciada/listar" ;
	}
	
	
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("cnesUnidade") String nome, ModelMap model) {		
		model.addAttribute("faixasValoresGpfDiferenciada", service.buscarPorNome(nome.toUpperCase().trim()));
		return "/faixasValoresGpfDiferenciada/lista";
	}
	
	@GetMapping("/exporta/excel")
    public void downloadExcel(HttpServletResponse response, ModelMap model) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=dados.xlsx");
        ByteArrayInputStream stream = service.exportarExcel(service.buscarTodos());
        IOUtils.copy(stream, response.getOutputStream());
    }
	
	@GetMapping(value = "/exporta/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> employeeReports(HttpServletResponse response) throws IOException {
		ByteArrayInputStream bis = service.exportarPdf(service.buscarTodos());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=dados.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
	
	
	
	
	@ModelAttribute("idEspecialidadeFk")
	public List<CargosEspecialidade> getIdEspecialidadeFk() {
		return cargosEspecialidadeService.buscarTodos();	
	}
	
	@ModelAttribute("idAnoMesFk")
	public List<AnoMes> getIdAnoMesFk() {
		return anoMesService.buscarTodos();	
	}
	@ModelAttribute("idNivelCargoFk")
	public List<NiveisCargo> getIdNivelCarreiraFk() {
		return niveisCargoService.buscarTodos();	
	}
	@ModelAttribute("idUnidadeFk")
	public List<Unidades> getIdUnidadeRegimeFk() {
		return unidadesService.buscarTodos();	
	}
	@ModelAttribute("idClasseCarreiraFk")
	public List<ClassesCarreira> getIdClasseCarreiraFk() {
		return classesCarreiraService.buscarTodos();	
	}
	@ModelAttribute("idCargaHorariaSemanalFk")
	public List<CargaHorariaSemanal> getIdCargaHorariaSemanalFk() {
		return cargaHorariaSemanalService.buscarTodos();	
	}
	@ModelAttribute("idFonteFk")
	public List<Fonte> getIdFonteFk() {
		return fonteService.buscarTodos();
	}
	@ModelAttribute("idTipoBrutoLiquidoFk")
	public List<TipoBrutoLiquido> getIdTipoBrutoLiquidoFk() {
		return tipoBrutoLiquidoService.buscarTodos();
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

