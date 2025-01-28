/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            // Initialisation de la liste des erreurs
            List<String> erreurs = new ArrayList<>();

            try {
                // Récupération et validation des paramètres individuels
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String sexe = request.getParameter("sexe");
                String dateNaissancee = request.getParameter("dateNaissance");
                String matricule = request.getParameter("matricule");
                String password = request.getParameter("password");
                String role = request.getParameter("role");

                // Validation du téléphone avant conversion
                String telephoneStr = request.getParameter("telephone");
                int telephone;
                if (telephoneStr == null || telephoneStr.trim().isEmpty()) {
                    erreurs.add("Le numéro de téléphone est requis");
                    telephone = 0;
                } else {
                    try {
                        telephone = Integer.parseInt(telephoneStr);
                    } catch (NumberFormatException e) {
                        erreurs.add("Le numéro de téléphone doit être un nombre valide");
                        telephone = 0;
                    }
                }

                // Validation de la date avant conversion
                java.sql.Date dateNaissance = null;
                if (dateNaissancee != null && !dateNaissancee.trim().isEmpty()) {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date parsedDate = dateFormat.parse(dateNaissancee);
                        dateNaissance = new java.sql.Date(parsedDate.getTime());
                    } catch (ParseException e) {
                        erreurs.add("Le format de la date de naissance est invalide");
                    }
                } else {
                    erreurs.add("La date de naissance est requise");
                }

                // Création de l'objet utilisateur
                Mutilisateur utilisateur = new Mutilisateur(nom, prenom, sexe, dateNaissance, matricule, password, role, telephone);

                // Validation de l'utilisateur
                List<String> erreursValidation = ValiderUtilisateur.validerUtilisateur(utilisateur);
                erreurs.addAll(erreursValidation);

                // Traitement selon la présence d'erreurs
                if (!erreurs.isEmpty()) {
                    request.getSession().setAttribute("utilisateur", utilisateur);
                    request.getSession().setAttribute("erreurs", erreurs);
                    response.sendRedirect(request.getContextPath() + "/listUtilisateur");
                    return; // Important : arrêter l'exécution ici
                } else {
                    // Aucune erreur, l'utilisateur est valide
                    rutilisateur.insertUtilisateur(utilisateur);
                    request.getSession().setAttribute("message", "Utilisateur enregistré avec succès");
                    response.sendRedirect(request.getContextPath() + "/listUtilisateur");
                    return; // Important : arrêter l'exécution ici
                }
            } catch (Exception e) {
                // Capture des exceptions spécifiques au traitement
                erreurs.add("Une erreur s'est produite lors du traitement : " + e.getMessage());
                request.getSession().setAttribute("erreurs", erreurs);
                response.sendRedirect(request.getContextPath() + "/listUtilisateur");
                return;
            }

        } catch (Exception e) {
            // Capture des erreurs critiques (comme les erreurs de redirection)
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur système s'est produite");
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
