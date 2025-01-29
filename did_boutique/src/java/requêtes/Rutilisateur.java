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
import models.Mutilisateur;

/**
 *
 * @author USER
 */
public class Rutilisateur {

    // Connexion à la base de données principale
    Connection con = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    // Méthode pour insérer un utilisateur dans la base de données
    public void insertUtilisateur(Mutilisateur utilisateur) {
        String sql = "INSERT INTO utilisateur (nom, prenom, sexe, dateNaissance, matricule, password, role, telephone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = BD.maConnexion();  // ici c'est pour obtenir la connexion vers ma base d données 
            pst = con.prepareStatement(sql);
            pst.setString(1, utilisateur.getNom());
            pst.setString(2, utilisateur.getPrenom());
            pst.setString(3, utilisateur.getSexe());
            pst.setDate(4, new java.sql.Date(utilisateur.getDateNaissance().getTime()));
            pst.setString(5, utilisateur.getMatricule());
            pst.setString(6, utilisateur.getPassword());
            pst.setString(7, utilisateur.getRole());
            pst.setInt(8, utilisateur.getTelephone());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour les informations d'un utilisateur
    public void updateUtilisateur(Mutilisateur utilisateur) {
        String sql = "UPDATE utilisateur SET nom = ?, prenom = ?, sexe = ?, dateNaissance = ?, matricule = ?, password = ?, role = ?, telephone = ? WHERE idUtilisateur = ?";
        try {
            con = BD.maConnexion();  // ici c'est pour obtenir la connexion vers ma base d données 
            pst = con.prepareStatement(sql);
            pst.setString(1, utilisateur.getNom());
            pst.setString(2, utilisateur.getPrenom());
            pst.setString(3, utilisateur.getSexe());
            pst.setDate(4, new java.sql.Date(utilisateur.getDateNaissance().getTime()));
            pst.setString(5, utilisateur.getMatricule());
            pst.setString(6, utilisateur.getPassword());
            pst.setString(7, utilisateur.getRole());
            pst.setInt(8, utilisateur.getTelephone());

            pst.setLong(9, utilisateur.getIdUtilisateur());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un utilisateur en fonction de son identifiant
    public void deleteUtilisateur(Long idUtilisateur) {
        String sql = "DELETE FROM utilisateur WHERE idUtilisateur = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idUtilisateur);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour obtenir un utilisateur par son identifiant
    public Mutilisateur getUtilisateur(Long idUtilisateur) {
        Mutilisateur utilisateur = null;
        String sql = "SELECT * FROM utilisateur WHERE idUtilisateur = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idUtilisateur);
            rs = pst.executeQuery();
            if (rs.next()) {
                utilisateur = new Mutilisateur();
                utilisateur.setIdUtilisateur(rs.getLong("id"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setSexe(rs.getString("sexe"));
                utilisateur.setMatricule(rs.getString("matricule"));
                utilisateur.setPassword(rs.getString("password"));
                utilisateur.setDateNaissance(rs.getDate("dateNaissance"));
                utilisateur.setRole(rs.getString("role"));
                utilisateur.setTelephone(rs.getInt("telephone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }
    
    // Méthode pour obtenir un utilisateur par son matricule
    public Boolean getUtilisateurByMatricule(String matricule) {
        Mutilisateur utilisateur = null;
        String sql = "SELECT * FROM utilisateur WHERE matricule = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setString(1, matricule);
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Méthode pour obtenir la liste de tous les utilisateurs
    public List<Mutilisateur> listAllUtilisateur() {
        List<Mutilisateur> listMutilisateur = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur";
        try {
            con = BD.maConnexion();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Mutilisateur utilisateur = new Mutilisateur();
                utilisateur.setIdUtilisateur(rs.getLong("idUtilisateur"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setSexe(rs.getString("sexe"));
                utilisateur.setMatricule(rs.getString("matricule"));
                utilisateur.setPassword(rs.getString("password"));
                utilisateur.setDateNaissance(rs.getDate("dateNaissance"));
                utilisateur.setRole(rs.getString("role"));
                utilisateur.setTelephone(rs.getInt("telephone"));
                listMutilisateur.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listMutilisateur;
    }

}
