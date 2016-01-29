package kaszucar.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kaszucar.model.User;
import kaszucar.util.HibernateUtil;

@Repository
public class UserRepository {
	
	Session openSession = HibernateUtil.getSessionFactory().openSession();

	public List<User> getUserByEmailAndPwd(String email, String password) {
		Criteria cr = openSession.createCriteria(User.class);
		cr.add(Restrictions.eq("email_adress", email));
		cr.add(Restrictions.eq("password", password));
		return cr.list();
	}
	
	public List<User> getUserByEmail(String email) {
		Criteria cr = openSession.createCriteria(User.class);
		cr.add(Restrictions.eq("email_adress", email));
		return cr.list();
	}

	public void insertUser(String gender, String name, String lastName, String email, String password, int yearBirth, String ipAdress) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        User user = new User(name,lastName,email,password,ipAdress,gender,yearBirth);
         
        session.save(user);
 
        session.getTransaction().commit();
        HibernateUtil.shutdown();
	}

}
