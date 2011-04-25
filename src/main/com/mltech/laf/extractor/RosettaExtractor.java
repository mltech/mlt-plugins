package com.mltech.laf.extractor;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.mltech.laf.document.BitextDocument;
import com.mltech.laf.document.IDocument;
import com.mltech.laf.extractor.IDocumentExtractor;


// TODO: annotate with part annotation
public class RosettaExtractor implements IDocumentExtractor {
	private XPath _xpath;
	private DocumentBuilder _db;
	private InputSource _is;

	public RosettaExtractor() {
		_xpath = XPathFactory.newInstance().newXPath();
		try {
			_db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			_is = new InputSource();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public IDocument extract(String text) {
		String text1 = "";
		String text2 = "";
		try {
			text = text.substring(39);
			_is.setCharacterStream(new StringReader(text));
//			System.out.println("::" + text);
			org.w3c.dom.Document xmlDocument = _db.parse(_is);
			Element xml = xmlDocument.getDocumentElement();
			text1 = ((Node) _xpath.compile("/rosetta_doc/SDOAB/abeng").evaluate(xml, XPathConstants.NODE)).getTextContent().trim();
			text2 = ((Node) _xpath.compile("/rosetta_doc/SDOAB/abchi").evaluate(xml, XPathConstants.NODE)).getTextContent().trim();
//			System.out.println(text1);
//			System.out.println(text2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new BitextDocument(text1, text2);
	}
}
