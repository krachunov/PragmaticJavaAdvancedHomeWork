//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.09 at 03:55:10 PM EET 
//


package edu.pragmatic.homework.xml.jaxb;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: generated
	 * 
	 */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Education }
     * 
     */
    public Education createEducation() {
        return new Education();
    }

    /**
     * Create an instance of {@link Education.People }
     * 
     */
    public Education.People createEducationPeople() {
        return new Education.People();
    }

    /**
     * Create an instance of {@link Education.People.Students }
     * 
     */
    public Education.People.Students createEducationPeopleStudents() {
        return new Education.People.Students();
    }

    /**
     * Create an instance of {@link Education.People.Teacher }
     * 
     */
    public Education.People.Teacher createEducationPeopleTeacher() {
        return new Education.People.Teacher();
    }

    /**
     * Create an instance of {@link Education.People.Students.Student }
     * 
     */
    public Education.People.Students.Student createEducationPeopleStudentsStudent() {
        return new Education.People.Students.Student();
    }

}