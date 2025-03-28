/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validateurs;

import java.util.ArrayList;
import java.util.List;
import models.Mutilisateur;
import requêtes.Rutilisateur;

/**
 *
 * @author USER
 */
public class ValiderUtilisateur {

    public static List<String> validerUtilisateur(Mutilisateur utilisateur) {
        List<String> erreurs = new ArrayList<>();
        Rutilisateur rutilisateur= new Rutilisateur();
        if (utilisateur == null) {
            erreurs.add("L'utilisateur ne peut pas être nul.");
            return erreurs; // Retourne immédiatement avec l'erreur
        }

        // Validation du nom
        if (utilisateur.getNom() == null || utilisateur.getNom().trim().isEmpty()) {
            erreurs.add("Le champ nom n'est pas conforme.");
        }

        // Validation du prénom
        if (utilisateur.getPrenom() == null || utilisateur.getPrenom().trim().isEmpty()) {
            erreurs.add("Le champ prénom n'est pas conforme.");
        }

        // Validation du sexe
        if (utilisateur.getSexe() == null
                || (!utilisateur.getSexe().equalsIgnoreCase("masculin")
                && !utilisateur.getSexe().equalsIgnoreCase("feminin"))) {
            erreurs.add("Le champ sexe doit être 'masculin' ou 'feminin'.");
        }

        // Validation de la date de naissance
        if (utilisateur.getDateNaissance() == null) {
            erreurs.add("Le champ date de naissance n'est pas conforme.");
        }

        // Validation du matricule
        if (utilisateur.getMatricule() == null || utilisateur.getMatricule().trim().isEmpty()) {
            erreurs.add("Le champ matricule n'est pas conforme.");
        }
        
        
        // Validation du mot de passe
        if (utilisateur.getPassword() == null || utilisateur.getPassword().length() < 8) {
            erreurs.add("Le mot de passe doit contenir au minimum 8 caractères.");
        }

        // Validation du rôle
        if (utilisateur.getRole() == null || utilisateur.getRole().trim().isEmpty()) {
            erreurs.add("Le champ rôle n'est pas conforme.");
        }

        // Validation du téléphone
        if (utilisateur.getTelephone() == null
                || utilisateur.getTelephone().toString().length() != 8
                || utilisateur.getTelephone() <= 0) {
            erreurs.add("Le téléphone doit être un nombre positif de 8 chiffres.");
        }
        
        if(rutilisateur.getUtilisateurByMatricule(utilisateur.getMatricule()) !=null){
            erreurs.add("ce matricule existe déja");
        }
        return erreurs; // Retourne la liste des erreurs
    }
    
    //si on vient via la modification on va plus vérifier le matricule
    
    public static List<String> validerUtilisateurModification(Mutilisateur utilisateur) {
        List<String> erreurs = new ArrayList<>();
        Rutilisateur rutilisateur= new Rutilisateur();
        if (utilisateur == null) {
            erreurs.add("L'utilisateur ne peut pas être nul.");
            return erreurs; // Retourne immédiatement avec l'erreur
        }

        // Validation du nom
        if (utilisateur.getNom() == null || utilisateur.getNom().trim().isEmpty()) {
            erreurs.add("Le champ nom n'est pas conforme.");
        }

        // Validation du prénom
        if (utilisateur.getPrenom() == null || utilisateur.getPrenom().trim().isEmpty()) {
            erreurs.add("Le champ prénom n'est pas conforme.");
        }

        // Validation du sexe
        if (utilisateur.getSexe() == null
                || (!utilisateur.getSexe().equalsIgnoreCase("masculin")
                && !utilisateur.getSexe().equalsIgnoreCase("feminin"))) {
            erreurs.add("Le champ sexe doit être 'masculin' ou 'feminin'.");
        }

        // Validation de la date de naissance
        if (utilisateur.getDateNaissance() == null) {
            erreurs.add("Le champ date de naissance n'est pas conforme.");
        }

        // Validation du matricule
        if (utilisateur.getMatricule() == null || utilisateur.getMatricule().trim().isEmpty()) {
            erreurs.add("Le champ matricule n'est pas conforme.");
        }
        
        
        // Validation du mot de passe
        if (utilisateur.getPassword() == null || utilisateur.getPassword().length() < 8) {
            erreurs.add("Le mot de passe doit contenir au minimum 8 caractères.");
        }

        // Validation du rôle
        if (utilisateur.getRole() == null || utilisateur.getRole().trim().isEmpty()) {
            erreurs.add("Le champ rôle n'est pas conforme.");
        }

        // Validation du téléphone
        if (utilisateur.getTelephone() == null
                || utilisateur.getTelephone().toString().length() != 8
                || utilisateur.getTelephone() <= 0) {
            erreurs.add("Le téléphone doit être un nombre positif de 8 chiffres.");
        }
    
        return erreurs; // Retourne la liste des erreurs
    }
}
