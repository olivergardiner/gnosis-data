package uk.org.whitecottage.gnosis.apqc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class APQC2Gnosis {

	public static void main(String[] args) {
		
		String file = "APQC_PCF";
		if (args.length > 0) {
			file = args[0];
		}
		
		FileInputStream input;
		FileOutputStream output;
		try {
			input = new FileInputStream(new File("apqc/" + file + ".xlsx"));
			output = new FileOutputStream(new File("xlsx/gnosis.xlsx"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file: " + e.getMessage() + ".xlsx");
			return;
		}
		
		XSSFWorkbook xlsx = null;
		try {
			xlsx = new XSSFWorkbook(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		APQC apqc = new APQC(xlsx);
		
		try {
			apqc.getProcesses().write(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
