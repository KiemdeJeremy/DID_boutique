package controlleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.MdetailLivraison;
import models.Mlivraison;
import models.Mproduit;
import requêtes.RdetailLivraison;
import requêtes.Rlivraison;
import requêtes.Rproduit;
import validateurs.ValiderDetailLivraison;

/**
 * Servlet pour l'enregistrement des détails de livraison.
 */
@WebServlet(name = "DetailLivraisonEnregistrement", urlPatterns = {"/detailLivraisonEnregistrement"})
public class DetailLivraisonEnregistrement extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RdetailLivraison rdetailLivraison;
    private Rproduit rproduit;
    private Rlivraison rlivraison;

    @Override
    public void init() throws ServletException {
        rdetailLivraison = new RdetailLivraison();
        rproduit = new Rproduit();
        rlivraison = new Rlivraison();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Initialisation de la liste des erreurs
            List<String> erreurs = new ArrayList<>();

            // Récupération des paramètres
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
                erreurs.add("La quantité est requise.");
            } else {
                try {
                    quantite = Double.parseDouble(quantiteStr);
                } catch (NumberFormatException e) {
                    erreurs.add("La quantité doit être un nombre valide.");
                }
            }

            // Validation du prix unitaire
            if (prixUnitaireStr == null || prixUnitaireStr.trim().isEmpty()) {
                erreurs.add("Le prix unitaire est requis.");
            } else {
                try {
                    prixUnitaire = Double.parseDouble(prixUnitaireStr);
                } catch (NumberFormatException e) {
                    erreurs.add("Le prix unitaire doit être un nombre valide.");
                }
            }

            // Validation du coût total
            if (coutTotalStr == null || coutTotalStr.trim().isEmpty()) {
                erreurs.add("Le coût total est requis.");
            } else {
                try {
                    coutTotal = Double.parseDouble(coutTotalStr);
                } catch (NumberFormatException e) {
                    erreurs.add("Le coût total doit être un nombre valide.");
                }
            }

            // Validation des identifiants
            if (idLivraisonStr == null || idLivraisonStr.trim().isEmpty()) {
                erreurs.add("L'identifiant de livraison est requis.");
            } else {
                try {
                    idLivraison = Long.parseLong(idLivraisonStr);
                } catch (NumberFormatException e) {
                    erreurs.add("L'identifiant de livraison doit être un nombre valide.");
                }
            }

            if (idProduitStr == null || idProduitStr.trim().isEmpty()) {
                erreurs.add("L'identifiant de produit est requis.");
            } else {
                try {
                    idProduit = Long.parseLong(idProduitStr);
                } catch (NumberFormatException e) {
                    erreurs.add("L'identifiant de produit doit être un nombre valide.");
                }
            }

            // Création de l'objet détail de livraison
            MdetailLivraison detailLivraison = new MdetailLivraison(quantite, prixUnitaire, coutTotal, idLivraison, idProduit);

            // Validation du détail de livraison
            List<String> erreursValidation = ValiderDetailLivraison.validerDetailLivraison(detailLivraison);
            erreurs.addAll(erreursValidation);

            // Ici je contrôle le montant de la livraison mère et la somme des montants détaillés
            List<MdetailLivraison> detailLivraisonExistant = rdetailLivraison.listDetailsLivraisonByIdLivraison(idLivraison);
            Mlivraison livraisonExistant = rlivraison.getLivraison(idLivraison);

            // Initialisation de la somme avec le nouveau détail de livraison
            double sommeDetailLivraison = detailLivraison.getCoutTotal(); // Commence avec le nouveau montant

            // Vérification si des détails de livraison existent déjà
            if (detailLivraisonExistant != null && !detailLivraisonExistant.isEmpty()) {
                // Ajoute les montants des détails de livraison existants
                for (MdetailLivraison detailLivraisonExist : detailLivraisonExistant) {
                    sommeDetailLivraison += detailLivraisonExist.getCoutTotal();
                }
            }

            // Comparaison de la somme totale avec le montant de la livraison
            if (sommeDetailLivraison > livraisonExistant.getMontantLivraison()) {
                erreurs.add("Erreur : Le total des détails (" + sommeDetailLivraison
                        + ") dépasse le montant de la livraison (" + livraisonExistant.getMontantLivraison() + ")");
            } else {
                // Vérification du stock
                Mproduit produit = rproduit.getProduit(idProduit);
                
                    produit.setQuantite(produit.getQuantite() + quantite);
                    rproduit.updateProduit(produit);
            }

            // Traitement selon la présence d'erreurs
            if (!erreurs.isEmpty()) {
                request.getSession().setAttribute("detailLivraisonTransmis", detailLivraison);
                request.getSession().setAttribute("erreurs", erreurs);
                response.sendRedirect(request.getContextPath() + "/listDetailLivraison"); // Redirige vers la liste des détails de livraison
                return; // Arrête l'exécution ici
            } else {
                // Aucune erreur, le détail de livraison est valide
                rdetailLivraison.insertDetailLivraison(detailLivraison);
                request.getSession().setAttribute("message", "Détail de livraison enregistré avec succès");
                response.sendRedirect(request.getContextPath() + "/listDetailLivraison"); // Redirige vers la liste des détails de livraison
                return; // Arrête l'exécution ici
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
        return "Servlet pour l'enregistrement des détails de livraison.";
    }
}