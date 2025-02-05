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
import models.Mlivraison;

/**
 *
 * @author USER
 */
public class Rlivraison {

    // Connexion à la base de données principale
    Connection con = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    // Méthode pour insérer une livraison dans la base de données
    public void insertLivraison(Mlivraison livraison) {
        String sql = "INSERT INTO livraison (dateLivraison, libelle, montantLivraison) VALUES (?, ?, ?)";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setDate(1, livraison.getDateLivraison());
            pst.setString(2, livraison.getLibelle());
            pst.setDouble(3, livraison.getMontantLivraison());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour les informations d'une livraison
    public void updateLivraison(Mlivraison livraison) {
        String sql = "UPDATE livraison SET dateLivraison = ?, libelle = ?, montantLivraison = ? WHERE idLivraison = ?";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setDate(1, livraison.getDateLivraison());
            pst.setString(2, livraison.getLibelle());
            pst.setDouble(3, livraison.getMontantLivraison());
            pst.setLong(4, livraison.getIdLivraison());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer une livraison en fonction de son identifiant
    public void deleteLivraison(Long idLivraison) {
        String sql = "DELETE FROM livraison WHERE idLivraison = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idLivraison);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour obtenir une livraison par son identifiant
    public Mlivraison getLivraison(Long idLivraison) {
        Mlivraison livraison = null;
        String sql = "SELECT * FROM livraison WHERE idLivraison = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idLivraison);
            rs = pst.executeQuery();
            if (rs.next()) {
                livraison = new Mlivraison();
                livraison.setIdLivraison(rs.getLong("idLivraison"));
                livraison.setDateLivraison(rs.getDate("dateLivraison"));
                livraison.setLibelle(rs.getString("libelle"));
                livraison.setMontantLivraison(rs.getDouble("montantLivraison"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livraison;
    }

    // Méthode pour obtenir la liste de toutes les livraisons
    public List<Mlivraison> listAllLivraisons() {
        List<Mlivraison> listLivraisons = new ArrayList<>();
        String sql = "SELECT * FROM livraison";
        try {
            con = BD.maConnexion();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Mlivraison livraison = new Mlivraison();
                livraison.setIdLivraison(rs.getLong("idLivraison"));
                livraison.setDateLivraison(rs.getDate("dateLivraison"));
                livraison.setLibelle(rs.getString("libelle"));
                livraison.setMontantLivraison(rs.getDouble("montantLivraison"));
                listLivraisons.add(livraison);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listLivraisons;
    }
}
