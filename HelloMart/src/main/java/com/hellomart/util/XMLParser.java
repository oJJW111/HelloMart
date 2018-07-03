package com.hellomart.util;

import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.InputStream;

public class XMLParser {
	
	private static final Logger logger = LoggerFactory.getLogger(XMLParser.class);
	
	private Document doc;
	
	
	
	public XMLParser(String xmlFilePath){
		doc = parseXML(xmlFilePath);
	}

	
	
	private Document parseXML(String xmlFilePath) {
		DocumentBuilderFactory documentBuilderFactory = null;
		DocumentBuilder documentBuilder = null;
		Document doc = null;
		
		try{
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(xmlFilePath);
			
			doc = documentBuilder.parse(is); 
		}
		catch (ParserConfigurationException e){ 
			logger.debug(e.getMessage()); 
		} 
		catch (SAXException | IOException e) { 
			logger.debug("XML File not found / " + xmlFilePath + " 파일 경로 확인"); 
        } 
		
		return doc;
	}
	
	
	
	public String getValue(String tagName) { 
		return getFirstNode(tagName).getTextContent();
	}
	
	
	
	public String getAttributeValue(String tagName, String attr) {
		NamedNodeMap nnm = getFirstNode(tagName).getAttributes();
		if(nnm != null) {
			Node p = nnm.getNamedItem(attr);
			if(p != null) {
				return p.getTextContent();
			}
		}
		return null;
	}
	
	
	
	public Vector<String> getChildren(String tagName) {
		NodeList descNodes = getNodeList(tagName);

		Vector<String> children = new Vector<>();

		for (int i = 0; i < descNodes.getLength(); i++) {
			for (Node node = descNodes.item(i).getFirstChild(); node != null; node = node.getNextSibling()) {
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					children.add(node.getNodeName());
				}
			}
		}

		return children;
	}
	
	
	
	private NodeList getNodeList(String tagName) {
		return doc.getElementsByTagName(tagName);
	}
	
	
	
	private Node getFirstNode(String tagName) {
		return getNodeList(tagName).item(0);
	}
	
}
