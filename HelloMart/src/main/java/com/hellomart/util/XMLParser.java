package com.hellomart.util;

import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	
	private static final Logger logger = LoggerFactory.getLogger(XMLParser.class);
	
	private Document doc;
	
	public XMLParser(String xmlFilePath) {
		doc = parseXML(xmlFilePath);
	}
	
	public Vector<String> getChildren(String tagName) throws Exception {
		NodeList descNodes = doc.getElementsByTagName(tagName);
 
		Vector<String> children = new Vector<String>();
		
		for (int i = 0; i < descNodes.getLength(); i++) {
			for (Node node = descNodes.item(i).getFirstChild(); 
					node != null; node = node.getNextSibling()) {
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					children.add(node.getNodeName());
				}
			}
		}
        
        return children;
	}
	
	
	
	public String getValue(String tagName) {
		NodeList descNodes = doc.getElementsByTagName(tagName);
		Node node = descNodes.item(0);
		return node.getTextContent();
	}
	
	
	
	private Document parseXML(String xmlFilePath) {
		DocumentBuilderFactory dcumentBuilderFactory = null;
        DocumentBuilder documentBuilder = null;
        Document doc = null;
 
        try{
            dcumentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = dcumentBuilderFactory.newDocumentBuilder();
            
            
            doc = documentBuilder.parse(xmlFilePath);
        } catch (ParserConfigurationException e){
        	logger.debug("ParseConfigurationException");
        } catch (SAXException | IOException e) {
        	logger.debug("XML File not found");
        }
 
        return doc;
	}
}
