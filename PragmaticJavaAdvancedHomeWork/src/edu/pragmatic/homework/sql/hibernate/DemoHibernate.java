package edu.pragmatic.homework.sql.hibernate;

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
		listDepartments(session);
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
	private static void listDepartments(Session session) {
		System.out.println("Employees:");
		Criteria allStudentsCriteria = session.createCriteria(Employees.class);
		List<Employees> allEmployees = allStudentsCriteria.list();
		for (Employees employee : allEmployees) {
			System.out.printf(" - Id=%d, Name=%s\n", employee.getEmpNo(),
					employee.getFirstName());
		}
	}
}
