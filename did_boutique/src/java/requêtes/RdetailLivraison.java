package requêtes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.MdetailLivraison;

/**
 * Classe pour gérer les opérations sur les détails de livraison
 */
public class RdetailLivraison {

    // Connexion à la base de données
    private Connection con = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    // Méthode pour insérer un détail de livraison dans la base de données
    public void insertDetailLivraison(MdetailLivraison detailLivraison) {
        String sql = "INSERT INTO detailLivraison (quantite, prixUnitaire, coutTotal, idLivraison, idProduit) VALUES (?, ?, ?, ?, ?)";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setDouble(1, detailLivraison.getQuantite());
            pst.setDouble(2, detailLivraison.getPrixUnitaire());
            pst.setDouble(3, detailLivraison.getCoutTotal());
            pst.setLong(4, detailLivraison.getIdLivraison());
            pst.setLong(5, detailLivraison.getIdProduit());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Méthode pour mettre à jour un détail de livraison
    public void updateDetailLivraison(MdetailLivraison detailLivraison) {
        String sql = "UPDATE detailLivraison SET quantite = ?, prixUnitaire = ?, coutTotal = ?, idLivraison = ?, idProduit = ? WHERE idDetailLivraison = ?";
        try {
            con = BD.maConnexion();  // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setDouble(1, detailLivraison.getQuantite());
            pst.setDouble(2, detailLivraison.getPrixUnitaire());
            pst.setDouble(3, detailLivraison.getCoutTotal());
            pst.setLong(4, detailLivraison.getIdLivraison());
            pst.setLong(5, detailLivraison.getIdProduit());
            pst.setLong(6, detailLivraison.getIdDetailLivraison());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Méthode pour supprimer un détail de livraison en fonction de son identifiant
    public void deleteDetailLivraison(Long idDetailLivraison) {
        String sql = "DELETE FROM detailLivraison WHERE idDetailLivraison = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idDetailLivraison);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Méthode pour obtenir un détail de livraison par son identifiant
    public MdetailLivraison getDetailLivraison(Long idDetailLivraison) {
        MdetailLivraison detailLivraison = null;
        String sql = "SELECT * FROM detailLivraison WHERE idDetailLivraison = ?";
        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idDetailLivraison);
            rs = pst.executeQuery();
            if (rs.next()) {
                detailLivraison = new MdetailLivraison();
                detailLivraison.setIdDetailLivraison(rs.getLong("idDetailLivraison"));
                detailLivraison.setQuantite(rs.getDouble("quantite"));
                detailLivraison.setPrixUnitaire(rs.getDouble("prixUnitaire"));
                detailLivraison.setCoutTotal(rs.getDouble("coutTotal"));
                detailLivraison.setIdLivraison(rs.getLong("idLivraison"));
                detailLivraison.setIdProduit(rs.getLong("idProduit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return detailLivraison;
    }

    // Méthode pour obtenir la liste de tous les détails de livraison
    public List<MdetailLivraison> listAllDetailsLivraisons() {
        List<MdetailLivraison> listDetailsLivraisons = new ArrayList<>();
        String sql = "SELECT * FROM detailLivraison";
        try {
            con = BD.maConnexion();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                MdetailLivraison detailLivraison = new MdetailLivraison();
                detailLivraison.setIdDetailLivraison(rs.getLong("idDetailLivraison"));
                detailLivraison.setQuantite(rs.getDouble("quantite"));
                detailLivraison.setPrixUnitaire(rs.getDouble("prixUnitaire"));
                detailLivraison.setCoutTotal(rs.getDouble("coutTotal"));
                detailLivraison.setIdLivraison(rs.getLong("idLivraison"));
                detailLivraison.setIdProduit(rs.getLong("idProduit"));
                listDetailsLivraisons.add(detailLivraison);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return listDetailsLivraisons;
    }

    // Méthode pour obtenir la liste des détails de livraison par l'identifiant de livraison
    public List<MdetailLivraison> listDetailsLivraisonByIdLivraison(Long idLivraison) {
        List<MdetailLivraison> listDetailsLivraisons = new ArrayList<>();
        String sql = "SELECT * FROM detailLivraison WHERE idLivraison = ?";
        try {
            con = BD.maConnexion(); // Obtient la connexion vers la base de données 
            pst = con.prepareStatement(sql);
            pst.setLong(1, idLivraison);
            rs = pst.executeQuery();
            while (rs.next()) {
                MdetailLivraison detailLivraison = new MdetailLivraison();
                detailLivraison.setIdDetailLivraison(rs.getLong("idDetailLivraison"));
                detailLivraison.setQuantite(rs.getDouble("quantite"));
                detailLivraison.setPrixUnitaire(rs.getDouble("prixUnitaire"));
                detailLivraison.setCoutTotal(rs.getDouble("coutTotal"));
                detailLivraison.setIdLivraison(rs.getLong("idLivraison"));
                detailLivraison.setIdProduit(rs.getLong("idProduit"));
                listDetailsLivraisons.add(detailLivraison);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return listDetailsLivraisons;
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