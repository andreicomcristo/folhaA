package com.folha.boot.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.folha.boot.Reposytory.FaixasValoresParametrosCalculoFolhasExtrasReposytory;
import com.folha.boot.Reposytory.FaixasValoresSubsidioReposytory;
import com.folha.boot.Reposytory.RubricaComplementoConstitucionalFuncionarioReposytory;
import com.folha.boot.Reposytory.RubricaDescontoPensaoFuncionarioReposytory;
import com.folha.boot.Reposytory.RubricaInsalubridadeFuncionarioReposytory;
import com.folha.boot.Reposytory.RubricaInsalubridadeReposytory;
import com.folha.boot.domain.AnoMes;
import com.folha.boot.domain.Cidades;
import com.folha.boot.domain.FaixasValoresParametrosCalculoFolhasExtras;
import com.folha.boot.domain.FaixasValoresSubsidio;
import com.folha.boot.domain.PessoaFuncionarios;
import com.folha.boot.domain.RubricaComplementoConstitucionalCodigo;
import com.folha.boot.domain.RubricaComplementoConstitucionalFuncionario;
import com.folha.boot.domain.RubricaDescontoPensaoFuncionario;
import com.folha.boot.domain.RubricaInsalubridade;
import com.folha.boot.domain.RubricaInsalubridadeCodigo;
import com.folha.boot.domain.RubricaInsalubridadeFuncionario;
import com.folha.boot.domain.Unidades;
import com.folha.boot.service.seguranca.UsuarioService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
@Transactional(readOnly = false)
public class RubricaDescontoPensaoFuncionarioService {

	@Autowired
	private RubricaDescontoPensaoFuncionarioReposytory reposytory;
	
	@Autowired
	private AnoMesService anoMesService;
	@Autowired
	private UsuarioService usuarioService;
	

	public void salvar(RubricaDescontoPensaoFuncionario rubricaDescontoPensaoFuncionario) {
		// TODO Auto-generated method stub
		reposytory.save(rubricaDescontoPensaoFuncionario);
	}

	public void editar(RubricaDescontoPensaoFuncionario rubricaDescontoPensaoFuncionario) {
		// TODO Auto-generated method stub
		reposytory.save(rubricaDescontoPensaoFuncionario);
	}

	public void excluir(Long id) {
		// TODO Auto-generated method stub
		reposytory.deleteById(id);
	}

