package kaszucar.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kaszucar.model.Preference;
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

  public List<UsersHasCars> getCarsByIdAndUser(int idCars, int idUser) {
    Criteria cr = openSession.createCriteria(UsersHasCars.class);
    cr.createAlias("cars", "c");
    cr.createAlias("users", "u");
    
    cr.add(Restrictions.eq("c.idCars", idCars));
    cr.add(Restrictions.eq("u.idUsers", idUser));

    return cr.list();
  }

  public List<Preference> getIdPreference(Preference preference) {
    Criteria cr = openSession.createCriteria(Preference.class);
    cr.add(Restrictions.eq("animals", preference.getAnimals()));
    cr.add(Restrictions.eq("food", preference.getFood()));
    cr.add(Restrictions.eq("detour", preference.getDetour()));
    cr.add(Restrictions.eq("musics", preference.getMusics()));
    cr.add(Restrictions.eq("smoking", preference.getSmoking()));
  
    return cr.list();
  }

	
}
