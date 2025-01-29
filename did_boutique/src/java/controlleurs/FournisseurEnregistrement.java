/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Mfournisseur;
import requêtes.Rfournisseur;
import validateurs.ValiderFournisseur;

/**
 *
 * @author USER
 */
@WebServlet(name = "FournisseurEnregistrement", urlPatterns = {"/fournisseurEnregistrement"})
public class FournisseurEnregistrement extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rfournisseur rfournisseur;

    @Override
    public void init() throws ServletException {
        rfournisseur = new Rfournisseur();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Initialisation de la liste des erreurs
            List<String> erreurs = new ArrayList<>();

            // Récupération des paramètres
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String telephone1Str = request.getParameter("telephone1");
            String telephone2Str = request.getParameter("telephone2");
            String adresse = request.getParameter("adresse"); // Adresse en tant que String

            // Validation des numéros de téléphone
            Integer telephone1 = null;
            Integer telephone2 = null;
            if (telephone1Str == null || telephone1Str.trim().isEmpty()) {
                erreurs.add("Le numéro de téléphone 1 est requis.");
            } else {
                try {
                    telephone1 = Integer.parseInt(telephone1Str);
                } catch (NumberFormatException e) {
                    erreurs.add("Le numéro de téléphone 1 doit être un nombre valide.");
                }
            }

            if (telephone2Str != null && !telephone2Str.trim().isEmpty()) {
                try {
                    telephone2 = Integer.parseInt(telephone2Str);
                } catch (NumberFormatException e) {
                    erreurs.add("Le numéro de téléphone 2 doit être un nombre valide.");
                }
            }

            // Validation de l'adresse
            if (adresse == null || adresse.trim().isEmpty()) {
                erreurs.add("L'adresse est requise.");
            }

            // Création de l'objet fournisseur
            Mfournisseur fournisseur = new Mfournisseur(nom, prenom, telephone1, telephone2, adresse);

            // Validation du fournisseur
            List<String> erreursValidation = ValiderFournisseur.validerFournisseur(fournisseur);
            erreurs.addAll(erreursValidation);

            // Traitement selon la présence d'erreurs
            if (!erreurs.isEmpty()) {
                request.getSession().setAttribute("fournisseur", fournisseur);
                request.getSession().setAttribute("erreurs", erreurs);
                response.sendRedirect(request.getContextPath() + "/listFournisseur");
                return; // Important : arrêter l'exécution ici
            } else {
                // Aucune erreur, le fournisseur est valide
                rfournisseur.insertFournisseur(fournisseur);
                request.getSession().setAttribute("message", "Fournisseur enregistré avec succès");
                response.sendRedirect(request.getContextPath() + "/listFournisseur");
                return; // Important : arrêter l'exécution ici
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur système s'est produite");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Méthode laissée vide si la logique GET n'est pas nécessaire
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour l'enregistrement des fournisseurs.";
    }
}