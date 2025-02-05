/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validateurs;

import java.util.ArrayList;
import java.util.List;
import models.Mlivraison;

/**
 * Classe pour valider les objets de livraison.
 */
public class ValiderLivraison {

    public static List<String> validerLivraison(Mlivraison livraison) {
        List<String> erreurs = new ArrayList<>();

        if (livraison == null) {
            erreurs.add("La livraison ne peut pas être nulle.");
            return erreurs; // Retourne immédiatement avec l'erreur
        }

        // Validation de la date de livraison
        if (livraison.getDateLivraison() == null) {
            erreurs.add("La date de livraison est requise.");
        }

        // Validation du libellé
        if (livraison.getLibelle() == null || livraison.getLibelle().trim().isEmpty()) {
            erreurs.add("Le champ libellé n'est pas conforme.");
        }

        // Validation du montant de la livraison
        if (livraison.getMontantLivraison() == null || livraison.getMontantLivraison() <= 0) {
            erreurs.add("Le montant de la livraison doit être un nombre positif.");
        }

        return erreurs;
    }
}