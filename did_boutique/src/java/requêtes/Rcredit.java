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
import models.Mcredit;

/**
 *
 * @author USER
 */
public class Rcredit {

    // Connexion à la base de données principale
    Connection con = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    // Méthode pour insérer un crédit dans la base de données
    public void insertCredit(Mcredit credit) {
        String sql = "INSERT INTO credit (dateCredit, montantCredit, dateReglement, statut, idAchat) VALUES (?, ?, ?, ?, ?)";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setDate(1, credit.getDateCredit());
            pst.setDouble(2, credit.getMontantCredit());
            pst.setDate(3, credit.getDateReglement());
            pst.setString(4, credit.getStatut());
            pst.setLong(5, credit.getIdAchat());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour les informations d'un crédit
    public void updateCredit(Mcredit credit) {
        String sql = "UPDATE credit SET dateCredit = ?, montantCredit = ?, dateReglement = ?, statut = ?, idAchat = ? WHERE idCredit = ?";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setDate(1, credit.getDateCredit());
            pst.setDouble(2, credit.getMontantCredit());
            pst.setDate(3, credit.getDateReglement());
            pst.setString(4, credit.getStatut());
            pst.setLong(5, credit.getIdAchat());
            pst.setLong(6, credit.getIdCredit());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un crédit en fonction de son identifiant
    public void deleteCredit(Long idCredit) {
        String sql = "DELETE FROM credit WHERE idCredit = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idCredit);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour obtenir un crédit par son identifiant
    public Mcredit getCredit(Long idCredit) {
        Mcredit credit = null;
        String sql = "SELECT * FROM credit WHERE idCredit = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idCredit);
            rs = pst.executeQuery();
            if (rs.next()) {
                credit = new Mcredit();
                credit.setIdCredit(rs.getLong("idCredit"));
                credit.setDateCredit(rs.getDate("dateCredit"));
                credit.setMontantCredit(rs.getDouble("montantCredit"));
                credit.setDateReglement(rs.getDate("dateReglement"));
                credit.setStatut(rs.getString("statut"));
                credit.setIdAchat(rs.getLong("idAchat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return credit;
    }

    // Méthode pour obtenir la liste de tous les crédits
    public List<Mcredit> listAllCredits() {
        List<Mcredit> listCredits = new ArrayList<>();
        String sql = "SELECT * FROM credit";
        try {
            con = BD.maConnexion();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Mcredit credit = new Mcredit();
                credit.setIdCredit(rs.getLong("idCredit"));
                credit.setDateCredit(rs.getDate("dateCredit"));
                credit.setMontantCredit(rs.getDouble("montantCredit"));
                credit.setDateReglement(rs.getDate("dateReglement"));
                credit.setStatut(rs.getString("statut"));
                credit.setIdAchat(rs.getLong("idAchat"));
                listCredits.add(credit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCredits;
    }
}