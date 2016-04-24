package kaszucar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kaszucar.model.Cars;
import kaszucar.model.CityWaypoints;
import kaszucar.model.Covoiturage;
import kaszucar.model.Preference;
import kaszucar.model.Users;
import kaszucar.model.UsersHasCars;
import kaszucar.model.UsersHasCovoiturage;
import kaszucar.repository.CovoiturageRepository;

@Service
public class ImplCovoiturageService implements CovoiturageService {
  @Autowired
  private CovoiturageRepository CR;

  public List<Cars> getAllCarsByUser(int idUser) {
    List<UsersHasCars> UserHasCars = CR.getAllCarsByUser(idUser);
    List<Cars> cars = new ArrayList<Cars>();
    for (UsersHasCars UsersHasCar : UserHasCars) {
      cars.add(UsersHasCar.getCars());
    }
    return cars;
  }

  public Cars getCarsByIdAndUser(int idCars, int idUser) {
    List<UsersHasCars> UserHasCars = CR.getCarsByIdAndUser(idCars, idUser);

    return UserHasCars.get(0).getCars();
  }

  public void getIdPreference(Preference preference) {
    preference.setIdPreference(CR.getIdPreference(preference).get(0).getIdPreference());
  }

  public void insertCars(Cars cars, Users user) {
    UsersHasCars UsersHasCar = new UsersHasCars();
    UsersHasCar.setUsers(user);
    CR.insertCars(cars, UsersHasCar);

  }

  public void insertCovoiturage(Covoiturage covoiturage, Users user) {
    UsersHasCovoiturage usersHasCovoiturage = new UsersHasCovoiturage();
    usersHasCovoiturage.setUsers(user);
    CR.insertCovoiturage(covoiturage, usersHasCovoiturage);
  }

  public void insertWaypoints(String[] waypoints, Covoiturage covoiturage) {
    if(waypoints != null){
      for (String waypoint : waypoints) {
        CityWaypoints cityWaypoints = new CityWaypoints();
        cityWaypoints.setCityWaypoints(waypoint);
        cityWaypoints.setCovoiturage(covoiturage);
        CR.insertWaypoint(cityWaypoints);
      }
    }

  }

}
