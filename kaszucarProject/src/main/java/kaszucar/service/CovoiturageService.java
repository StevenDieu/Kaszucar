package kaszucar.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import kaszucar.model.Cars;
import kaszucar.model.Covoiturage;
import kaszucar.model.Preference;
import kaszucar.model.Users;

public interface CovoiturageService {
  public List<Cars> getAllCarsByUser(int idUser);

  public Cars getCarsByIdAndUser(int idCars, int idUser);

  public void getIdPreference(Preference preference);

  public void insertCars(Cars cars, Users user);

  public void insertCovoiturage(Covoiturage covoiturage, Users user);

  public void insertWaypoints(String[] waypoints, Covoiturage covoiturage);

  public List<Map<String, Object>> getAllCovoitByDestination(String from, String to, Date date);
}
