package edu.pragmatic.homework.xml.sax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class SaxDemo {
	public static void main(String[] args) throws SAXException,
			FileNotFoundException, IOException {

		XMLReader parser = XMLReaderFactory.createXMLReader();
		parser.setContentHandler(new StudenContent());

		File fileToParse = new File("resources/Students.xml");
		parser.parse(new InputSource(new FileReader(fileToParse)));
	}
}
