package uk.org.whitecottage.ea.apqc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class APQC2XML {

	public static void main(String[] args) {
		
		Marshaller marshaller = null;
		try {
		    JAXBContext fbdContext = JAXBContext.newInstance("uk.org.whitecottage.ea.gnosis.jaxb.framework");
		    marshaller = fbdContext.createMarshaller();
		} catch (JAXBException e) {
			System.err.println("Failed to create parser (JAXB problem)");
			e.printStackTrace();
		}

		String file = "APQC_PCF";
		if (args.length > 0) {
			file = args[0];
		}
		
		FileInputStream input;
		try {
			input = new FileInputStream(new File(file + ".xlsx"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file: " + file + ".xlsx");
			return;
		}
		
		FileOutputStream output;
		try {
			output = new FileOutputStream(new File("framework.xml"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file: framework.xml");
			try {
				input.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
    		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
			marshaller.marshal(apqc.getFramework(), output);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
