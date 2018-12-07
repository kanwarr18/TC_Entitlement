package com;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Eval_creator {

	public static void main(String[] args) throws ParserConfigurationException {
		// TODO Auto-generated method stub

		
		 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		 Document doc = docBuilder.newDocument();
		   Element rootElement = doc.createElement("eval_sw_orgs");
		    doc.appendChild(rootElement);
		    Element district = doc.createElement("eval_sw_org_district");
		  

		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    try {
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
			    StreamResult result = new StreamResult(new File("/D:/ENT-16447/new.xml"));
			    transformer.transform(source, result);
			    System.out.println("File saved!");
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    
			}   
		 

		 
		
	}

}
