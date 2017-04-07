package uk.org.whitecottage.gnosis.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uk.org.whitecottage.gnosis.json.JSONArray;
import uk.org.whitecottage.gnosis.json.JSONMap;
import uk.org.whitecottage.gnosis.json.JSONString;

public class GnosisXSLX {
	protected XSSFWorkbook xlsx;

	public GnosisXSLX(XSSFWorkbook xlsx) {
		this.xlsx = xlsx;		
	}
	
	public JSONArray getTaxonomy(String sheetName) {
		JSONArray processesJson = new JSONArray();
		
		Sheet process = xlsx.getSheet(sheetName);
		
		for (Row row: process) {
			String index[] = getCellValue(row, 1).split("\\.");
			if (row.getRowNum() > 0 && index.length == 1) {
				JSONMap activity = new JSONMap();
				activity.put(new JSONString("nodeId", getCellValue(row, 0)));
				activity.put(new JSONString("name", getCellValue(row, 2)));
				activity.put(new JSONString("description", getCellValue(row, 3)));
				activity.put(parseProcess(row, 2));
			
				processesJson.add(activity);
			}
		}
		
		return processesJson;
	}
	
	protected JSONArray parseProcess(Row row, int level) {
		JSONArray activities = new JSONArray("children");

		row = nextRow(row);
		while (row != null) {
			String index[] = getCellValue(row, 1).split("\\.");
			if (index.length == level && !index[1].equals("0")) {
				JSONMap activity = new JSONMap();
				activity.put(new JSONString("nodeId", getCellValue(row, 0)));
				activity.put(new JSONString("name", getCellValue(row, 2)));
				activity.put(new JSONString("description", getCellValue(row, 3)));
				activity.put(parseProcess(row, level + 1));
				
				activities.add(activity);
			} else if (index.length < level || index[1].equals("0")) {
				break;
			}
			row = nextRow(row);
		}
		
		return activities;
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
}
