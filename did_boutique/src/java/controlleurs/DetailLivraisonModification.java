package controlleurs;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.MdetailLivraison;
import requêtes.RdetailLivraison;
import validateurs.ValiderDetailLivraison;

/**
 * Servlet pour la modification des détails de livraison.
 */
@WebServlet(name = "DetailLivraisonModification", urlPatterns = {"/detailLivraisonModification"})
public class DetailLivraisonModification extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RdetailLivraison rdetailLivraison;

    @Override
    public void init() throws ServletException {
        rdetailLivraison = new RdetailLivraison();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Récupération de l'ID du détail de livraison à modifier
            Long idDetailLivraison = Long.parseLong(request.getParameter("idDetailLivraison"));
            
            // Récupération des paramètres du formulaire de modification
            String quantiteStr = request.getParameter("quantite");
            String prixUnitaireStr = request.getParameter("prixUnitaire");
            String coutTotalStr = request.getParameter("coutTotal");
            String idLivraisonStr = request.getParameter("idLivraison");
            String idProduitStr = request.getParameter("idProduit");

            // Conversion et validation des paramètres
            Double quantite = null;
            Double prixUnitaire = null;
            Double coutTotal = null;
            Long idLivraison = null;
            Long idProduit = null;

            // Validation de la quantité
            if (quantiteStr == null || quantiteStr.trim().isEmpty()) {
                request.setAttribute("erreurQuantite", "La quantité est requise.");
            } else {
                try {
                    quantite = Double.parseDouble(quantiteStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("erreurQuantite", "La quantité doit être un nombre valide.");
                }
            }

            // Validation du prix unitaire
            if (prixUnitaireStr == null || prixUnitaireStr.trim().isEmpty()) {
                request.setAttribute("erreurPrixUnitaire", "Le prix unitaire est requis.");
            } else {
                try {
                    prixUnitaire = Double.parseDouble(prixUnitaireStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("erreurPrixUnitaire", "Le prix unitaire doit être un nombre valide.");
                }
            }

            // Validation du coût total
            if (coutTotalStr == null || coutTotalStr.trim().isEmpty()) {
                request.setAttribute("erreurCoutTotal", "Le coût total est requis.");
            } else {
                try {
                    coutTotal = Double.parseDouble(coutTotalStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("erreurCoutTotal", "Le coût total doit être un nombre valide.");
                }
            }

            // Validation des identifiants
            if (idLivraisonStr == null || idLivraisonStr.trim().isEmpty()) {
                request.setAttribute("erreurIdLivraison", "L'identifiant de livraison est requis.");
            } else {
                try {
                    idLivraison = Long.parseLong(idLivraisonStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("erreurIdLivraison", "L'identifiant de livraison doit être un nombre valide.");
                }
            }

            if (idProduitStr == null || idProduitStr.trim().isEmpty()) {
                request.setAttribute("erreurIdProduit", "L'identifiant de produit est requis.");
            } else {
                try {
                    idProduit = Long.parseLong(idProduitStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("erreurIdProduit", "L'identifiant de produit doit être un nombre valide.");
                }
            }

            // Création de l'objet détail de livraison avec les nouvelles valeurs
            MdetailLivraison detailLivraison = new MdetailLivraison(quantite, prixUnitaire, coutTotal, idLivraison, idProduit);
            detailLivraison.setIdDetailLivraison(idDetailLivraison);

            // Validation des données
            List<String> erreurs = ValiderDetailLivraison.validerDetailLivraison(detailLivraison);
            if (!erreurs.isEmpty()) {
                request.setAttribute("detailLivraison", detailLivraison);
                request.setAttribute("erreurs", erreurs);
                response.sendRedirect(request.getContextPath() + "/listDetailLivraison");
            } else {
                // Si les données sont valides, mise à jour du détail de livraison
                rdetailLivraison.updateDetailLivraison(detailLivraison);
                request.getSession().setAttribute("message", "Détail de livraison modifié avec succès");
                response.sendRedirect(request.getContextPath() + "/listDetailLivraison");
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
        return "Servlet pour la modification d'un détail de livraison";
    }
}