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
import com.folha.boot.domain.Cidades;
import com.folha.boot.domain.FaixasValoresParametrosCalculoFolhasExtras;
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
public class FaixasValoresParametrosCalculoFolhasExtrasService {

	@Autowired
	private FaixasValoresParametrosCalculoFolhasExtrasReposytory reposytory;

	public void salvar(FaixasValoresParametrosCalculoFolhasExtras faixasValoresParametrosCalculoFolhasExtras) {
		// TODO Auto-generated method stub
		reposytory.save(faixasValoresParametrosCalculoFolhasExtras);
	}

	public void editar(FaixasValoresParametrosCalculoFolhasExtras faixasValoresParametrosCalculoFolhasExtras) {
		// TODO Auto-generated method stub
		reposytory.save(faixasValoresParametrosCalculoFolhasExtras);
	}

	public void excluir(Long id) {
		// TODO Auto-generated method stub
		reposytory.deleteById(id);
	}

	@Transactional(readOnly = true)
	public FaixasValoresParametrosCalculoFolhasExtras buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return reposytory.findById(id).get();
	}

	@Transactional(readOnly = true)
	public List<FaixasValoresParametrosCalculoFolhasExtras> buscarTodos() {
		// TODO Auto-generated method stub
		return reposytory.findAllByOrderByCnesUnidadeAsc();
	}
	@Transactional(readOnly = true)
	public List<FaixasValoresParametrosCalculoFolhasExtras> buscarPorCnes(String cnesUnidade) {
		return reposytory.findByCnesUnidadeContainingOrderByCnesUnidadeAsc(cnesUnidade);
	}
	
	public Page<FaixasValoresParametrosCalculoFolhasExtras> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return this.reposytory.findAllByOrderByAnoMesAscCnesUnidadeAsc(pageable);
	}

	public Page<FaixasValoresParametrosCalculoFolhasExtras> findPaginatedAnoMes(int pageNo, int pageSize, String nomeCidade) {
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return this.reposytory.findByAnoMesContainingOrderByAnoMesAscCnesUnidadeAsc(nomeCidade.toUpperCase().trim(), pageable);
	}
	
	public ByteArrayInputStream exportarExcel(List<FaixasValoresParametrosCalculoFolhasExtras> lista) {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Cidades");
			
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
	        cell.setCellValue("Folha");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(4);
	        cell.setCellValue("Valor Hr Dia");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(5);
	        cell.setCellValue("Valor Hr Noite");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(6);
	        cell.setCellValue("Valor Hr Semana");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(7);
	        cell.setCellValue("Valor Hr Fim Semana");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(8);
	        cell.setCellValue("Valor Liquido por Hora");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(9);
	        cell.setCellValue("Valor Bruto por Hora");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(10);
	        cell.setCellValue("Nível");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(11);
	        cell.setCellValue("Regime");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(12);
	        cell.setCellValue("Cnes");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(13);
	        cell.setCellValue("Unidade");
	        cell.setCellStyle(headerCellStyle);
	        
	        // Creating data rows for each customer
	        for(int i = 0; i < lista.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue((i+1));
	        	dataRow.createCell(1).setCellValue(lista.get(i).getId());
	        	dataRow.createCell(2).setCellValue(lista.get(i).getAnoMes());
	        	dataRow.createCell(3).setCellValue(lista.get(i).getNomeTipoFolha());
	        	dataRow.createCell(4).setCellValue(lista.get(i).getValorHoraDia());
	        	dataRow.createCell(5).setCellValue(lista.get(i).getValorHoraNoite());
	        	dataRow.createCell(6).setCellValue(lista.get(i).getValorHoraSemana());
	        	dataRow.createCell(7).setCellValue(lista.get(i).getValorHoraFimDeSemana());
	        	dataRow.createCell(8).setCellValue(lista.get(i).getValorLiquidoPorHora());
	        	dataRow.createCell(9).setCellValue(lista.get(i).getValorBrutoPorHora());
	        	dataRow.createCell(10).setCellValue(lista.get(i).getNomeNivel());
	        	dataRow.createCell(11).setCellValue(lista.get(i).getNomeRegime());
	        	dataRow.createCell(12).setCellValue(lista.get(i).getCnesUnidade());
	        	dataRow.createCell(13).setCellValue(lista.get(i).getIdUnidadeFk().getNomeFantasia());
	        	
	        	
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
	        sheet.autoSizeColumn(8);
	        sheet.autoSizeColumn(9);
	        sheet.autoSizeColumn(10);
	        sheet.autoSizeColumn(11);
	        sheet.autoSizeColumn(12);
	        sheet.autoSizeColumn(13);
	        
	        
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ByteArrayInputStream exportarPdf(List<FaixasValoresParametrosCalculoFolhasExtras> lista) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfPTable table = new PdfPTable(14);
			table.setWidthPercentage(90);
			table.setWidths(new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 9 });

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
			
			hcell = new PdfPCell(new Phrase("Folha", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Valor Hora Dia", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Valor Hora Noite", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Valor Hora Semana", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Valor Hora Fim Semana", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Valor Liquido Hora", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Valor Bruto Hora", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Nível", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Regime", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Cnes", cabecalhoFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Unidade", cabecalhoFont));
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

				cell = new PdfPCell(new Phrase(lista.get(i).getAnoMes() ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(lista.get(i).getNomeTipoFolha() ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase( String.valueOf(lista.get(i).getValorHoraDia()) ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(lista.get(i).getValorHoraNoite()) ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(lista.get(i).getValorHoraSemana()) ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(lista.get(i).getValorHoraFimDeSemana()) ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(lista.get(i).getValorLiquidoPorHora()) ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(lista.get(i).getValorBrutoPorHora()) ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(lista.get(i).getNomeNivel() ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(lista.get(i).getNomeRegime() ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(lista.get(i).getCnesUnidade() ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(lista.get(i).getIdUnidadeFk().getNomeFantasia() ,corpoFont) );
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				
				
				
			
			
			}
			
			// Titulo
			
			PdfPTable tableTitulo = new PdfPTable(1);
			tableTitulo.setWidthPercentage(90);
			tableTitulo.setWidths(new int[] { 6 });
			PdfPCell cellTitulo;
			cellTitulo = new PdfPCell(new Phrase("Faixas Valores Referência Extra", tituloFont) );
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