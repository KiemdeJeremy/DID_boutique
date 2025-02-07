package validateurs;

import java.util.ArrayList;
import java.util.List;
import models.MdetailLivraison;

/**
 * Classe pour valider les détails de livraison.
 */
public class ValiderDetailLivraison {

    public static List<String> validerDetailLivraison(MdetailLivraison detailLivraison) {
        List<String> erreurs = new ArrayList<>();

        if (detailLivraison == null) {
            erreurs.add("Le détail de livraison ne peut pas être nul.");
            return erreurs; // Retourne immédiatement avec l'erreur
        }

        // Validation de la quantité
        if (detailLivraison.getQuantite() == null || detailLivraison.getQuantite() <= 0) {
            erreurs.add("La quantité doit être un nombre positif.");
        }

        // Validation du prix unitaire
        if (detailLivraison.getPrixUnitaire() == null || detailLivraison.getPrixUnitaire() <= 0) {
            erreurs.add("Le prix unitaire doit être un nombre positif.");
        }

        // Validation du coût total
        if (detailLivraison.getCoutTotal() == null || detailLivraison.getCoutTotal() <= 0) {
            erreurs.add("Le coût total doit être un nombre positif.");
        }

        // Validation de l'identifiant de livraison
        if (detailLivraison.getIdLivraison() == null || detailLivraison.getIdLivraison() <= 0) {
            erreurs.add("L'identifiant de livraison doit être un nombre positif.");
        }

        // Validation de l'identifiant de produit
        if (detailLivraison.getIdProduit() == null || detailLivraison.getIdProduit() <= 0) {
            erreurs.add("L'identifiant de produit doit être un nombre positif.");
        }

        return erreurs;
    }
}