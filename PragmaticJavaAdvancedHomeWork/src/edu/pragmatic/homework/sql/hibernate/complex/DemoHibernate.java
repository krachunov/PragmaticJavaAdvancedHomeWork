package edu.pragmatic.homework.sql.hibernate.complex;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DemoHibernate {

	public static void main(String[] args) {
	
		SessionFactory factory = createSessionFactory();
		Session session = factory.openSession();
		try {
			 listEmployees(session);
//			createEmployee(session);
			
		} finally {
			session.close();
		}
	}

	public static SessionFactory createSessionFactory() {
		Configuration cfg = new Configuration();
		cfg.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(cfg.getProperties()).build();
		SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
		return factory;
	}

	@SuppressWarnings("unchecked")
	private static void listEmployees(Session session) {
		System.out.println("Employees:");
		Criteria allStudentsCriteria = session.createCriteria(Employee.class);
		List<Employee> allEmployees = allStudentsCriteria.list();
		for (Employee employee : allEmployees) {
			System.out.printf(" - Id=%d, Name=%s Last name=%s\n", employee.getEmpNo(),
					employee.getFirstName(),employee.getLastLame());
		}
	}
	private static void createEmployee(Session session) {
		try {
			session.beginTransaction();
			System.out.println("-- Starting new transaction.");
			
			Employee emp = new Employee();
			emp.setEmpNo(10000);
			emp.setPersonId(999954139L);
			emp.setFirstName("ICO");
			emp.setLastLame("KRACHUNVO");
			emp.setBirthDate("2000-10-10");
			emp.setHireDate("2000-10-10");
			emp.setGender("M");	
			session.save(emp);
			System.out.println("Emp inserted: " + emp.getEmpNo());
			
			session.getTransaction().commit();
			System.out.println("-- Transaction committed.");
		} catch (RuntimeException e) {
			System.out.println("-- Transaction failed.");
			session.getTransaction().rollback();
			throw e;
		}
	}
}
