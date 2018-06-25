package com.hellomart.util;

import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.InputStream;

public class XMLParser {
	private static final Logger logger = LoggerFactory.getLogger(XMLParser.class);
	final static int SEARCH_VALUE = 1000;
	final static int SEARCH_NAME = 1001;
	
	
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
			logger.debug("ParseConfigurationException"); 
		} 
		catch (SAXException | IOException e) { 
			logger.debug("XML File not found / " + xmlFilePath + " 파일 경로 확인"); 
        } 
		
		return doc;
	}
	
	public String getValue(String tagName) { 
		NodeList descNodes = doc.getElementsByTagName(tagName); 
		Node node = descNodes.item(0); 
		return getTextContent(node, SEARCH_VALUE); 
	} 
	
	public String getName(String tagName) { 
		NodeList descNodes = doc.getElementsByTagName(tagName); 
		Node node = descNodes.item(0); 
		return getTextContent(node, SEARCH_NAME); 
	} 
	
	public static String getTextContent(Node node, int whatSearch) throws DOMException {
		String textContent = "";
		//System.out.println("노드 이름 : " + node.getNodeName() + " / textContent1 : " + textContent.trim());
		
		if (node.getNodeType() == Node.ATTRIBUTE_NODE){
			textContent = node.getNodeValue(); 
			//System.out.println("노드 이름 : " + node.getNodeName() + " / textContent2 : " + textContent.trim());
		}
		else{
			Node child = node.getFirstChild();
			
			if (child != null){
				Node sibling = child.getNextSibling();
				
				if (sibling != null){
					StringBuffer sb = new StringBuffer();
					getTextContent(node, sb, whatSearch);
					textContent = sb.toString();
					//System.out.println("노드 이름 : " + node.getNodeName() + " / textContent3 : " + textContent.trim());
				}
				else{ 
					if (child.getNodeType() == Node.TEXT_NODE) {
						textContent = child.getNodeValue();
						//System.out.println("노드 이름 : " + child.getNodeName() + " / textContent4 : " + textContent.trim());
					}
					else{
						textContent = getTextContent(child, whatSearch);
						//System.out.println("노드 이름 : " + child.getNodeName() + " / textContent5 : " + textContent.trim());
					}
				}
			}
		}
		return textContent;
	}

	private static void getTextContent(Node node, StringBuffer sb, int whatSearch) throws DOMException {
		Node child = node.getFirstChild();
		while (child != null) {
			if (child.getNodeType() == Node.TEXT_NODE) {
				if(whatSearch == SEARCH_NAME){
					if(node.getNodeName() == "name"){
						sb.append(child.getNodeValue());
						//System.out.println("노드 이름 : " + child.getNodeName() + " / textContent6 : " + sb.toString().trim());
					}
				}
				else if(whatSearch == SEARCH_VALUE){
					if(node.getNodeName() != "name"){
						sb.append(child.getNodeValue());		
						//System.out.println("노드 이름 : " + child.getNodeName() + " / textContent7 : " + sb.toString().trim());				
					}
				}
			}
			else {
				getTextContent(child, sb, whatSearch);
			}
			child = child.getNextSibling();
		}
	}

	public Vector<String> getChildren(String tagName) throws Exception {
		NodeList descNodes = doc.getElementsByTagName(tagName);

		Vector<String> children = new Vector<String>();

		for (int i = 0; i < descNodes.getLength(); i++) {
			for (Node node = descNodes.item(i).getFirstChild(); node != null; node = node.getNextSibling()) {
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					children.add(node.getNodeName());
				}
			}
		}

		return children;
	}
}
