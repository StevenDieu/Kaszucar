package kaszucar.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    usersHasCovoiturage.setPassagers(false);
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

  public List<Map<String, Object>> getAllCovoitByDestination(String from, String to, Date date) {
     List<Covoiturage> listCovoiturage = CR.getAllCovoitByDestination(from,to,date);
     List<Map<String,Object>> listCovoiturageWithUser = new ArrayList<Map<String,Object>>();

     for(Covoiturage covoiturage : listCovoiturage){
       SimpleDateFormat formaterDate = new SimpleDateFormat("EEEE, d MMMMM yyyy");
       SimpleDateFormat formaterHours = new SimpleDateFormat("HH:mm");

       Users user = null;
       int reserve = -1;
       for(UsersHasCovoiturage usersHasCovoit : covoiturage.getUsersHasCovoiturages()){
         user = CR.getUserByIdUsersHasCovoiturage(usersHasCovoit.getIdUsersHasCovoiturage()).getUsers();
         reserve++;
       }
       Map<String,Object> mapCovoit = new HashMap<String,Object>();
       
       mapCovoit.put("sitNumber", covoiturage.getSitNumber() - reserve);
       mapCovoit.put("place", convertSitNumber(covoiturage.getSitNumber() - reserve));
       mapCovoit.put("sizeOfLuggage", convertSizeOfLugage(covoiturage.getSizeOfLuggage()));
       mapCovoit.put("hours", formaterHours.format(covoiturage.getDateFirstTrip()));
       mapCovoit.put("date", formaterDate.format(covoiturage.getDateFirstTrip()));
       mapCovoit.put("covoiturage", covoiturage);
       mapCovoit.put("user", user);

       listCovoiturageWithUser.add(mapCovoit);
     }

     return listCovoiturageWithUser;
  }

  private String convertSitNumber(int sitNumber) {
    if(sitNumber == 0){
      return "Complet";
    }else if(sitNumber == 1){
      return "1 place restante";
    }else{
      return sitNumber + " places restantes";
    }
  }

  private String convertSizeOfLugage(String sizeOfLuggage) {
    if(sizeOfLuggage.equals("little")){
      return "Petit";
    }else if(sizeOfLuggage.equals("medium")) {
      return "Moyen";
    }else{
      return "Gros";
    }
  }

}
