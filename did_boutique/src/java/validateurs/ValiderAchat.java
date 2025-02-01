package validateurs;

import java.util.ArrayList;
import java.util.List;
import models.Machat;

/**
 * Classe pour valider les informations d'un achat.
 */
public class ValiderAchat {

    public static List<String> validerAchat(Machat achat) {
        List<String> erreurs = new ArrayList<>();
        
        if (achat == null) {
            erreurs.add("L'achat ne peut pas être nul.");
            return erreurs; // Retourne immédiatement avec l'erreur
        }

        // Validation de la date d'achat
        if (achat.getDateAchat() == null) {
            erreurs.add("La date d'achat ne peut pas être nulle.");
        }

        // Validation du montant
        if (achat.getMontant() == null || achat.getMontant() < 0) {
            erreurs.add("Le montant doit être un nombre positif.");
        }

        // Validation de la somme encaissée
        if (achat.getSommeEncaisse() == null || achat.getSommeEncaisse() < 0) {
            erreurs.add("La somme encaissée doit être un nombre positif.");
        }

        // Validation de la remise
        if (achat.getRemise() == null || achat.getRemise() < 0) {
            erreurs.add("La remise doit être un nombre positif.");
        }

        // Validation de l'identifiant de l'utilisateur
        if (achat.getIdUtilisateur() == null || achat.getIdUtilisateur() <= 0) {
            erreurs.add("L'identifiant de l'utilisateur doit être un nombre positif.");
        }

        // Validation de l'identifiant du client
        if (achat.getIdClient() == null || achat.getIdClient() <= 0) {
            erreurs.add("L'identifiant du client doit être un nombre positif.");
        }

        return erreurs;
    }
}