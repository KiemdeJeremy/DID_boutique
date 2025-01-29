/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requêtes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Mfournisseur;

/**
 *
 * @author USER
 */
public class Rfournisseur {

    // Connexion à la base de données principale
    Connection con = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    // Méthode pour insérer un fournisseur dans la base de données
    public void insertFournisseur(Mfournisseur fournisseur) {
        String sql = "INSERT INTO fournisseur (nom, prenom, telephone1, telephone2, adresse) VALUES (?, ?, ?, ?, ?)";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setString(1, fournisseur.getNom());
            pst.setString(2, fournisseur.getPrenom());
            pst.setInt(3, fournisseur.getTelephone1());
            pst.setInt(4, fournisseur.getTelephone2());
            pst.setString(5, fournisseur.getAdresse());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour les informations d'un fournisseur
    public void updateFournisseur(Mfournisseur fournisseur) {
        String sql = "UPDATE fournisseur SET nom = ?, prenom = ?, telephone1 = ?, telephone2 = ?, adresse = ? WHERE idFournisseur = ?";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setString(1, fournisseur.getNom());
            pst.setString(2, fournisseur.getPrenom());
            pst.setInt(3, fournisseur.getTelephone1());
            pst.setInt(4, fournisseur.getTelephone2());
            pst.setString(5, fournisseur.getAdresse());
            pst.setLong(6, fournisseur.getIdFournisseur());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un fournisseur en fonction de son identifiant
    public void deleteFournisseur(Long idFournisseur) {
        String sql = "DELETE FROM fournisseur WHERE idFournisseur = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idFournisseur);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour obtenir un fournisseur par son identifiant
    public Mfournisseur getFournisseur(Long idFournisseur) {
        Mfournisseur fournisseur = null;
        String sql = "SELECT * FROM fournisseur WHERE idFournisseur = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idFournisseur);
            rs = pst.executeQuery();
            if (rs.next()) {
                fournisseur = new Mfournisseur();
                fournisseur.setIdFournisseur(rs.getLong("idFournisseur"));
                fournisseur.setNom(rs.getString("nom"));
                fournisseur.setPrenom(rs.getString("prenom"));
                fournisseur.setTelephone1(rs.getInt("telephone1"));
                fournisseur.setTelephone2(rs.getInt("telephone2"));
                fournisseur.setAdresse(rs.getString("adresse"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fournisseur;
    }

    // Méthode pour obtenir la liste de tous les fournisseurs
    public List<Mfournisseur> listAllFournisseurs() {
        List<Mfournisseur> listFournisseurs = new ArrayList<>();
        String sql = "SELECT * FROM fournisseur";
        try {
            con = BD.maConnexion();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Mfournisseur fournisseur = new Mfournisseur();
                fournisseur.setIdFournisseur(rs.getLong("idFournisseur"));
                fournisseur.setNom(rs.getString("nom"));
                fournisseur.setPrenom(rs.getString("prenom"));
                fournisseur.setTelephone1(rs.getInt("telephone1"));
                fournisseur.setTelephone2(rs.getInt("telephone2"));
                fournisseur.setAdresse(rs.getString("adresse"));
                listFournisseurs.add(fournisseur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFournisseurs;
    }
}