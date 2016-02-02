package edu.pragmatic.homework.xml.jaxb;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXB;

import edu.pragmatic.homework.xml.jaxb.Education.People;
import edu.pragmatic.homework.xml.jaxb.Education.People.Students;
import edu.pragmatic.homework.xml.jaxb.Education.People.Students.Student;
import edu.pragmatic.homework.xml.jaxb.Education.People.Teacher;

public class JaxbDemo {

	public static void main(String[] args) {
		File xmlFile = new File("resources/Students.xml");

		Education education = JAXB.unmarshal(xmlFile, Education.class);

		System.out.printf("Duration: %s\n",education.getDuration());
		People people = education.getPeople();
		Teacher teacherElements = people.getTeacher();
		System.out.printf("Teacher name: %s\nDiscipline: %s\n",
				teacherElements.getName(), teacherElements.getDiscipline());

		Students studentsElements = people.getStudents();
		List<Student> students = studentsElements.getStudent();
		for (Student student : students) {
			System.out.printf(
					"Student name: %s age:%s group:%s sex:%s mark:%s\n",
					student.getName(), student.getAge(), student.getGroup(),
					student.getSex(), student.getMark());
		}
		
		ObjectFactory factory = new ObjectFactory();

	}
}
