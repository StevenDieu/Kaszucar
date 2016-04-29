package kaszucar.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import kaszucar.model.Cars;
import kaszucar.model.CityWaypoints;
import kaszucar.model.Covoiturage;
import kaszucar.model.Preference;
import kaszucar.model.UsersHasCars;
import kaszucar.model.UsersHasCovoiturage;
import kaszucar.util.HibernateUtil;

@SuppressWarnings("unchecked")
@Repository
public class CovoiturageRepository {

  /**
   * Requête permettant de récupérer toutes les voitures en fonction d'un utilisateur
   * 
   * @param idUser
   * @return
   */
  public List<UsersHasCars> getAllCarsByUser(int idUser) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(UsersHasCars.class);
    cr.createAlias("cars", "c");
    cr.createAlias("users", "u");

    cr.add(Restrictions.eq("u.idUsers", idUser));

    List<UsersHasCars> listUsersHasCars = cr.list();
    
    openSession.close();
    HibernateUtil.shutdown();
    return listUsersHasCars;
  }

  /**
   * Requête permettant de récupérer une voitures en fonction d'un utilisateur
   * 
   * @param idCars
   * @param idUser
   * @return
   */
  public List<UsersHasCars> getCarsByIdAndUser(int idCars, int idUser) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(UsersHasCars.class);
    cr.createAlias("cars", "c");
    cr.createAlias("users", "u");

    cr.add(Restrictions.eq("c.idCars", idCars));
    cr.add(Restrictions.eq("u.idUsers", idUser));

    List<UsersHasCars> listUsersHasCars = cr.list();
    
    openSession.close();
    HibernateUtil.shutdown();
    return listUsersHasCars;
  }

  /**
   * Requête permettant de récupérer l'id préférence en fonction de :
   * animals
   * food
   * detour
   * musics
   * smoking
   * 
   * @param preference
   * @return
   */
  public List<Preference> getIdPreference(Preference preference) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Preference.class);
    cr.add(Restrictions.eq("animals", preference.getAnimals()));
    cr.add(Restrictions.eq("food", preference.getFood()));
    cr.add(Restrictions.eq("detour", preference.getDetour()));
    cr.add(Restrictions.eq("musics", preference.getMusics()));
    cr.add(Restrictions.eq("smoking", preference.getSmoking()));
    List<Preference> listPreference = cr.list();
    
    openSession.close();
    HibernateUtil.shutdown();
    return listPreference;
  }

  /**
   * Requête permettant d'insérer une voiture
   * 
   * @param cars
   * @param userHasCar
   */
  public void insertCars(Cars cars, UsersHasCars userHasCar) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Transaction tx = openSession.beginTransaction();

    try {
      openSession.save(cars);
      userHasCar.setCars(cars);
      openSession.save(userHasCar);

      tx.commit();
    } catch (RuntimeException e) {
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

  /**
   * Requête permettant d'insérer un covoiturage
   * 
   * @param covoiturage
   * @param usersHasCovoiturage
   */
  public void insertCovoiturage(Covoiturage covoiturage, UsersHasCovoiturage usersHasCovoiturage) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Transaction tx = openSession.beginTransaction();

    try {
      openSession.save(covoiturage);
      usersHasCovoiturage.setCovoiturage(covoiturage);
      openSession.save(usersHasCovoiturage);

      tx.commit();
    } catch (RuntimeException e) {
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();

  }

  /**
   * Requête permettant d'insérer un Waypoints
   * 
   * @param cityWaypoints
   */
  public void insertWaypoint(CityWaypoints cityWaypoints) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Transaction tx = openSession.beginTransaction();

    try {
      openSession.save(cityWaypoints);

      tx.commit();
    } catch (RuntimeException e) {
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

  /**
   * Requete permettant de récupérer tous les covoiturage par destination (cityFrom et cityTo)
   * 
   * @param from
   * @param to
   * @param date
   * @return
   */
  public List<Covoiturage> getAllCovoitByDestination(String from, String to, Date date) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Covoiturage.class);

    cr.createAlias("cityWaypointses", "cw", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("preference", "pr", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("cars", "ca", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("usersHasCovoiturages", "uhc", JoinType.LEFT_OUTER_JOIN);

    cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    
    cr.add(Restrictions.eq("cityFrom", from));
    cr.add(Restrictions.eq("cityTo", to));
    cr.addOrder( Order.asc("dateFirstTrip") );

     if (date != null) {
       Calendar cal = Calendar.getInstance();
       cal.setTime(date);
       cal.add(Calendar.DATE, 1);
       cr.add(Restrictions.between("dateFirstTrip", date, cal.getTime()));
     }else{
       cr.add(Restrictions.gt("dateFirstTrip", new Date()));
     }

    List<Covoiturage> listCovoiturage = cr.list();

    openSession.close();
    HibernateUtil.shutdown();
    return listCovoiturage;
  }

  /**
   * Repère l'utilisateur avec l'id UsersHasCovoiturage
   * 
   * @param idUsersHasCovoiturage
   * @return
   */
  public UsersHasCovoiturage getUserByIdUsersHasCovoiturage(int idUsersHasCovoiturage) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(UsersHasCovoiturage.class);
    cr.createAlias("users", "u", JoinType.LEFT_OUTER_JOIN);
    cr.add(Restrictions.eq("idUsersHasCovoiturage", idUsersHasCovoiturage));
    cr.add(Restrictions.eq("passagers", false));

    UsersHasCovoiturage usersHasCovoiturage = (UsersHasCovoiturage) cr.list().get(0);
    
    openSession.close();
    HibernateUtil.shutdown();
    return usersHasCovoiturage;
  }

}
