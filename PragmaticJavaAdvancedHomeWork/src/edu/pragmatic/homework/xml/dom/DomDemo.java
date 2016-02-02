package edu.pragmatic.homework.xml.dom;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

public class DomDemo {

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, TransformerFactoryConfigurationError,
			TransformerException {
		StringBuilder stringBuilder = new StringBuilder();

		File fileToParse = new File("resources/Students.xml");
		Document document = createDomDocument(fileToParse);

		Element root = document.getDocumentElement();
		String rootAttribute = root.getAttribute("duration");
		stringBuilder.append(String.format("%s %s\n", root.getNodeName(),
				rootAttribute));

		NodeList teacherList = document.getElementsByTagName("teacher");
		Element teacher = (Element) teacherList.item(0);
		stringBuilder.append(printTeacherInfo(teacher));

		NodeList studentList = document.getElementsByTagName("student");
		for (int i = 0; i < studentList.getLength(); i++) {
			Node node = studentList.item(i);

			Element eElement = (Element) node;
			stringBuilder.append(printStudentInfo(eElement));
		}

		System.out.println(stringBuilder.toString());

		addNewStuffToXML(document);
		saveToFileXML(document);
	}

	private static String printStudentInfo(Element eElement) {
		String attributeName = eElement.getAttribute("name");
		String attributeAge = eElement.getAttribute("age");
		String attributeGroup = eElement.getAttribute("group");
		String attributeSex = eElement.getAttribute("sex");
		String attributeMark = eElement.getAttribute("mark");

		return String.format(
				"Student name: %s age:%s group:%s sex:%s mark:%s\n",
				attributeName, attributeAge, attributeGroup, attributeSex,
				attributeMark);
	}

	private static String printTeacherInfo(Element eElement) {
		String attributeName = eElement.getAttribute("name");
		String discipline = eElement.getElementsByTagName("discipline").item(0)
				.getTextContent();
		return String.format("Teacher name: %s\nDiscipline: %s\n",
				attributeName, discipline);
	}

	private static Document createDomDocument(File fileToParse)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(fileToParse);
		return document;
	}

	/*
	 * Add to XML <exams> <exam teacher="1"> <students> <id>1</id> <id>2</id>
	 * <id>3</id> </students> </exam> </exams>
	 */
	private static void addNewStuffToXML(Document document)
			throws TransformerFactoryConfigurationError, TransformerException {

		Element root = document.getDocumentElement();
		Element exams = document.createElement("exams");
		root.appendChild(exams);

		int numberOfTeacher = 3;
		for (int i = 1; i <= numberOfTeacher; i++) {
			Element exam = document.createElement("exam");
			exam.setAttribute("teacher", String.valueOf(i));
			exams.appendChild(exam);

			Element students = document.createElement("students");
			Random random = new Random();
			int numberOfStudent = random.nextInt(5) + 1;

			Stack<Integer> uniqueID = new Stack<Integer>();
			for (int j = 0; j < numberOfStudent; j++) {
				int studentID = random.nextInt(5) + 1;
				if (!uniqueID.contains(studentID)) {
					uniqueID.add(studentID);

					Element id = document.createElement("id");
					id.setTextContent(String.valueOf(studentID));
					students.appendChild(id);
				}
			}
			exam.appendChild(students);
		}
	}

	private static void saveToFileXML(Document document)
			throws TransformerFactoryConfigurationError, TransformerException {
		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		DOMSource domSource = new DOMSource(document);
		StreamResult streamResult = new StreamResult(new File(
				"resources/New_Students.xml"));
		transformer.transform(domSource, streamResult);
		System.out.println("File saved");
	}
}
