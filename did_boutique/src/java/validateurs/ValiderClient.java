/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validateurs;

import java.util.ArrayList;
import java.util.List;
import models.Mclient;

/**
 *
 * @author USER
 */
public class ValiderClient {

    public static List<String> validerClient(Mclient client) {
        List<String> erreurs = new ArrayList<>();

        if (client == null) {
            erreurs.add("Le client ne peut pas être nul.");
            return erreurs; // Retourne immédiatement avec l'erreur
        }

        // Validation du nom
        if (client.getNom() == null || client.getNom().trim().isEmpty()) {
            erreurs.add("Le champ nom n'est pas conforme.");
        }

        // Validation du numéro de téléphone
        if (client.getTelephone() == null || client.getTelephone() <= 0) {
            erreurs.add("Le numéro de téléphone doit être un nombre positif.");
        }

        return erreurs;
    }
}