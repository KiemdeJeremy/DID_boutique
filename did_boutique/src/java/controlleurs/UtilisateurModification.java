/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Mutilisateur;
import requêtes.Rutilisateur;
import validateurs.ValiderUtilisateur;

/**
 *
 * @author USER
 */
@WebServlet(name = "UtilisateurModification", urlPatterns = {"/utilisateurModification"})
public class UtilisateurModification extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rutilisateur rutilisateur;

    @Override
    public void init() throws ServletException {
        rutilisateur = new Rutilisateur();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Récupération de l'ID de l'utilisateur à modifier
            Long idUtilisateur = Long.parseLong(request.getParameter("idUtilisateur"));

            // Récupération des paramètres du formulaire de modification
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String sexe = request.getParameter("sexe");
            String dateNaissancee = request.getParameter("dateNaissance");
            String matricule = request.getParameter("matricule");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            int telephone = Integer.parseInt(request.getParameter("telephone"));

            // Conversion de la chaîne de caractères en date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dateNaissancee);
            java.sql.Date dateNaissance = new java.sql.Date(parsedDate.getTime());

            // Création de l'objet utilisateur avec les nouvelles valeurs
            Mutilisateur utilisateur = new Mutilisateur(nom, prenom, sexe, dateNaissance, matricule, password, role, telephone);
            utilisateur.setIdUtilisateur(idUtilisateur);

            // Validation des données
            List<String> erreurs = ValiderUtilisateur.validerUtilisateurModification(utilisateur);

            if (!erreurs.isEmpty()) {
                // En cas d'erreurs de validation
                request.setAttribute("utilisateur", utilisateur);
                request.getSession().setAttribute("erreurs", erreurs);
                response.sendRedirect(request.getContextPath() + "/listUtilisateur");
            } else {
                // Si les données sont valides, mise à jour de l'utilisateur
                rutilisateur.updateUtilisateur(utilisateur);
                request.getSession().setAttribute("message", "Utilisateur modifié avec succès");
                response.sendRedirect(request.getContextPath() + "/listUtilisateur");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/erreur.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Servlet pour la modification d'un utilisateur";
    }

}
