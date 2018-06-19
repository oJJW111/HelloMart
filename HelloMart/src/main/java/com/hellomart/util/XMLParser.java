package com.hellomart.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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

import com.hellomart.controller.AccountController;

public class XMLParser {
	
	private static final Logger logger = LoggerFactory.getLogger(XMLParser.class);
	
	public Vector<String> getChildren(String tagName) throws Exception {
		URL url = null;
		try {
			url = new URL("category.xml");
		} catch (MalformedURLException e) {
			logger.debug("XML url not found");
		}
		
		URLConnection connection = url.openConnection();
        
		Document doc = parseXML(connection.getInputStream());

		NodeList descNodes = doc.getElementsByTagName(tagName);
 
		Vector<String> children = new Vector<String>();
		
        for(int i=0; i<descNodes.getLength();i++){
            for(Node node = descNodes.item(i).getFirstChild(); node!=null; node=node.getNextSibling()) {
            	children.add(node.getNodeName());
            }
        }
        
        return children;
	}
	
	private Document parseXML(InputStream stream) {
		DocumentBuilderFactory dcumentBuilderFactory = null;
        DocumentBuilder documentBuilder = null;
        Document doc = null;
 
        try{
            dcumentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = dcumentBuilderFactory.newDocumentBuilder();
 
            doc = documentBuilder.parse(stream);
        } catch (ParserConfigurationException e){
        } catch (SAXException | IOException e) {
        }
 
        return doc;
	}
}
