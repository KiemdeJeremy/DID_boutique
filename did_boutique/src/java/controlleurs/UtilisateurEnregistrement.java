/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet(name = "UtilisateurEnregistrement", urlPatterns = {"/utilisateurEnregistrement"})
public class UtilisateurEnregistrement extends HttpServlet {

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
            // Récupération des paramètres du formulaire d'inscription
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String sexe = request.getParameter("sexe");
            String dateNaissancee = request.getParameter("dateNaissance");
            String matricule = request.getParameter("matricule");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            int telephone = Integer.parseInt(request.getParameter("telephone"));

            // Conversion de la chaîne de caractères en date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajuste le format selon ton besoin
            java.util.Date parsedDate = dateFormat.parse(dateNaissancee);
            java.sql.Date dateNaissance = new java.sql.Date(parsedDate.getTime());
            Mutilisateur utilisateur = new Mutilisateur(nom, prenom, sexe, dateNaissance, matricule, password, role, telephone);
            List<String> erreurs = ValiderUtilisateur.validerUtilisateur(utilisateur);

            if (!erreurs.isEmpty()) {
                // Ajouter les erreurs et l'utilisateur non valide à la requête
                request.setAttribute("utilisateur", utilisateur);
                request.getSession().setAttribute("erreurs", erreurs);
                // Rediriger vers la servlet
                response.sendRedirect(request.getContextPath() + "/listUtilisateur");
            } else {
                // Aucune erreur, l'utilisateur est valide
                rutilisateur.insertUtilisateur(utilisateur);
                request.getSession().setAttribute("message", "Utilisateur enregistré avec succès");
                response.sendRedirect(request.getContextPath() + "/listUtilisateur");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Cela affichera l'erreur dans la console
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
