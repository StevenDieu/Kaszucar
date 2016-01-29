package kaszucar.service;

import org.hibernate.Session;

import kaszucar.util.HibernateUtil;

public class CovoiturageService {
	Session openSession = HibernateUtil.getSessionFactory().openSession();

	
	
}