	@Transactional(readOnly = true)
	public RubricaDescontoPensaoFuncionario buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return reposytory.findById(id).get();
	}

	@Transactional(readOnly = true)
	public List<RubricaDescontoPensaoFuncionario> buscarTodos() {
		// TODO Auto-generated method stub
		return reposytory.findByDtCancelamentoIsNullOrderByIdAnoMesFkNomeAnoMesDesc();
	}
	
	@Transactional(readOnly = true)
	public List<RubricaDescontoPensaoFuncionario> buscarPorMesExato(AnoMes anoMes) {
		return reposytory.findByIdAnoMesFkAndDtCancelamentoIsNullOrderByIdAnoMesFkNomeAnoMesDesc(anoMes);
	}
	
	@Transactional(readOnly = true)
	public List<RubricaDescontoPensaoFuncionario> buscarPorNome(String nome) {
		return reposytory.findByIdAnoMesFkNomeAnoMesContainingAndDtCancelamentoIsNullOrderByIdAnoMesFkNomeAnoMesDesc(nome);
	}
	
	public Page<RubricaDescontoPensaoFuncionario> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return this.reposytory.findByDtCancelamentoIsNullOrderByIdAnoMesFkNomeAnoMesDesc(pageable);
	}

	public Page<RubricaDescontoPensaoFuncionario> findPaginatedAnoMes(int pageNo, int pageSize, String nome) {
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return this.reposytory.findByIdAnoMesFkNomeAnoMesContainingAndDtCancelamentoIsNullOrderByIdAnoMesFkNomeAnoMesDesc(nome.toUpperCase().trim(), pageable);
	}
	
	public boolean avaliarCadastrado(  AnoMes anoMes, PessoaFuncionarios PessoaFuncionarios) {
		boolean resposta = false;
		List<RubricaDescontoPensaoFuncionario> lista = reposytory.findByIdAnoMesFkAndIdFuncionarioFkAndDtCancelamentoIsNull(  anoMes, PessoaFuncionarios); 
		if(!lista.isEmpty()) {resposta = true;}
		return resposta;
	}
	
	//Herdar de um mes para o outro
	public void herdarDeUmMesParaOOutro(Long anoMesInicial, Long anoMesFinal) {
		
		List<RubricaDescontoPensaoFuncionario> listaInicial = buscarPorMesExato(anoMesService.buscarPorId(anoMesInicial)); 
		List<RubricaDescontoPensaoFuncionario> listaFinal = buscarPorMesExato(anoMesService.buscarPorId(anoMesFinal));
		
		if( (!listaInicial.isEmpty())  &&  (listaFinal.isEmpty()) ) {
			for(int i=0;i<listaInicial.size();i++) {
				RubricaDescontoPensaoFuncionario f = new RubricaDescontoPensaoFuncionario();
				f.setId(null);
				f.setIdAnoMesFk(anoMesService.buscarPorId(anoMesFinal));
				f.setIdFuncionarioFk(listaInicial.get(i).getIdFuncionarioFk());
				f.setDescricao(listaInicial.get(i).getDescricao());
				f.setDtCadastro( new Date() );
				f.setDtCancelamento(listaInicial.get(i).getDtCancelamento());
				f.setIdOperadorCadastroFk( usuarioService.pegarOperadorLogado() );
				f.setIdOperadorCancelamentoFk(listaInicial.get(i).getIdOperadorCadastroFk());
				f.setPercentagem(listaInicial.get(i).getPercentagem());
				f.setValorCheio(listaInicial.get(i).getValorCheio());
				
				salvar(f);
			}
		}
	}
	
	
	public ByteArrayInputStream exportarExcel(List<RubricaDescontoPensaoFuncionario> lista) {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Dados");
			
			Row row = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        // Creating header
	        
	        Cell cell = row.createCell(0);
	        cell.setCellValue("Ordem");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Id");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(2);
	        cell.setCellValue("Mês");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(3);
	        cell.setCellValue("Nome");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(4);
	        cell.setCellValue("Cpf");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(5);
	        cell.setCellValue("Valor");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(6);
	        cell.setCellValue("Percentagem");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(7);
	        cell.setCellValue("Obs");
	        cell.setCellStyle(headerCellStyle);
	        
	        
	        
	        
	        // Creating data rows for each customer
	        for(int i = 0; i < lista.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue((i+1));
	        	dataRow.createCell(1).setCellValue(lista.get(i).getId());
	        	dataRow.createCell(2).setCellValue(lista.get(i).getIdAnoMesFk().getNomeAnoMes());
	        	dataRow.createCell(3).setCellValue(lista.get(i).getIdFuncionarioFk().getIdPessoaFk().getNome());
	        	dataRow.createCell(4).setCellValue(lista.get(i).getIdFuncionarioFk().getIdPessoaFk().getCpf());
	        	dataRow.createCell(5).setCellValue(lista.get(i).getValorCheio());
	        	dataRow.createCell(6).setCellValue(lista.get(i).getPercentagem());
	        	dataRow.createCell(7).setCellValue(lista.get(i).getDescricao());
	        	
	        	
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        sheet.autoSizeColumn(5);
	        sheet.autoSizeColumn(6);
	        sheet.autoSizeColumn(7);
	        
	        
	        
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ByteArrayInputStream exportarPdf(List<RubricaDescontoPensaoFuncionario> lista) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfPTable table = new PdfPTable(8);
			table.setWidthPercentage(90);
			table.setWidths(new int[] { 2, 2, 2, 3, 2, 2, 2, 4 });

			// Tipos de Fonte
			Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,14);
			Font cabecalhoFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,6);
			Font corpoFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 5);
			Font nomeSistemaFont = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 6);
			Font rodapeFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 4);
			
			//Cabeçalho
			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Ordem", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Id", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Mês", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Nome", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Cpf", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Valor", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Percentagem", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Descrição", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			

			// Corpo
			for (int i=0; i<lista.size();i++) {

				PdfPCell cell;

				cell = new PdfPCell(new Phrase( String.valueOf(i+1) ,corpoFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase( String.valueOf( lista.get(i).getId()) ,corpoFont) );
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(lista.get(i).getIdAnoMesFk().getNomeAnoMes() ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(lista.get(i).getIdFuncionarioFk().getIdPessoaFk().getNome()) ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(lista.get(i).getIdFuncionarioFk().getIdPessoaFk().getCpf()) ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(lista.get(i).getValorCheio()  ) ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(lista.get(i).getPercentagem()  ) ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(lista.get(i).getDescricao()  ) ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				
				
			
			
			}
			
			// Titulo
			
			PdfPTable tableTitulo = new PdfPTable(1);
			tableTitulo.setWidthPercentage(90);
			tableTitulo.setWidths(new int[] { 6 });
			PdfPCell cellTitulo;
			cellTitulo = new PdfPCell(new Phrase("Pessoas com Rubrica Desconto Pensão", tituloFont) );
			cellTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableTitulo.addCell(cellTitulo);
			
			
			// Rodape
			PdfPTable tableRodape = new PdfPTable(1);
			tableRodape.setWidthPercentage(90);
			tableRodape.setWidths(new int[] { 6 });
			PdfPCell cellRodape;
			
			cellRodape = new PdfPCell(new Phrase("Sistema Gente-Web", nomeSistemaFont) );
			cellRodape.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellRodape.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableRodape.addCell(cellRodape);
			
			cellRodape = new PdfPCell(new Phrase(""+new Date() ,rodapeFont)  );
			cellRodape.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellRodape.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableRodape.addCell(cellRodape);
			
			

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(tableTitulo);
			document.add(table);
			document.add(tableRodape);

			document.close();

		} catch (DocumentException ex) {

		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	
	
	
}