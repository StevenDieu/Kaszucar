package kaszucar.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import kaszucar.model.Users;

@Service
public interface UserService {

  public Users connexion(String email, String password);

  public Users register(String gender, String name, String lastName, String emailAdress,
      String password, short yearOfBirth, String ipAdress);

  public boolean checkEmail(String email);

  public boolean isEmailAdress(String email);

  public String getIpAdresse(HttpServletRequest request);

  public boolean checkYear18(int yearBirth);

}
