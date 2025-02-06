package validateurs;

import java.util.ArrayList;
import java.util.List;
import models.MdetailAchat;

/**
 * Classe pour valider les détails d'achat.
 */
public class ValiderDetailAchat {

    public static List<String> validerDetailAchat(MdetailAchat detailAchat) {
        List<String> erreurs = new ArrayList<>();

        if (detailAchat == null) {
            erreurs.add("Le détail d'achat ne peut pas être nul.");
            return erreurs; // Retourne immédiatement avec l'erreur
        }

        // Validation de la quantité
        if (detailAchat.getQuantite() == null || detailAchat.getQuantite() <= 0) {
            erreurs.add("La quantité doit être un nombre positif.");
        }

        // Validation du prix unitaire
        if (detailAchat.getPrixUnitaire() == null || detailAchat.getPrixUnitaire() <= 0) {
            erreurs.add("Le prix unitaire doit être un nombre positif.");
        }

        // Validation du coût total
        if (detailAchat.getCoutTotal() == null || detailAchat.getCoutTotal() <= 0) {
            erreurs.add("Le coût total doit être un nombre positif.");
        }

        // Validation de l'identifiant d'achat
        if (detailAchat.getIdAchat() == null || detailAchat.getIdAchat() <= 0) {
            erreurs.add("L'identifiant d'achat doit être un nombre positif.");
        }

        // Validation de l'identifiant de produit
        if (detailAchat.getIdProduit() == null || detailAchat.getIdProduit() <= 0) {
            erreurs.add("L'identifiant de produit doit être un nombre positif.");
        }

        return erreurs;
    }
}
