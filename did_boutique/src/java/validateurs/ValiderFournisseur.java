/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validateurs;

import java.util.ArrayList;
import java.util.List;
import models.Mfournisseur;
import requêtes.Rfournisseur;

/**
 *
 * @author USER
 */
public class ValiderFournisseur {

    public static List<String> validerFournisseur(Mfournisseur fournisseur) {
        List<String> erreurs = new ArrayList<>();
        Rfournisseur rfournisseur = new Rfournisseur();
        
        if (fournisseur == null) {
            erreurs.add("Le fournisseur ne peut pas être nul.");
            return erreurs; // Retourne immédiatement avec l'erreur
        }

        // Validation du nom
        if (fournisseur.getNom() == null || fournisseur.getNom().trim().isEmpty()) {
            erreurs.add("Le champ nom n'est pas conforme.");
        }

        // Validation du prénom
        if (fournisseur.getPrenom() == null || fournisseur.getPrenom().trim().isEmpty()) {
            erreurs.add("Le champ prénom n'est pas conforme.");
        }

        // Validation des numéros de téléphone
        if (fournisseur.getTelephone1() == null || fournisseur.getTelephone1() <= 0) {
            erreurs.add("Le téléphone 1 doit être un nombre positif.");
        }
        
        if (fournisseur.getTelephone2() != null && fournisseur.getTelephone2() <= 0) {
            erreurs.add("Le téléphone 2 doit être un nombre positif.");
        }

        // Validation de l'adresse
        if (fournisseur.getAdresse() == null || fournisseur.getAdresse().trim().isEmpty()) {
            erreurs.add("L'adresse n'est ps conforme.");
        }

        return erreurs;
    }
}