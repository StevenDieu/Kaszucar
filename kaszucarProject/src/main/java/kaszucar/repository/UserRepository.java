package kaszucar.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kaszucar.model.Users;
import kaszucar.util.HibernateUtil;

@SuppressWarnings("unchecked")
@Repository
public class UserRepository {

	Session openSession = HibernateUtil.getSessionFactory().openSession();

	/**
	 * Permet de récupérer le user par email et le mot de passe
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public List<Users> getUserByEmailAndPwd(String email, String password) {
		Criteria cr = openSession.createCriteria(Users.class);
		cr.add(Restrictions.eq("emailAdress", email));
		cr.add(Restrictions.eq("password", password));
		return cr.list();
	}

	/**
     * Permet de récupérer le user par email
	 * 
	 * @param email
	 * @return
	 */
	public List<Users> getUserByEmail(String email) {
		Criteria cr = openSession.createCriteria(Users.class);
		cr.add(Restrictions.eq("emailAdress", email));
		return cr.list();
	}

	/**
	 * Permet d'ajouter un utilisateur
	 * 
	 * @param users
	 */
	public void insertUser(Users users) {
		Transaction tx = openSession.beginTransaction();

		openSession.save(users);

		tx.commit();
	}

}
