package uk.org.whitecottage.gnosis.apqc;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class APQC {
	protected XSSFWorkbook xlsx;
	protected Sheet glossary;

	public APQC(XSSFWorkbook xlsx) {
		this.xlsx = xlsx;		
		glossary = xlsx.getSheet("Glossary terms");
	}
	
	@SuppressWarnings("deprecation")
	public XSSFWorkbook getProcesses() {
		XSSFWorkbook gnosis = new XSSFWorkbook();
		CellStyle wrapped = gnosis.createCellStyle();
		wrapped.setWrapText(true);
		wrapped.setVerticalAlignment(VerticalAlignment.TOP);
		
		Sheet taxonomy = gnosis.createSheet("ProcessTaxonomy");
		Row header = taxonomy.createRow(0);
		header.createCell(0).setCellValue("Node Id");
		header.createCell(1).setCellValue("Index");
		header.createCell(2).setCellValue("Name");
		header.createCell(3).setCellValue("Description");
		
		taxonomy.setColumnWidth(0, 12*256);
		taxonomy.setColumnWidth(1, 12*256);
		taxonomy.setColumnWidth(2, 60*256);
		taxonomy.setColumnWidth(3, 120*256);
		
		Sheet combined = xlsx.getSheet("Combined");

		int rowCount = 1;
		
		for (Row row: combined) {
			if (row.getCell(0).getCellTypeEnum() == CellType.NUMERIC) {
				String id;
				String[] index = getCellValue(row, 1).split("\\.");
				if (index.length == 2 && "0".equals(index[1])) {
					id = index[0];
				} else {
					id = row.getCell(1).getStringCellValue();
				}
				Row newRow = taxonomy.createRow(rowCount++);
				Cell cell = newRow.createCell(0);
				cell.setCellValue("apqc_" + getCellValue(row, 0));
				cell.setCellStyle(wrapped);
				cell = newRow.createCell(1);
				cell.setCellValue(id);
				cell.setCellStyle(wrapped);
				cell = newRow.createCell(2);
				cell.setCellValue(row.getCell(2).getStringCellValue());
				cell.setCellStyle(wrapped);
				cell = newRow.createCell(3);
				cell.setCellValue(findDescription(getCellValue(row, 0)));
				cell.setCellStyle(wrapped);
			}
		}
		
		return gnosis;
	}
	
	protected Row nextRow(Row row) {
		return row.getSheet().getRow(row.getRowNum() + 1);
	}
	
	protected String getCellValue(Sheet sheet, int r, int c) {
		return getCellValue(sheet.getRow(r), c);
	}

	protected String getCellValue(Row row, int c) {
		if (row != null) {
			return getCellValue(row.getCell(c));
		}
		
		return "";
	}

	@SuppressWarnings("deprecation")
	protected String getCellValue(Cell cell) {
		if (cell != null) {
			if (cell.getCellTypeEnum() == CellType.STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
				Double d = cell.getNumericCellValue();
				return Integer.toString(d.intValue());
			}
		}
		
		return "";
	}
	
	protected String findDescription(String id) {
		for (Row row: glossary) {
			if (getCellValue(row, 0).equals(id)) {
				return getCellValue(row, 3);
			}
		}
		
		return "";
	}
}
