/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
import java.sql.Date;
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
 * Servlet pour la modification d'une livraison.
 */
@WebServlet(name = "LivraisonModification", urlPatterns = {"/livraisonModification"})
public class LivraisonModification extends HttpServlet {

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
            // Récupération de l'ID de la livraison à modifier
            Long idLivraison = Long.parseLong(request.getParameter("idLivraison"));

            // Récupération des paramètres du formulaire de modification
            String dateLivraisonStr = request.getParameter("dateLivraison");
            String libelle = request.getParameter("libelle");
            String montantLivraisonStr = request.getParameter("montantLivraison");

            // Validation de la date de livraison
            Date dateLivraison = null;
            if (dateLivraisonStr != null && !dateLivraisonStr.trim().isEmpty()) {
                try {
                    dateLivraison = Date.valueOf(dateLivraisonStr);
                } catch (IllegalArgumentException e) {
                    // Gérer l'erreur pour dateLivraison
                }
            }

            // Validation du montant de la livraison
            Double montantLivraison = null;
            if (montantLivraisonStr != null && !montantLivraisonStr.trim().isEmpty()) {
                try {
                    montantLivraison = Double.parseDouble(montantLivraisonStr);
                } catch (NumberFormatException e) {
                    // Gérer l'erreur pour montantLivraison
                }
            }

            // Création de l'objet livraison avec les nouvelles valeurs
            Mlivraison livraison = new Mlivraison(dateLivraison, libelle, montantLivraison);
            livraison.setIdLivraison(idLivraison);

            // Validation des données
            List<String> erreurs = ValiderLivraison.validerLivraison(livraison);

            if (!erreurs.isEmpty()) {
                // En cas d'erreurs de validation
                request.setAttribute("livraison", livraison);
                request.setAttribute("erreursLivraison", erreurs);
                response.sendRedirect(request.getContextPath() + "/listLivraison");
            } else {
                // Si les données sont valides, mise à jour de la livraison
                rlivraison.updateLivraison(livraison);
                request.getSession().setAttribute("messageLivraison", "Livraison modifiée avec succès");
                response.sendRedirect(request.getContextPath() + "/listLivraison");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/erreur.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Méthode laissée vide si la logique GET n'est pas nécessaire
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour la modification d'une livraison.";
    }
}