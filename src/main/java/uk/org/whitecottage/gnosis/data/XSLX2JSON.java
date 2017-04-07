package uk.org.whitecottage.gnosis.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSLX2JSON {

	public static void main(String[] args) {
		
		String file = "gnosis";
		if (args.length > 0) {
			file = args[0];
		}
		
		FileInputStream input;
		try {
			input = new FileInputStream(new File("xlsx/" + file + ".xlsx"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file: " + e.getMessage());
			return;
		}
		
		XSSFWorkbook xlsx = null;
		try {
			xlsx = new XSSFWorkbook(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		GnosisXSLX gnosis = new GnosisXSLX(xlsx);
		
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("json/process-taxonomy.json"))));
			writer.write(gnosis.getTaxonomy("ProcessTaxonomy").toJSON());
			writer.close();

			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("json/application-taxonomy.json"))));
			writer.write(gnosis.getTaxonomy("ApplicationTaxonomy").toJSON());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
