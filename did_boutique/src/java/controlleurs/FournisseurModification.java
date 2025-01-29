/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
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
@WebServlet(name = "FournisseurModification", urlPatterns = {"/fournisseurModification"})
public class FournisseurModification extends HttpServlet {

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
            // Récupération de l'ID du fournisseur à modifier
            Long idFournisseur = Long.parseLong(request.getParameter("idFournisseur"));
            
            // Récupération des paramètres du formulaire de modification
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String telephone1Str = request.getParameter("telephone1");
            String telephone2Str = request.getParameter("telephone2");
            String adresse = request.getParameter("adresse");

            // Validation des numéros de téléphone
            Integer telephone1 = null;
            Integer telephone2 = null;

            if (telephone1Str != null && !telephone1Str.trim().isEmpty()) {
                try {
                    telephone1 = Integer.parseInt(telephone1Str);
                } catch (NumberFormatException e) {
                    // Gérer l'erreur pour telephone1
                }
            }

            if (telephone2Str != null && !telephone2Str.trim().isEmpty()) {
                try {
                    telephone2 = Integer.parseInt(telephone2Str);
                } catch (NumberFormatException e) {
                    // Gérer l'erreur pour telephone2
                }
            }

            // Création de l'objet fournisseur avec les nouvelles valeurs
            Mfournisseur fournisseur = new Mfournisseur(nom, prenom, telephone1, telephone2, adresse);
            fournisseur.setIdFournisseur(idFournisseur);

            // Validation des données
            List<String> erreurs = ValiderFournisseur.validerFournisseur(fournisseur);

            if (!erreurs.isEmpty()) {
                // En cas d'erreurs de validation
                request.setAttribute("fournisseur", fournisseur);
                request.setAttribute("erreurs", erreurs);
                response.sendRedirect(request.getContextPath() + "/listFournisseur");
            } else {
                // Si les données sont valides, mise à jour du fournisseur
                rfournisseur.updateFournisseur(fournisseur);
                request.getSession().setAttribute("message", "Fournisseur modifié avec succès");
                response.sendRedirect(request.getContextPath() + "/listFournisseur");
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
        return "Servlet pour la modification d'un fournisseur";
    }
}