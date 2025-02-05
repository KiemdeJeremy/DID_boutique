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
import models.Mproduit;

/**
 * Classe pour gérer les opérations sur les produits.
 */
public class Rproduit {

    // Connexion à la base de données principale
    Connection con = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    // Méthode pour insérer un produit dans la base de données
    public void insertProduit(Mproduit produit) {
        String sql = "INSERT INTO produit (nom, prix, quantite, datePeremption, image) VALUES (?, ?, ?, ?, ?)";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setString(1, produit.getNom());
            pst.setDouble(2, produit.getPrix());
            pst.setDouble(3, produit.getQuantite());
            pst.setDate(4, produit.getDatePeremption());
            pst.setString(5, produit.getImage());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour les informations d'un produit
    public void updateProduit(Mproduit produit) {
        String sql = "UPDATE produit SET nom = ?, prix = ?, quantite = ?, datePeremption = ?, image = ? WHERE idProduit = ?";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setString(1, produit.getNom());
            pst.setDouble(2, produit.getPrix());
            pst.setDouble(3, produit.getQuantite());
            pst.setDate(4, produit.getDatePeremption());
            pst.setString(5, produit.getImage());
            pst.setLong(6, produit.getIdProduit());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un produit en fonction de son identifiant
    public void deleteProduit(Long idProduit) {
        String sql = "DELETE FROM produit WHERE idProduit = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idProduit);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour obtenir un produit par son identifiant
    public Mproduit getProduit(Long idProduit) {
        Mproduit produit = null;
        String sql = "SELECT * FROM produit WHERE idProduit = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idProduit);
            rs = pst.executeQuery();
            if (rs.next()) {
                produit = new Mproduit();
                produit.setIdProduit(rs.getLong("idProduit"));
                produit.setNom(rs.getString("nom"));
                produit.setPrix(rs.getDouble("prix"));
                produit.setQuantite(rs.getDouble("quantite"));
                produit.setDatePeremption(rs.getDate("datePeremption"));
                produit.setImage(rs.getString("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produit;
    }

    // Méthode pour obtenir la liste de tous les produits
    public List<Mproduit> listAllProduits() {
        List<Mproduit> listProduits = new ArrayList<>();
        String sql = "SELECT * FROM produit";
        try {
            con = BD.maConnexion();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Mproduit produit = new Mproduit();
                produit.setIdProduit(rs.getLong("idProduit"));
                produit.setNom(rs.getString("nom"));
                produit.setPrix(rs.getDouble("prix"));
                produit.setQuantite(rs.getDouble("quantite"));
                produit.setDatePeremption(rs.getDate("datePeremption"));
                produit.setImage(rs.getString("image"));
                listProduits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProduits;
    }
}