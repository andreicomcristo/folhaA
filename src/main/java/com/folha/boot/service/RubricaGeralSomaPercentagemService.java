package com.folha.boot.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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
import com.folha.boot.Reposytory.FaixasValoresParametrosCalculoFolhasExtrasReposytory;
import com.folha.boot.Reposytory.FaixasValoresSubsidioReposytory;
import com.folha.boot.Reposytory.RubricaComplementoConstitucionalReposytory;
import com.folha.boot.Reposytory.RubricaGeralSomaPercentagemReposytory;
import com.folha.boot.Reposytory.RubricaGeralSomaReposytory;
import com.folha.boot.Reposytory.RubricaInsalubridadeReposytory;
import com.folha.boot.domain.AnoMes;
import com.folha.boot.domain.Cidades;
import com.folha.boot.domain.FaixasValoresParametrosCalculoFolhasExtras;
import com.folha.boot.domain.FaixasValoresSubsidio;
import com.folha.boot.domain.RubricaComplementoConstitucional;
import com.folha.boot.domain.RubricaComplementoConstitucionalCodigo;
import com.folha.boot.domain.RubricaGeralSoma;
import com.folha.boot.domain.RubricaGeralSomaCodigo;
import com.folha.boot.domain.RubricaGeralSomaPercentagem;
import com.folha.boot.domain.RubricaGeralSomaPercentagemCodigo;
import com.folha.boot.domain.RubricaInsalubridade;
import com.folha.boot.domain.RubricaInsalubridadeCodigo;
import com.folha.boot.domain.Unidades;
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
public class RubricaGeralSomaPercentagemService {

	@Autowired
	private RubricaGeralSomaPercentagemReposytory reposytory;
	
	@Autowired
	private AnoMesService anoMesService;
	

	public void salvar(RubricaGeralSomaPercentagem rubricaGeralSomaPercentagem) {
		// TODO Auto-generated method stub
		reposytory.save(rubricaGeralSomaPercentagem);
	}

	public void editar(RubricaGeralSomaPercentagem rubricaGeralSomaPercentagem) {
		// TODO Auto-generated method stub
		reposytory.save(rubricaGeralSomaPercentagem);
	}

	public void excluir(Long id) {
		// TODO Auto-generated method stub
		reposytory.deleteById(id);
	}

	@Transactional(readOnly = true)
	public RubricaGeralSomaPercentagem buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return reposytory.findById(id).get();
	}

	@Transactional(readOnly = true)
	public List<RubricaGeralSomaPercentagem> buscarTodos() {
		// TODO Auto-generated method stub
		return reposytory.findAllByOrderByIdAnoMesFkNomeAnoMesDesc();
	}
	
	@Transactional(readOnly = true)
	public List<RubricaGeralSomaPercentagem> buscarPorMesExato(AnoMes anoMes) {
		return reposytory.findByIdAnoMesFkOrderByIdAnoMesFkNomeAnoMesDesc(anoMes);
	}
	
	@Transactional(readOnly = true)
	public List<RubricaGeralSomaPercentagem> buscarPorNome(String nome) {
		return reposytory.findByIdAnoMesFkNomeAnoMesContainingOrderByIdAnoMesFkNomeAnoMesDesc(nome);
	}
	
	public Page<RubricaGeralSomaPercentagem> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return this.reposytory.findAllByOrderByIdAnoMesFkNomeAnoMesDesc(pageable);
	}

	public Page<RubricaGeralSomaPercentagem> findPaginatedAnoMes(int pageNo, int pageSize, String nome) {
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return this.reposytory.findByIdAnoMesFkNomeAnoMesContainingOrderByIdAnoMesFkNomeAnoMesDesc(nome.toUpperCase().trim(), pageable);
	}
	
	public boolean avaliarCadastrado( RubricaGeralSomaPercentagemCodigo rubricaGeralSomaPercentagemCodigo, AnoMes anoMes) {
		boolean resposta = false;
		List<RubricaGeralSomaPercentagem> lista = reposytory.findByIdCodigoFkAndIdAnoMesFk( rubricaGeralSomaPercentagemCodigo, anoMes); 
		if(!lista.isEmpty()) {resposta = true;}
		return resposta;
	}
	
	//Herdar de um mes para o outro
	public void herdarDeUmMesParaOOutro(Long anoMesInicial, Long anoMesFinal) {
		
		List<RubricaGeralSomaPercentagem> listaInicial = buscarPorMesExato(anoMesService.buscarPorId(anoMesInicial)); 
		List<RubricaGeralSomaPercentagem> listaFinal = buscarPorMesExato(anoMesService.buscarPorId(anoMesFinal));
		
		if( (!listaInicial.isEmpty())  &&  (listaFinal.isEmpty()) ) {
			for(int i=0;i<listaInicial.size();i++) {
				RubricaGeralSomaPercentagem f = new RubricaGeralSomaPercentagem();
				f.setId(null);
				f.setIdAnoMesFk(anoMesService.buscarPorId(anoMesFinal));
				f.setIdCodigoFk( listaInicial.get(i).getIdCodigoFk() );
				f.setValor(listaInicial.get(i).getValor());
				
				salvar(f);
			}
		}
	}
	
	
	public ByteArrayInputStream exportarExcel(List<RubricaGeralSomaPercentagem> lista) {
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
	        cell.setCellValue("Codigo");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(4);
	        cell.setCellValue("Valor");
	        cell.setCellStyle(headerCellStyle);
	        
	        
	        // Creating data rows for each customer
	        for(int i = 0; i < lista.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue((i+1));
	        	dataRow.createCell(1).setCellValue(lista.get(i).getId());
	        	dataRow.createCell(2).setCellValue(lista.get(i).getIdAnoMesFk().getNomeAnoMes());
	        	dataRow.createCell(3).setCellValue(lista.get(i).getIdCodigoFk().getCodigo());
	        	dataRow.createCell(4).setCellValue(lista.get(i).getValor());
	        	
	        	
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        
	        
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ByteArrayInputStream exportarPdf(List<RubricaGeralSomaPercentagem> lista) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(90);
			table.setWidths(new int[] { 2, 2, 2, 2, 2 });

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
			
			hcell = new PdfPCell(new Phrase("Código", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Valor", cabecalhoFont));
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
				
				cell = new PdfPCell(new Phrase(lista.get(i).getIdCodigoFk().getCodigo() ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(lista.get(i).getValor()) ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				
				
				
			
			
			}
			
			// Titulo
			
			PdfPTable tableTitulo = new PdfPTable(1);
			tableTitulo.setWidthPercentage(90);
			tableTitulo.setWidths(new int[] { 6 });
			PdfPCell cellTitulo;
			cellTitulo = new PdfPCell(new Phrase("Rubrica Geral Soma Percentagem", tituloFont) );
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
