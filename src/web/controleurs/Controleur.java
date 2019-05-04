package web.controleurs;

import java.io.IOException;
import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.entites.EcolePolytech;
import ejb.entites.Specialite;
import ejb.entites.SpecialiteApprentissage;
import ejb.entites.SpecialiteFC;
import ejb.entites.SpecialiteInitiale;
import ejb.sessions.EcoleInconnueException;
import ejb.sessions.ServiceEcolesPolytech;
import ejb.sessions.ServiceEcolesPolytech.TypeSpec;
import ejb.sessions.ServiceEcolesPolytechLocal;
import ejb.sessions.SpecialiteExisteDejaException;

@WebServlet(value = { "ajoutSpecialite", "formAjoutSpecialite" })
public class Controleur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@javax.ejb.EJB
	private ServiceEcolesPolytechLocal sve;

	public Controleur() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI().toString();
		String maVue = "/formAjoutSpecialite.html";
		if (url.endsWith("/formAjoutSpecialite")) {
			// page par défaut

			maVue = "/formAjoutSpecialite.html";
		} else if (url.endsWith("/ajoutSpecialite")) {
			maVue = "/ajoutSpecialite.jsp";

			// récup des données

			String nomEcole = request.getParameter("nomEcole");
			String nomSpe = request.getParameter("nomSpecialite");
			String acroSpe = request.getParameter("acronymeSpecialite");

			// 1 = FC // 2 = APPRENTISSAGE // 3 = INITIALE

			try {
				switch (request.getParameter("typeSpecialite")) {
				case "initiale":

					sve.addSpecialite(nomEcole, nomSpe, acroSpe, TypeSpec.INITIALE);

					break;
				case "apprentissage":
					sve.addSpecialite(nomEcole, nomSpe, acroSpe, TypeSpec.APPRENTISSAGE);
					break;

				case "fc":
					sve.addSpecialite(nomEcole, nomSpe, acroSpe, TypeSpec.FC);

					break;

				default:
					sve.addSpecialite(nomEcole, nomSpe, acroSpe, TypeSpec.FC);
					break;
				}

				request.setAttribute("nomSpe", nomSpe);
				request.setAttribute("ecole", nomEcole);
				request.setAttribute("listeSpe", sve.getEcole(nomEcole).getSpecialites());
				request.setAttribute("erreurType", "OK");
			} catch (EcoleInconnueException e) {
				request.setAttribute("ecole", nomEcole);
				request.setAttribute("erreurType", "EcoleInconnueException");
			} catch (SpecialiteExisteDejaException e) {
				request.setAttribute("erreurType", "SpecialiteExisteDejaException");
				try {
					request.setAttribute("ecole", sve.getEcole(nomEcole));
					request.setAttribute("listeSpe", sve.getEcole(nomEcole).getSpecialites());
					request.setAttribute("nomSpe", nomSpe);
				} catch (EcoleInconnueException ignored) {
				}
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(maVue);
			dispatcher.forward(request, response);

		}
	}
}
