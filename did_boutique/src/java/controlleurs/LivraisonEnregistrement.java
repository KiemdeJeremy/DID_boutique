/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Mlivraison;
import requêtes.Rlivraison;
import validateurs.ValiderLivraison;

/**
 * Servlet pour l'enregistrement des livraisons.
 */
@WebServlet(name = "LivraisonEnregistrement", urlPatterns = {"/livraisonEnregistrement"})
public class LivraisonEnregistrement extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rlivraison rlivraison;

    @Override
    public void init() throws ServletException {
        rlivraison = new Rlivraison(); // Initialisation de l'objet Rlivraison
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Initialisation de la liste des erreurs
            List<String> erreurs = new ArrayList<>();

            // Récupération des paramètres
            String dateLivraisonStr = request.getParameter("dateLivraison");
            String libelle = request.getParameter("libelle");
            String montantLivraisonStr = request.getParameter("montantLivraison");

            // Validation de la date de livraison
            Date dateLivraison = null;
            if (dateLivraisonStr == null || dateLivraisonStr.trim().isEmpty()) {
                erreurs.add("La date de livraison est requise.");
            } else {
                try {
                    dateLivraison = Date.valueOf(dateLivraisonStr);
                } catch (IllegalArgumentException e) {
                    erreurs.add("La date de livraison doit être au format valide (YYYY-MM-DD).");
                }
            }

            // Validation du libellé
            if (libelle == null || libelle.trim().isEmpty()) {
                erreurs.add("Le libellé est requis.");
            }

            // Validation du montant de la livraison
            Double montantLivraison = null;
            if (montantLivraisonStr == null || montantLivraisonStr.trim().isEmpty()) {
                erreurs.add("Le montant de la livraison est requis.");
            } else {
                try {
                    montantLivraison = Double.parseDouble(montantLivraisonStr);
                } catch (NumberFormatException e) {
                    erreurs.add("Le montant de la livraison doit être un nombre valide.");
                }
            }

            // Création de l'objet livraison
            Mlivraison livraison = new Mlivraison(dateLivraison, libelle, montantLivraison);

            // Validation de la livraison
            List<String> erreursValidation = ValiderLivraison.validerLivraison(livraison);
            erreurs.addAll(erreursValidation);

            // Traitement selon la présence d'erreurs
            if (!erreurs.isEmpty()) {
                request.getSession().setAttribute("livraison", livraison);
                request.getSession().setAttribute("erreursLivraison", erreurs);
                response.sendRedirect(request.getContextPath() + "/listLivraison");
                return; // Arrêter l'exécution ici
            } else {
                // Aucune erreur, la livraison est valide
                rlivraison.insertLivraison(livraison);
                request.getSession().setAttribute("messageLivraison", "Livraison enregistrée avec succès");
                response.sendRedirect(request.getContextPath() + "/listLivraison");
                return; // Arrêter l'exécution ici
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
        return "Servlet pour l'enregistrement des livraisons.";
    }
}