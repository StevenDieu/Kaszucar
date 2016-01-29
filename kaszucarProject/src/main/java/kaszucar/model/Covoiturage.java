package kaszucar.model;

import java.security.Timestamp;

public class Covoiturage {

	private Timestamp dateFirstTrip;
	private Timestamp dateReturnTrip;
	private int idCityFrom;
	private int idCityTo;
	private String description;
	private int price;
	private int idCars;
	private int sitNumber;
	private String sizeOfLuggage;

	public Covoiturage(Timestamp dateFirstTrip, Timestamp dateReturnTrip, int idCityFrom, int idCityTo,
			String description, int price, int idCars, int sitNumber, String sizeOfLuggage) {
		super();
		this.dateFirstTrip = dateFirstTrip;
		this.dateReturnTrip = dateReturnTrip;
		this.idCityFrom = idCityFrom;
		this.idCityTo = idCityTo;
		this.description = description;
		this.price = price;
		this.idCars = idCars;
		this.sitNumber = sitNumber;
		this.sizeOfLuggage = sizeOfLuggage;
	}

	public Timestamp getDateFirstTrip() {
		return dateFirstTrip;
	}

	public void setDateFirstTrip(Timestamp dateFirstTrip) {
		this.dateFirstTrip = dateFirstTrip;
	}

	public Timestamp getDateReturnTrip() {
		return dateReturnTrip;
	}

	public void setDateReturnTrip(Timestamp dateReturnTrip) {
		this.dateReturnTrip = dateReturnTrip;
	}

	public int getIdCityFrom() {
		return idCityFrom;
	}

	public void setIdCityFrom(int idCityFrom) {
		this.idCityFrom = idCityFrom;
	}

	public int getIdCityTo() {
		return idCityTo;
	}

	public void setIdCityTo(int idCityTo) {
		this.idCityTo = idCityTo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getIdCars() {
		return idCars;
	}

	public void setIdCars(int idCars) {
		this.idCars = idCars;
	}

	public int getSitNumber() {
		return sitNumber;
	}

	public void setSitNumber(int sitNumber) {
		this.sitNumber = sitNumber;
	}

	public String getSizeOfLuggage() {
		return sizeOfLuggage;
	}

	public void setSizeOfLuggage(String sizeOfLuggage) {
		this.sizeOfLuggage = sizeOfLuggage;
	}

}
