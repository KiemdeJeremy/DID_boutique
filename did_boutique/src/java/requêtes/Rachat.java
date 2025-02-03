package requêtes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
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
            con = BD.maConnexion();
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
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setDate(1, achat.getDateAchat());
            pst.setDouble(2, achat.getMontant());
            pst.setDouble(3, achat.getSommeEncaisse());
            pst.setDouble(4, achat.getRemise());
            pst.setLong(5, achat.getIdUtilisateur());
            pst.setLong(6, achat.getIdClient());
            pst.setLong(7, achat.getIdAchat());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune mise à jour effectuée pour l'achat ID: " + achat.getIdAchat());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la mise à jour de l'achat : " + e.getMessage());
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
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucun achat trouvé avec l'ID: " + idAchat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la suppression de l'achat : " + e.getMessage());
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
            System.out.println("Erreur lors de la récupération de l'achat : " + e.getMessage());
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
            System.out.println("Erreur lors de la récupération de la liste des achats : " + e.getMessage());
        } finally {
            closeResources();
        }
        return listAchats;
    }

    // Retourne l'achat avec l'ID maximum d'un client donné
    public Machat getMaxAchatByClient(Machat achat) {

        String sql = "SELECT * FROM achat WHERE idClient = ? ORDER BY idAchat DESC LIMIT 1"; // Récupère l'achat avec l'ID maximum

        try {
            con = BD.maConnexion();
            pst = con.prepareStatement(sql);
            pst.setLong(1, achat.getIdClient());
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
            System.out.println("Erreur lors de la récupération de l'achat maximal pour le client : " + e.getMessage());
        } finally {
            closeResources();
        }
        return achat; // Retourne l'achat avec l'ID maximum ou null s'il n'y a pas d'achats
    }

    // Fonction pour créer le ticket de caisse
    // Fonction pour créer le ticket de caisse
    public void generateTicket(Machat achat) {
        System.out.println("Tentative de génération du ticket pour l'achat ID: " + achat.getIdAchat());

        String userHome = System.getProperty("user.home");
        String ticketDirPath = userHome + File.separator + "Desktop" + File.separator + "tickets_DID";
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
            document.add(new Paragraph("------------------------- TICKET DE CAISSE -----------------------", font));

            // Afficher la date d'achat sur la même ligne
            document.add(new Paragraph("Date Achat : " + achat.getDateAchat(), font));

            // Souligner le montant et la remise en rouge
            Font montantFont = new Font(Font.FontFamily.HELVETICA, 15, Font.UNDERLINE, BaseColor.RED);
            document.add(new Paragraph("Montant : " + achat.getMontant(), montantFont)); // Affichage sur la même ligne

            document.add(new Paragraph("Somme Encaissée : " + achat.getSommeEncaisse(), font));

            Font remiseFont = new Font(Font.FontFamily.HELVETICA, 15, Font.UNDERLINE, BaseColor.RED);
            document.add(new Paragraph("Remise : " + achat.getRemise(), remiseFont)); // Affichage sur la même ligne

            document.add(new Paragraph("ID Utilisateur : " + achat.getIdUtilisateur(), font));
            document.add(new Paragraph("ID Client : " + achat.getIdClient(), font));
            document.add(new Paragraph("--------------------------------------------------------------------", font));

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
