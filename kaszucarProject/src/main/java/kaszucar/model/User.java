package kaszucar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_users")
	private int idUser;

	@Column(name = "name", length = 255)
	private String name;

	@Column(name = "last_name", length = 255)
	private String lastName;

	@Column(name = "phone_number")
	private int phoneNumber;

	@Column(name = "email_adress", length = 255)
	private String emailAdress;

	@Column(name = "password", length = 255)
	private String password;

	@Column(name = "ip_adress")
	private String ipAdress;

	@Column(name = "id_adress")
	private String idAdress;

	@Column(name = "genre")
	private String genre;

	@Column(name = "year_of_birth")
	private int yearOfBirth;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "url_picture", length = 1024)
	private String urlPicture;

	public User() {

	}

	public User(String name, String lastName, String emailAdress, String password,
			String ipAdress, String genre, int yearOfBirth) {
		this.name = name;
		this.lastName = lastName;
		this.emailAdress = emailAdress;
		this.password = password;
		this.ipAdress = ipAdress;
		this.genre = genre;
		this.yearOfBirth = yearOfBirth;
	}

	
	public int getIdUser() {
		return idUser;
	}

	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getLastName() {
		return lastName;
	}

	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public int getPhoneNumber() {
		return phoneNumber;
	}

	
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public String getEmailAdress() {
		return emailAdress;
	}

	
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getIpAdress() {
		return ipAdress;
	}

	
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	
	public String getIdAdress() {
		return idAdress;
	}

	
	public void setIdAdress(String idAdress) {
		this.idAdress = idAdress;
	}

	
	public String getGenre() {
		return genre;
	}

	
	public void setGenre(String genre) {
		this.genre = genre;
	}

	
	public int getYearOfBirth() {
		return yearOfBirth;
	}

	
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getUrlPicture() {
		return urlPicture;
	}

	
	public void setUrlPicture(String urlPicture) {
		this.urlPicture = urlPicture;
	}
}
