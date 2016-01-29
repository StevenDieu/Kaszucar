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
	private int id_users;

	@Column(name = "name", length = 255)
	private String name;

	@Column(name = "last_name", length = 255)
	private String last_name;

	@Column(name = "phoneNumber")
	private int phoneNumber;

	@Column(name = "email_adress", length = 255)
	private String email_adress;

	@Column(name = "password", length = 255)
	private String password;

	@Column(name = "ip_adress")
	private String ip_adress;

	@Column(name = "id_adress")
	private String id_adress;

	@Column(name = "genre")
	private String genre;

	@Column(name = "year_of_birth")
	private int year_of_birth;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "url_picture", length = 1024)
	private String url_picture;

	public User() {

	}

	public User(String name, String last_name, String email_adress, String password,
			String ip_adress, String genre, int year_of_birth) {
		this.name = name;
		this.last_name = last_name;
		this.email_adress = email_adress;
		this.password = password;
		this.ip_adress = ip_adress;
		this.genre = genre;
		this.year_of_birth = year_of_birth;
	}

	public int getId_user() {
		return id_users;
	}

	public void setId_user(int id_user) {
		this.id_users = id_user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail_adress() {
		return email_adress;
	}

	public void setEmail_adress(String email_adress) {
		this.email_adress = email_adress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp_adress() {
		return ip_adress;
	}

	public void setIp_adress(String ip_adress) {
		this.ip_adress = ip_adress;
	}

	public String getId_adress() {
		return id_adress;
	}

	public void setId_adress(String id_adress) {
		this.id_adress = id_adress;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear_of_birth() {
		return year_of_birth;
	}

	public void setYear_of_birth(int year_of_birth) {
		this.year_of_birth = year_of_birth;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl_picture() {
		return url_picture;
	}

	public void setUrl_picture(String url_picture) {
		this.url_picture = url_picture;
	}
}
