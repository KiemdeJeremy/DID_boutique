package requêtes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.MdetailAchat;

/**
 * Classe pour gérer les opérations sur les détails d'achat
 */
public class RdetailAchat {

    // Connexion à la base de données
    private Connection con = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    // Méthode pour insérer un détail d'achat dans la base de données
    public void insertDetailAchat(MdetailAchat detailAchat) {
        String sql = "INSERT INTO detailachat (quantite, prixUnitaire, coutTotal, idAchat, idProduit) VALUES (?, ?, ?, ?, ?)";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setDouble(1, detailAchat.getQuantite());
            pst.setDouble(2, detailAchat.getPrixUnitaire());
            pst.setDouble(3, detailAchat.getCoutTotal());
            pst.setLong(4, detailAchat.getIdAchat());
            pst.setLong(5, detailAchat.getIdProduit());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Méthode pour mettre à jour un détail d'achat
    public void updateDetailAchat(MdetailAchat detailAchat) {
        String sql = "UPDATE detailachat SET quantite = ?, prixUnitaire = ?, coutTotal = ?, idAchat = ?, idProduit = ? WHERE idDetailAchat = ?";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setDouble(1, detailAchat.getQuantite());
            pst.setDouble(2, detailAchat.getPrixUnitaire());
            pst.setDouble(3, detailAchat.getCoutTotal());
            pst.setLong(4, detailAchat.getIdAchat());
            pst.setLong(5, detailAchat.getIdProduit());
            pst.setLong(6, detailAchat.getIdDetailAchat());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Méthode pour supprimer un détail d'achat en fonction de son identifiant
    public void deleteDetailAchat(Long idDetailAchat) {
        String sql = "DELETE FROM detailachat WHERE idDetailAchat = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idDetailAchat);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Méthode pour obtenir un détail d'achat par son identifiant
    public MdetailAchat getDetailAchat(Long idDetailAchat) {
        MdetailAchat detailAchat = null;
        String sql = "SELECT * FROM detailachat WHERE idDetailAchat = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idDetailAchat);
            rs = pst.executeQuery();
            if (rs.next()) {
                detailAchat = new MdetailAchat();
                detailAchat.setIdDetailAchat(rs.getLong("idDetailAchat"));
                detailAchat.setQuantite(rs.getDouble("quantite"));
                detailAchat.setPrixUnitaire(rs.getDouble("prixUnitaire"));
                detailAchat.setCoutTotal(rs.getDouble("coutTotal"));
                detailAchat.setIdAchat(rs.getLong("idAchat"));
                detailAchat.setIdProduit(rs.getLong("idProduit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return detailAchat;
    }

    // Méthode pour obtenir la liste de tous les détails d'achat
    public List<MdetailAchat> listAllDetailsAchats() {
        List<MdetailAchat> listDetailsAchats = new ArrayList<>();
        String sql = "SELECT * FROM detailachat";
        try {
            con = BD.maConnexion();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                MdetailAchat detailAchat = new MdetailAchat();
                detailAchat.setIdDetailAchat(rs.getLong("idDetailAchat"));
                detailAchat.setQuantite(rs.getDouble("quantite"));
                detailAchat.setPrixUnitaire(rs.getDouble("prixUnitaire"));
                detailAchat.setCoutTotal(rs.getDouble("coutTotal"));
                detailAchat.setIdAchat(rs.getLong("idAchat"));
                detailAchat.setIdProduit(rs.getLong("idProduit"));
                listDetailsAchats.add(detailAchat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return listDetailsAchats;
    }

    // Méthode pour obtenir la liste des détails d'achat par l'identifiant d'achat
    public List<MdetailAchat> listDetailsAchatByIdAchat(Long idAchat) {
        List<MdetailAchat> listDetailsAchats = new ArrayList<>();
        String sql = "SELECT * FROM detailachat WHERE idAchat = ?";
        try {
            con = BD.maConnexion(); // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setLong(1, idAchat);
            rs = pst.executeQuery();
            while (rs.next()) {
                MdetailAchat detailAchat = new MdetailAchat();
                detailAchat.setIdDetailAchat(rs.getLong("idDetailAchat"));
                detailAchat.setQuantite(rs.getDouble("quantite"));
                detailAchat.setPrixUnitaire(rs.getDouble("prixUnitaire"));
                detailAchat.setCoutTotal(rs.getDouble("coutTotal"));
                detailAchat.setIdAchat(rs.getLong("idAchat"));
                detailAchat.setIdProduit(rs.getLong("idProduit"));
                listDetailsAchats.add(detailAchat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDetailsAchats;
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
