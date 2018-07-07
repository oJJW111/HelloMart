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
<<<<<<< HEAD

	
=======
>>>>>>> refs/remotes/origin/jsb
	
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
	
<<<<<<< HEAD
	
	
	public String getValue(String tagName) {
		Node firstNode = getFirstNode(tagName);
		return getValue(firstNode);
	}
	
	
	
	public String getValue(String parentTagName, String tagName) {
		Node uniqueNode = getUniqueNode(parentTagName, tagName);
		return getValue(uniqueNode);
	}
	
	

	private String getValue(Node node) {
		return node == null ? null : node.getTextContent();
	}
	
	
	
	public String getAttributeValue(String tagName, String attr) {
		Node firstNode = getFirstNode(tagName);
		NamedNodeMap nnm = firstNode.getAttributes();
		return getAttributeValue(nnm, attr);
	}
	
	
	
	public String getAttributeValue(String parentTagName, String tagName, String attr) {
		Node uniqueNode = getUniqueNode(parentTagName, tagName);
		NamedNodeMap nnm = uniqueNode.getAttributes();
		return getAttributeValue(nnm, attr);
	}
	
	
	
	private String getAttributeValue(NamedNodeMap nnm, String attr) {
		if(nnm != null) {
			Node node = nnm.getNamedItem(attr);
			if(node != null) {
				return node.getTextContent();
			}
		}
		return null;
	}
	
	
	
	public Vector<String> getChildren(String tagName) {
		NodeList nodeList = getNodeList(tagName);
		return getChildren(nodeList);
	}
	
	
	
	public Vector<String> getChildren(String parentTagName, String tagName) {
		Node uniqueNode = getUniqueNode(parentTagName, tagName);
		NodeList nodeList = getNodeList(uniqueNode.getNodeName());
		return getChildren(nodeList);
	}
	
	
	
	private Vector<String> getChildren(NodeList nodeList) {
		Vector<String> children = new Vector<>();
=======
	public String getValue(String parentTagName, String tagName) { 
		NodeList descNodes = doc.getElementsByTagName(tagName);
		Node node = null;
		for(int i=0; i<descNodes.getLength(); i++){
			Node temp = descNodes.item(i);
			
			if(parentTagName.equals(temp.getParentNode().getNodeName())){
				node = temp;
				break;
			}
		}
		
		return (node == null) ? null : node.getTextContent();
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
>>>>>>> refs/remotes/origin/jsb

<<<<<<< HEAD
		for (int i = 0; i < nodeList.getLength(); i++) {
			for (Node node = nodeList.item(i).getFirstChild(); node != null; node = node.getNextSibling()) {
=======
		Vector<String> children = new Vector<>();

		for (int i = 0; i < descNodes.getLength(); i++) {
			for (Node node = descNodes.item(i).getFirstChild(); node != null; node = node.getNextSibling()) {
>>>>>>> refs/remotes/origin/jsb
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					children.add(node.getNodeName());
				}
			}
		}
		
		return children;
	}
	
<<<<<<< HEAD
	
	
	private NodeList getNodeList(String tagName) {
		return doc.getElementsByTagName(tagName);
	}
	
	
	
	private Node getFirstNode(String tagName) {
		return getNodeList(tagName).item(0);
	}
	
	
	
	private Node getUniqueNode(String parentTagName, String tagName) {
		NodeList descNodes = getNodeList(tagName);
		for(int i = 0; i < descNodes.getLength(); i++) {
			Node node = descNodes.item(i);
			if(parentTagName.equals(node.getParentNode().getNodeName())) {
				return node;
			}
		}
		return null;
	}
	
}
=======
	private NodeList getNodeList(String tagName) {
		return doc.getElementsByTagName(tagName);
	}
	
	private Node getFirstNode(String tagName) {
		return getNodeList(tagName).item(0);
	}
	
}
>>>>>>> refs/remotes/origin/jsb
