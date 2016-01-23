package Model;

public class User {
	private int idUser;
	private String name;
	private String lastName;
	private int phoneNumber;
	private String emailAdress;
	private String password;
	private String ipAdress;
	private String idAdress;
	private String title;
	private int yearOfBirth;
	private String description;
	private String urlPicture;

	public User(int idUser, String name, String lastName, int phoneNumber, String emailAdress, String password,
			String ipAdress, String idAdress, String title, int yearOfBirth, String description, String urlPicture) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAdress = emailAdress;
		this.password = password;
		this.ipAdress = ipAdress;
		this.idAdress = idAdress;
		this.title = title;
		this.yearOfBirth = yearOfBirth;
		this.description = description;
		this.urlPicture = urlPicture;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
