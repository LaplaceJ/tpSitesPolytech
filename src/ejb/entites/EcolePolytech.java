package ejb.entites;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity(name = "ecole")
public class EcolePolytech implements Serializable{
	
	@Id
	@Column(length=80)
	private String nom;
	
	@Column(name="url", length=100)
	private String siteWeb;
	
	@Column(name="latit")
	private double latitude ;
	
	@Column(name="longit")	
	private double longitude;
	
	@OneToMany(mappedBy = "ecole", fetch=FetchType.EAGER)
	private Set<Specialite> specialites;
	
	public EcolePolytech(){}

	public EcolePolytech(String nom, String siteWeb, double latitude, double longitude) {
		super();
		this.nom = nom;
		this.siteWeb = siteWeb;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSiteWeb() {
		return siteWeb;
	}

	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Set<Specialite> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(Set<Specialite> specialites) {
		this.specialites = specialites;
	}

	
	
	

}
