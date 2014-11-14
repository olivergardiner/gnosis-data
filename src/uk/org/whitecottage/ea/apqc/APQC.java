package uk.org.whitecottage.ea.apqc;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uk.org.whitecottage.ea.gnosis.jaxb.framework.BusinessApplications;
import uk.org.whitecottage.ea.gnosis.jaxb.framework.BusinessOperatingModel;
import uk.org.whitecottage.ea.gnosis.jaxb.framework.BusinessProcesses;
import uk.org.whitecottage.ea.gnosis.jaxb.framework.CommonServices;
import uk.org.whitecottage.ea.gnosis.jaxb.framework.Framework;
import uk.org.whitecottage.ea.gnosis.jaxb.framework.Infrastructure;
import uk.org.whitecottage.ea.gnosis.jaxb.framework.PrimaryActivity;
import uk.org.whitecottage.ea.gnosis.jaxb.framework.ProcessComponent;
import uk.org.whitecottage.ea.gnosis.jaxb.framework.ProcessDomain;
import uk.org.whitecottage.ea.gnosis.jaxb.framework.Process;
import uk.org.whitecottage.ea.gnosis.jaxb.framework.SupportActivity;
import uk.org.whitecottage.ea.gnosis.jaxb.framework.ValueChain;

public class APQC {
	protected XSSFWorkbook xlsx;

	public APQC(XSSFWorkbook xlsx) {
		this.xlsx = xlsx;		
	}
	
	public Framework getFramework() {
		Framework framework = new Framework();
		
		framework.setBusinessContext("");
		
		BusinessOperatingModel bom = new BusinessOperatingModel();
		framework.setBusinessOperatingModel(bom);
		
		BusinessProcesses processes = new BusinessProcesses();
		bom.setBusinessProcesses(processes);
		
		ValueChain valueChain = new ValueChain();
		framework.setValueChain(valueChain);
		
		List<ProcessDomain> domains = processes.getProcessDomain();
		int numDomains = xlsx.getNumberOfSheets() - 7;
		
		for (int i = 0; i < numDomains; i++) {
			XSSFSheet sheet = xlsx.getSheetAt(i + 3);
			String activityId = "apqc_" + getCellValue(sheet, 1, 0);
			String activityName = getCellValue(sheet, 1, 2);
			if (i < 5) {
				PrimaryActivity activity = new PrimaryActivity();
				activity.setValueChainId(activityId);
				activity.setValue(activityName);
				valueChain.getPrimaryActivity().add(activity);
			} else {
				SupportActivity activity = new SupportActivity();
				activity.setValueChainId(activityId);
				activity.setValue(activityName);
				valueChain.getSupportActivity().add(activity);
			}
			
			domains.addAll(parseDomains(sheet, activityId));
		}
		
		BusinessApplications businessApplications = new BusinessApplications();
		framework.setBusinessApplications(businessApplications);
		
		CommonServices commonServices = new CommonServices();
		framework.setCommonServices(commonServices);
		
		Infrastructure infrastructure = new Infrastructure();
		framework.setInfrastructure(infrastructure);
		
		return framework;
	}
	
	protected List<ProcessDomain> parseDomains(XSSFSheet sheet, String activityId) {
		List<ProcessDomain> domains = new ArrayList<ProcessDomain>();

		for (Row row: sheet) {
			String index[] = getCellValue(row, 1).split("\\.");
			if (index.length == 2 && !index[1].equals("0")) {
				ProcessDomain domain = new ProcessDomain();
				domain.setName(getCellValue(row, 2));
				domain.setDescription("");
				domain.setDomainId("apqc_" + getCellValue(row, 0));
				domain.setValueChain(activityId);
				
				domain.getProcess().addAll(parseProcesses(row));
				
				domains.add(domain);
			}
		}
				
		return domains;
	}
	
	protected List<Process> parseProcesses(Row row) {
		List<Process> processes = new ArrayList<Process>();

		row = nextRow(row);
		while (row != null) {
			String index[] = getCellValue(row, 1).split("\\.");
			if (index.length == 3) {
				Process process = new Process();
				process.setProcessId("apqc_" + getCellValue(row, 0));
				process.setName(getCellValue(row, 2));
				process.setDescription("");
				
				process.getProcessComponent().addAll(parseComponents(row));
				
				processes.add(process);
			} else if (index.length < 3) {
				break;
			}
			row = nextRow(row);
		}
		
		return processes;
	}

	protected List<ProcessComponent> parseComponents(Row row) {
		List<ProcessComponent> components = new ArrayList<ProcessComponent>();

		row = nextRow(row);
		while (row != null) {
			String index[] = getCellValue(row, 1).split("\\.");
			if (index.length == 4) {
				ProcessComponent component = new ProcessComponent();
				component.setProcessComponentId("apqc_" + getCellValue(row, 0));
				component.setName(getCellValue(row, 2));
				component.setDescription("");
				
				components.add(component);
			} else if (index.length < 4) {
				break;
			}
			row = nextRow(row);
		}
		
		
		return components;
	}

	protected Row nextRow(Row row) {
		return row.getSheet().getRow(row.getRowNum() + 1);
	}
	
	protected String getCellValue(XSSFSheet sheet, int r, int c) {
		return getCellValue(sheet.getRow(r), c);
	}

	protected String getCellValue(Row row, int c) {
		if (row != null) {
			return getCellValue(row.getCell(c));
		}
		
		return "";
	}

	protected String getCellValue(Cell cell) {
		if (cell != null) {
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				Double d = cell.getNumericCellValue();
				return Integer.toString(d.intValue());
			}
		}
		
		return "";
	}
}
