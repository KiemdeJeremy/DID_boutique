/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.Mproduit;
import requêtes.Rproduit;

/**
 *
 * @author USER
 */
@WebServlet(name = "ProduitModification", urlPatterns = {"/produitModification"})
@MultipartConfig // Configuration pour le multipart
public class ProduitModification extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rproduit rproduit;

    @Override
    public void init() throws ServletException {
        rproduit = new Rproduit();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupération des paramètres du formulaire
            long id = Long.parseLong(request.getParameter("idProduit"));
            String nom = request.getParameter("nom");
            Double prix = Double.parseDouble(request.getParameter("prix"));
            Double quantite = Double.parseDouble(request.getParameter("quantite"));
            Date datePeremption = Date.valueOf(request.getParameter("datePeremption"));
            Part filePart = request.getPart("image"); // Récupération de l'image
            String ancienneImage = request.getParameter("imageActuelle");

            System.out.println("ID Produit: " + id);
            System.out.println("Nom Produit: " + nom);
            System.out.println("Prix Produit: " + prix);
            System.out.println("Quantité Produit: " + quantite);
            System.out.println("Date de Péremption: " + datePeremption);

            String filePath = null;

            // Gestion de l'image
            if (filePart != null && filePart.getSize() > 0) {
                // Nouvelle image fournie
                String fileName = filePart.getSubmittedFileName();
                filePath = "C:/Users/USER/Desktop/mes projets github/DID_boutique/did_boutique/web/uploads/" + fileName;

                // Écriture du fichier uploadé
                try (FileOutputStream fos = new FileOutputStream(filePath);
                        InputStream is = filePart.getInputStream()) {
                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);
                }
            } else {
                // Pas de nouvelle image, conserver l'ancienne
                filePath = ancienneImage; // Assurez-vous que cette variable est bien initialisée
                try {
//                 Ouvre un flux de sortie pour écrire le fichier uploadé dans le répertoire spécifié (filePath).
                    FileOutputStream fos = new FileOutputStream(ancienneImage);
//                Récupère un flux d'entrée de l'image uploadée.
                    InputStream is = filePart.getInputStream();
//                Alloue un tableau de bytes de la taille du fichier.
                    byte[] data = new byte[is.available()];
//                Lit les données du fichier dans le tableau de bytes.
                    is.read(data);
//                Écrit les données dans le fichier sur le serveur.
                    fos.write(data);
//                 Ferme le flux de sortie pour s'assurer que toutes les données sont bien écrites et que les ressources sont libérées
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Création du produit avec les nouvelles valeurs
            Mproduit produit = new Mproduit();
            produit.setIdProduit(id);
            produit.setNom(nom);
            produit.setPrix(prix);
            produit.setQuantite(quantite);
            produit.setDatePeremption(datePeremption);
            produit.setImage(filePath);

            // Mise à jour du produit
            rproduit.updateProduit(produit);
            request.getSession().setAttribute("messageProduit", "produit modifié avec succes !!!");
            // Redirection vers la liste des produits
            response.sendRedirect(request.getContextPath() + "/listProduit");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("messageProduit", "erreur de modification du produit !!!");
            response.sendRedirect(request.getContextPath() + "/listProduit");
        }
    }

}
