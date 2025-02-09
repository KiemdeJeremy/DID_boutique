package controlleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Machat;
import models.MdetailAchat;
import models.Mproduit;
import requêtes.Rachat;
import requêtes.RdetailAchat;
import requêtes.Rproduit;
import validateurs.ValiderDetailAchat;

/**
 * Servlet pour l'enregistrement des détails d'achat.
 */
@WebServlet(name = "DetailAchatEnregistrement", urlPatterns = {"/detailAchatEnregistrement"})
public class DetailAchatEnregistrement extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RdetailAchat rdetailAchat;
    private Rproduit rproduit;
    private Rachat rachat;

    @Override
    public void init() throws ServletException {
        rdetailAchat = new RdetailAchat();
        rproduit = new Rproduit();
        rachat = new Rachat();
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
            String idAchatStr = request.getParameter("idAchat");
            String idProduitStr = request.getParameter("idProduit");

            // Conversion et validation des paramètres
            Double quantite = null;
            Double prixUnitaire = null;
            Double coutTotal = null;
            Long idAchat = null;
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
            if (idAchatStr == null || idAchatStr.trim().isEmpty()) {
                erreurs.add("L'identifiant d'achat est requis.");
            } else {
                try {
                    idAchat = Long.parseLong(idAchatStr);
                } catch (NumberFormatException e) {
                    erreurs.add("L'identifiant d'achat doit être un nombre valide.");
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

            // Création de l'objet détail d'achat
            MdetailAchat detailAchat = new MdetailAchat(quantite, prixUnitaire, coutTotal, idAchat, idProduit);
// Validation du détail d'achat
            List<String> erreursValidation = ValiderDetailAchat.validerDetailAchat(detailAchat);
            erreurs.addAll(erreursValidation);

// Ici je contrôle le montant de l'achat mère et la somme des montants détaillés
            List<MdetailAchat> detailAchatExistant = rdetailAchat.listDetailsAchatByIdAchat(idAchat);
            Machat achatExistant = rachat.getAchat(idAchat);

// Initialisation de la somme avec le nouveau détail d'achat
            double sommeDetailAchat = detailAchat.getCoutTotal(); // Commence avec le nouveau montant

// Vérification si des détails d'achat existent déjà
            if (detailAchatExistant != null && !detailAchatExistant.isEmpty()) {
                // Ajoute les montants des détails d'achat existants
                for (MdetailAchat detailAchatExist : detailAchatExistant) {
                    sommeDetailAchat += detailAchatExist.getCoutTotal();
                }
            }

// Comparaison de la somme totale avec le montant de l'achat
            if (sommeDetailAchat > achatExistant.getMontant()) {
                erreurs.add("Erreur : Le total des détails (" + sommeDetailAchat
                        + ") dépasse le montant de l'achat (" + achatExistant.getMontant() + ")");
            } else {

                // Vérification du stock
                Mproduit produit = rproduit.getProduit(idProduit);
                if (produit.getQuantite() < quantite) {
                    erreurs.add("le Stock restant est insuffisant !!!");
                } else {
                    produit.setQuantite(produit.getQuantite() - quantite);
                    rproduit.updateProduit(produit);
                }
            }

            // Traitement selon la présence d'erreurs
            if (!erreurs.isEmpty()) {
                request.getSession().setAttribute("detailAchatTransmis", detailAchat);
                request.getSession().setAttribute("erreurs", erreurs);
                response.sendRedirect(request.getContextPath() + "/listDetailAchat"); // Redirige vers la liste des détails d'achat
                return; // Arrête l'exécution ici
            } else {
                // Aucune erreur, le détail d'achat est valide
                rdetailAchat.insertDetailAchat(detailAchat);
                request.getSession().setAttribute("message", "Détail d'achat enregistré avec succès");
                if (sommeDetailAchat == achatExistant.getMontant()) {
                    //ici je reprend la liste des detailsAchat pour prendre en compte celui qu'on d'ajouté tout récemment
                    List<MdetailAchat> detailAchatExistantComplet = rdetailAchat.listDetailsAchatByIdAchat(idAchat);
                    rdetailAchat.generateTicket(achatExistant, detailAchatExistantComplet);
                }
                response.sendRedirect(request.getContextPath() + "/listDetailAchat"); // Redirige vers la liste des détails d'achat
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
        return "Servlet pour l'enregistrement des détails d'achat.";
    }
}
