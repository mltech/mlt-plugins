package com.mltech.laf.extractor;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;


import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.mltech.laf.document.Document;

public final class XMLPartExtractor {
	public static Document createDocument(URL path, String xPath)
			throws Exception {
		Document document = new Document();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		builder.setEntityResolver(new EntityResolver() {

			public InputSource resolveEntity(String publicId, String systemId)
					throws SAXException, IOException {
				return new InputSource(new StringReader(""));
			}
		});
		org.w3c.dom.Document domDocument = builder.parse(path.toString());
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		String text = xpath.evaluate(xPath, domDocument.getDocumentElement());
		document.setText(text);
		return document;
	}
}
