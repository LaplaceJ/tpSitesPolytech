package ejb.sessions;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import ejb.entites.Domaine;
import ejb.entites.EcolePolytech;
import ejb.entites.Specialite;
import ejb.entites.SpecialiteApprentissage;
import ejb.entites.SpecialiteFC;
import ejb.entites.SpecialiteInitiale;

@Stateless
public class ServiceEcolesPolytechBean implements ServiceEcolesPolytechRemote, ServiceEcolesPolytechLocal {

	@PersistenceContext(unitName = "sitesPolytech")
	protected EntityManager em;

	public ServiceEcolesPolytechBean() {

	}

	public void creerEcolePolytech(String nom, String siteWeb, double latitude, double longitude)
			throws EcoleDejaCreeeException {

		if (em.find(EcolePolytech.class, nom) == null) {
			EcolePolytech ep = new EcolePolytech(nom, siteWeb, latitude, longitude);
			em.persist(ep);
		} else {
			throw new EcoleDejaCreeeException();
		}

	}

	public EcolePolytech getEcole(String nom) throws EcoleInconnueException {

		EcolePolytech ep = (EcolePolytech) em.find(EcolePolytech.class, nom);
		if (ep == null)
			throw new EcoleInconnueException();
		return ep;
	}

	@SuppressWarnings("unchecked")
	public Collection<Domaine> getDomaines() {
		return (Collection<Domaine>) em.createQuery("Select  d from Domaine d").getResultList();

	}

	public void attacherDomaine(int domaineId, String nomEcole)
			throws DomaineInconnuException, EcoleInconnueException, EcoleDejaAttacheeException {
		EcolePolytech ep;

		ep = this.getEcole(nomEcole);

		Domaine d;
		try {
			d = (Domaine) em.createQuery("Select d from Domaine d where d.id = :id  ").setParameter("id", domaineId)
					.getSingleResult();

		} catch (NoResultException ignored) {
			throw new DomaineInconnuException();
		}

		if (d.getEcoles().contains(ep))
			throw new EcoleDejaAttacheeException();
		else {
			d.getEcoles().add(ep);
			em.persist(d);
		}

	}

	@SuppressWarnings("unchecked")

	public Collection<EcolePolytech> getEcolesPolytech() {
		return (Collection<EcolePolytech>) em.createQuery("Select e from ecole e").getResultList();
	}

	public Collection<EcolePolytech> getEcolesPolytechAuNordDe(String nom) throws EcoleInconnueException {

		Collection<EcolePolytech> ecoles = getEcolesPolytech();
		EcolePolytech epArg = getEcole(nom);
		Collection<EcolePolytech> res = new ArrayList<EcolePolytech>();
		for (EcolePolytech ep : ecoles) {
			if (ep.getLatitude() > epArg.getLatitude()) {
				res.add(ep);
			}
		}
		return res;
	}

	public void addSpecialite(String nomEcole, String nomSpecialite, String acronyme, TypeSpec type)
			throws EcoleInconnueException, SpecialiteExisteDejaException {

		if (em.find(Specialite.class, nomSpecialite) != null){
			System.out.println("ERREUR SpecialiteExisteDejaException");
			throw new SpecialiteExisteDejaException();
		}
			
		if (em.find(EcolePolytech.class, nomEcole) == null){
			System.out.println("ERREUR EcoleInconnueException");
			throw new EcoleInconnueException();
		}
			

		Specialite spe = null;

		switch (type) {
		case INITIALE:
			spe = new SpecialiteInitiale();
			break;
		case APPRENTISSAGE:
			spe = new SpecialiteApprentissage();
			break;

		case FC:
			spe = new SpecialiteFC();

			break;

		default:
			spe = new SpecialiteFC();
			break;
		}

		spe.setAcronyme(acronyme);
		spe.setEcole(getEcole(nomEcole));
		spe.setNom(nomSpecialite);
		em.persist(spe);

	}

}
