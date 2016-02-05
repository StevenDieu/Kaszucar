package kaszucar.model;
// Generated 4 f�vr. 2016 18:26:01 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CovoiturageId generated by hbm2java
 */
@Embeddable
public class CovoiturageId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCovoiturage;
	private int idPreference;

	public CovoiturageId() {
	}

	public CovoiturageId(int idCovoiturage, int idPreference) {
		this.idCovoiturage = idCovoiturage;
		this.idPreference = idPreference;
	}

	@Column(name = "id_covoiturage", nullable = false)
	public int getIdCovoiturage() {
		return this.idCovoiturage;
	}

	public void setIdCovoiturage(int idCovoiturage) {
		this.idCovoiturage = idCovoiturage;
	}

	@Column(name = "id_preference", nullable = false)
	public int getIdPreference() {
		return this.idPreference;
	}

	public void setIdPreference(int idPreference) {
		this.idPreference = idPreference;
	}

	public boolean equals(Object other) {
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof CovoiturageId)) return false;
		CovoiturageId castOther = (CovoiturageId) other;

		return (this.getIdCovoiturage() == castOther.getIdCovoiturage())
				&& (this.getIdPreference() == castOther.getIdPreference());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdCovoiturage();
		result = 37 * result + this.getIdPreference();
		return result;
	}

}
