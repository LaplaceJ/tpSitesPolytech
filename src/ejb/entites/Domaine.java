package ejb.entites;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Domaine  implements Serializable {

	@Id 
	private int id ; 
	 
	private String nom; 
	
	@ManyToMany
	private Set<EcolePolytech> ecoles ; 
	
	
	public Domaine(){}


	public Set<EcolePolytech> getEcoles() {
		return ecoles;
	}


	public void setEcoles(Set<EcolePolytech> ecoles) {
		this.ecoles = ecoles;
	}


	@Override
	public String toString() {
		return "Domaine [id=" + id + ", nom=" + nom + ", ecoles=" + ecoles + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
}
