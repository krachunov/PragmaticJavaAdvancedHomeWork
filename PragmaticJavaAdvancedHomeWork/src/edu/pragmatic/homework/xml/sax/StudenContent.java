package edu.pragmatic.homework.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class StudenContent implements ContentHandler {

	private boolean inElement = false;
	private String currentElement;
	private Attributes atribute;

	private boolean expectElement(String qName) {
		return (qName.equals("education") || qName.equals("teacher")
				|| qName.equals("student") || qName.equals("discipline"));
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		inElement = expectElement(qName);
		currentElement = qName;
		atribute = atts;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		inElement = !expectElement(qName);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (inElement) {
			StringBuilder sb = new StringBuilder();
			String cc = new String(ch, start, length).trim();

			if (!cc.equals("")) {
				sb.append(String.format("%s: ", cc));
			} else {
				for (int i = 0; i < atribute.getLength(); i++) {
					sb.append(String.format("%s: %s ",
							atribute.getLocalName(i), atribute.getValue(i)));
				}
			}
			System.out.printf("%s %s\n", currentElement, sb.toString());
		}
	}

	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
	}

	@Override
	public void setDocumentLocator(Locator locator) {
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
	}

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
	}

}
