package client ;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException ;

import ejb.entites.Domaine;
import ejb.entites.EcolePolytech;
import ejb.entites.Specialite;
import ejb.sessions.DomaineInconnuException;
import ejb.sessions.EcoleDejaAttacheeException;
import ejb.sessions.EcoleDejaCreeeException;
import ejb.sessions.EcoleInconnueException;
import ejb.sessions.ServiceEcolesPolytech;
import ejb.sessions.SpecialiteExisteDejaException;



public class Main {
	
	
 public static void main(String[] args) throws EcoleInconnueException { 
   try {

      InitialContext ctx = new InitialContext();
      Object obj = ctx.lookup("ejb:sitesPolytech/sitesPolytechSessions//"+"ServiceEcolesPolytechBean!ejb.sessions.ServiceEcolesPolytechRemote");
      ServiceEcolesPolytech  sve = (ServiceEcolesPolytech) obj ;

      sve.creerEcolePolytech("Lille", "www.lille.com", 47.52222, 0.58941);
      
      Collection<Domaine> spe = sve.getDomaines() ; 
   
      for( Domaine d  : spe){
    	  if (d.getId()!=3) sve.attacherDomaine(d.getId(), "Lille");
    	  System.out.println(d.getNom());
      }
      Collection<EcolePolytech> eps = sve.getEcolesPolytechAuNordDe("Paris-UPMC"); 
      for(EcolePolytech ep  : eps){
    	  System.out.println( "Nom école :  "  + ep.getNom() + " \n spécialité" + ep.getSpecialites().toString()); 
    	
      }
      
      sve.addSpecialite("Lille" , "GIS4" , "Génie informatique et statistique", ServiceEcolesPolytech.TypeSpec.FC ) ; 
      sve.addSpecialite("Lille" , "GIS2A4" , "Génie informatique et statistique alternant", ServiceEcolesPolytech.TypeSpec.APPRENTISSAGE ) ; 
      System.out.println("Toutes les écoles avec leurs spécialités délivrées");
      Collection<EcolePolytech> allec = new ArrayList<EcolePolytech>();
      allec = sve.getEcolesPolytech() ; 
      for(EcolePolytech ec : allec){
    	  System.out.println("Nom école :  "  + ec.getNom() + " \n spécialité : "  );
    	  for ( Specialite spes :  ec.getSpecialites() ){
    		  System.out.println(spes + " ");
    	  }
    	  

      }
      
    } catch(NamingException /* DomaineInconnuException | EcoleDejaAttacheeException | EcoleDejaCreeeException | */ e) {
        System.err.println("Erreur:"+e.getMessage() + e.getClass() ) ;
	} catch (SpecialiteExisteDejaException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DomaineInconnuException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (EcoleDejaAttacheeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (EcoleDejaCreeeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
  }	
}

