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
import models.Mclient;

/**
 *
 * @author USER
 */
public class Rclient {

    // Connexion à la base de données principale
    private Connection con = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    // Méthode pour insérer un client dans la base de données
    public void insertClient(Mclient client) {
        String sql = "INSERT INTO client (nom, telephone) VALUES (?, ?)";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setString(1, client.getNom());
            pst.setInt(2, client.getTelephone());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Méthode pour mettre à jour les informations d'un client
    public void updateClient(Mclient client) {
        String sql = "UPDATE client SET nom = ?, telephone = ? WHERE idClient = ?";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setString(1, client.getNom());
            pst.setInt(2, client.getTelephone());
            pst.setLong(3, client.getIdClient());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Méthode pour supprimer un client en fonction de son identifiant
    public void deleteClient(Long idClient) {
        String sql = "DELETE FROM client WHERE idClient = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idClient);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Méthode pour obtenir un client par son identifiant
    public Mclient getClient(Long idClient) {
        Mclient client = null;
        String sql = "SELECT * FROM client WHERE idClient = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idClient);
            rs = pst.executeQuery();
            if (rs.next()) {
                client = new Mclient();
                client.setIdClient(rs.getLong("idClient"));
                client.setNom(rs.getString("nom"));
                client.setTelephone(rs.getInt("telephone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return client;
    }
// Méthode pour obtenir un client par son nom et son numéro de téléphone avec l'idClient le plus élevé

    public Mclient getClientParNomEtTelephone(String nom, Integer telephone) {
        Mclient client = null;
        String sql = "SELECT * FROM client WHERE idClient = (SELECT MAX(idClient) FROM client WHERE nom = ? AND telephone = ?)";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setString(1, nom);
            pst.setInt(2, telephone);
            rs = pst.executeQuery();
            if (rs.next()) {
                client = new Mclient();
                client.setIdClient(rs.getLong("idClient"));
                client.setNom(rs.getString("nom"));
                client.setTelephone(rs.getInt("telephone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return client;
    }

    // Méthode pour obtenir la liste de tous les clients
    public List<Mclient> listAllClients() {
        List<Mclient> listClients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try {
            con = BD.maConnexion();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Mclient client = new Mclient();
                client.setIdClient(rs.getLong("idClient"));
                client.setNom(rs.getString("nom"));
                client.setTelephone(rs.getInt("telephone"));
                listClients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return listClients;
    }

    // Méthode pour fermer les ressources
    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
