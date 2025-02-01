package requêtes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Machat;

/**
 * Classe pour gérer les opérations liées aux achats dans la base de données.
 */
public class Rachat {

    // Connexion à la base de données
    private Connection con = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    // Méthode pour insérer un achat dans la base de données
    public void insertAchat(Machat achat) {
        String sql = "INSERT INTO achat (dateAchat, montant, sommeEncaisse, remise, idUtilisateur, idClient) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = BD.maConnexion(); // Obtient la connexion vers la base de données
            pst = con.prepareStatement(sql);
            pst.setDate(1, achat.getDateAchat());
            pst.setDouble(2, achat.getMontant());
            pst.setDouble(3, achat.getSommeEncaisse());
            pst.setDouble(4, achat.getRemise());
            pst.setLong(5, achat.getIdUtilisateur());
            pst.setLong(6, achat.getIdClient());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucun enregistrement n'a été effectué.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'insertion de l'achat : " + e.getMessage());
        } finally {
            closeResources();
        }
    }

    // Méthode pour mettre à jour les informations d'un achat
    public void updateAchat(Machat achat) {
        String sql = "UPDATE achat SET dateAchat = ?, montant = ?, sommeEncaisse = ?, remise = ?, idUtilisateur = ?, idClient = ? WHERE idAchat = ?";
        try {
            con = BD.maConnexion(); // Obtient la connexion vers la base de données
            pst = con.prepareStatement(sql);
            pst.setDate(1, achat.getDateAchat());
            pst.setDouble(2, achat.getMontant());
            pst.setDouble(3, achat.getSommeEncaisse());
            pst.setDouble(4, achat.getRemise());
            pst.setLong(5, achat.getIdUtilisateur());
            pst.setLong(6, achat.getIdClient());
            pst.setLong(7, achat.getIdAchat());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Méthode pour supprimer un achat en fonction de son identifiant
    public void deleteAchat(Long idAchat) {
        String sql = "DELETE FROM achat WHERE idAchat = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idAchat);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Méthode pour obtenir un achat par son identifiant
    public Machat getAchat(Long idAchat) {
        Machat achat = null;
        String sql = "SELECT * FROM achat WHERE idAchat = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idAchat);
            rs = pst.executeQuery();
            if (rs.next()) {
                achat = new Machat();
                achat.setIdAchat(rs.getLong("idAchat"));
                achat.setDateAchat(rs.getDate("dateAchat"));
                achat.setMontant(rs.getDouble("montant"));
                achat.setSommeEncaisse(rs.getDouble("sommeEncaisse"));
                achat.setRemise(rs.getDouble("remise"));
                achat.setIdUtilisateur(rs.getLong("idUtilisateur"));
                achat.setIdClient(rs.getLong("idClient"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return achat;
    }

    // Méthode pour obtenir la liste de tous les achats
    public List<Machat> listAllAchats() {
        List<Machat> listAchats = new ArrayList<>();
        String sql = "SELECT * FROM achat";
        try {
            con = BD.maConnexion();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Machat achat = new Machat();
                achat.setIdAchat(rs.getLong("idAchat"));
                achat.setDateAchat(rs.getDate("dateAchat"));
                achat.setMontant(rs.getDouble("montant"));
                achat.setSommeEncaisse(rs.getDouble("sommeEncaisse"));
                achat.setRemise(rs.getDouble("remise"));
                achat.setIdUtilisateur(rs.getLong("idUtilisateur"));
                achat.setIdClient(rs.getLong("idClient"));
                listAchats.add(achat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return listAchats;
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
