package kaszucar.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kaszucar.model.UsersHasCars;
import kaszucar.util.HibernateUtil;

@SuppressWarnings("unchecked")
@Repository
public class CovoiturageRepository {

	Session openSession = HibernateUtil.getSessionFactory().openSession();

	public List<UsersHasCars> getAllCarsByUser(int idUser) {
		Criteria cr = openSession.createCriteria(UsersHasCars.class);
		cr.createAlias("cars", "c");
		cr.createAlias("users", "u");

		cr.add(Restrictions.eq("u.idUsers", idUser));

		return cr.list();
	}

	
}
