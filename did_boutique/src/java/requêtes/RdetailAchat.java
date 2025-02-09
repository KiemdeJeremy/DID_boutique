package requêtes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import models.Machat;
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

    //fonction pour creer le ticket du client
    public void generateTicket(Machat achat, List<MdetailAchat> detailAchat) {
        System.out.println("Tentative de génération du ticket pour l'achat ID: " + achat.getIdAchat());

        String userHome = System.getProperty("user.home");
        String ticketDirPath = userHome + File.separator + "Desktop" + File.separator + "tickets_Clients_DID";
        File ticketDir = new File(ticketDirPath);

        if (!ticketDir.exists()) {
            ticketDir.mkdir();
        }

        String filePath = ticketDirPath + File.separator + "ticket_" + achat.getIdAchat() + ".pdf";

        try {
            Document document = new Document(PageSize.A5, 20, 10, 10, 10); // Augmenter la marge gauche
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Charger le logo depuis le classpath
            InputStream is = getClass().getResourceAsStream("/images/DID.jpg");
            if (is == null) {
                throw new FileNotFoundException("Image non trouvée dans le classpath : /images/DID.jpg");
            }
            Image logo = Image.getInstance(ImageIO.read(is), null);
            logo.scaleToFit(125, 125); // Ajuster la taille du logo (larger)
            document.add(logo);

            // Agrandir la taille de la police
            Font font = new Font(Font.FontFamily.HELVETICA, 15); // Taille de police de 15 points
            Font montantFont = new Font(Font.FontFamily.HELVETICA, 15, Font.UNDERLINE, BaseColor.RED);
            document.add(new Paragraph("------------------------- TICKET DE CAISSE -----------------------", font));

            // je vais maintenant boucler pour afficher la liste des details dans le ticket
            for (MdetailAchat detailAchats : detailAchat) {

                // Souligner le montant et la remise en rouge
                document.add(new Paragraph("ID DetailAchat: " + detailAchats.getIdDetailAchat(), font));
                document.add(new Paragraph("Quantite: " + detailAchats.getQuantite(), font));
                document.add(new Paragraph("Prix Unitaire: " + detailAchats.getPrixUnitaire(), font));
                document.add(new Paragraph("ID Produit : " + detailAchats.getIdProduit(), font));
                document.add(new Paragraph("Cout Total: " + detailAchats.getCoutTotal(), montantFont));
                document.add(new Paragraph("---------------------------------------------------------------------------", font));

            }
            document.add(new Paragraph("ID Achat : " + achat.getIdAchat(), font));
            document.add(new Paragraph("Date Achat : " + achat.getDateAchat(), font));
            document.add(new Paragraph("ID Utilisateur : " + achat.getIdUtilisateur(), font));
            document.add(new Paragraph("ID Client : " + achat.getIdClient(), font));

            // Ajouter un message de remerciement en bas à droite
            Paragraph remerciement = new Paragraph("Merci de votre fidélité !", font);
            remerciement.setAlignment(Element.ALIGN_RIGHT);
            document.add(remerciement);

            document.close();
            System.out.println("Ticket PDF généré : " + filePath);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la génération du ticket : " + e.getMessage());
        }
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
