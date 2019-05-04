package ejb.sessions;


import java.util.Collection;

import javax.ejb.Remote;

import ejb.entites.EcolePolytech;
import ejb.entites.Domaine ;

@Remote 
public interface ServiceEcolesPolytech  {
  public void creerEcolePolytech(String nom, String siteWeb, 
    double latitude, double longitude) 
    throws EcoleDejaCreeeException ;  
  public EcolePolytech getEcole(String nom)
    throws EcoleInconnueException ;
  public Collection<Domaine> getDomaines() ;
  public void attacherDomaine(int domaineId, String nomEcole) 
		    throws DomaineInconnuException,
		    EcoleInconnueException,
		    EcoleDejaAttacheeException ;
  public Collection<EcolePolytech> getEcolesPolytech() ;
  Collection<EcolePolytech> getEcolesPolytechAuNordDe(String nom) 
		    throws EcoleInconnueException ;
  
  public enum TypeSpec {INITIALE, APPRENTISSAGE, FC} ;
  public void addSpecialite(String nomEcole, String nomSpecialite, 
                            String acronyme, TypeSpec type) 
    throws EcoleInconnueException, SpecialiteExisteDejaException ;
}