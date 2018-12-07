package com;


import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Eval_creator2 {
	
	public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chrome_driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		
		String evalString = null;
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();

	    Document document = db.newDocument();

	    Element book = document.createElement("eval_sw_org_district"); 
	    
	    book.setAttribute("areacode", "407");
	    book.setAttribute("ftype", "05");
	    book.setAttribute("mailing_city", "Middleburg");
	    book.setAttribute("mailing_state", "DC");
	    book.setAttribute("mailing_street", "123 Main Street");
	    book.setAttribute("mailing_zip", "12345");
	    book.setAttribute("mailing_zipext", "1111");
	    book.setAttribute("name", "Middleburg County Schools");
	    book.setAttribute("phy_city", "Middleburg");
	    book.setAttribute("phy_state", "DC");
	    book.setAttribute("phy_street", "123 Main Street");
	    book.setAttribute("school_type", "E");
	    book.setAttribute("timezone", "8");
	    book.setAttribute("org_type", "D");
	    
	    document.appendChild(book);   

	    String file = "D:\\ENT-1000\\ENT-1000.txt";
	    BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file));
	    String line = null;
	    line = bufferedReader.readLine();
	    while(line!=null) {    
        Element title = document.createElement("eval_sw_product");
        title.appendChild(document.createTextNode(line));
        book.appendChild(title);
        line = bufferedReader.readLine();
	    }
	    bufferedReader.close();
	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer = tf.newTransformer();
	    //Files.createDirectory(Paths.get("D:\\ENT-16448"));
	    DOMSource source = new DOMSource(document);          
	    //StreamResult result = new StreamResult(System.out);
	    StreamResult result1 = new StreamResult(new File("D:\\ENT-1000\\rkan.xml"));

	    
	   // System.out.println(evalString);
	    
	    try {
			transformer.transform(source, result1);
			Zipper.Zip();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	     System.out.println("Eval creator done");
	    
	 
	}
}
