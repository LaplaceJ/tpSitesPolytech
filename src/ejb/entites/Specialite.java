package ejb.entites;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Inheritance ( strategy =InheritanceType.TABLE_PER_CLASS) 
public abstract class Specialite implements Serializable {

	@Id
	private String nom;
	
	private String acronyme;
	
	@ManyToOne
	private EcolePolytech ecole;
	
	public Specialite(){}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAcronyme() {
		return acronyme;
	}

	public void setAcronyme(String acronyme) {
		this.acronyme = acronyme;
	}

	public EcolePolytech getEcole() {
		return ecole;
	}

	public void setEcole(EcolePolytech ecole) {
		this.ecole = ecole;
	}

	@Override
	public String toString() {
		return "Specialite [nom=" + nom + ", acronyme=" + acronyme + ", ecole=" + ecole + "]";
	}
	
	
	
	
	

}
