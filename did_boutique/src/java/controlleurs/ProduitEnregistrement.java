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
@WebServlet(name = "ProduitEnregistrement", urlPatterns = {"/produitEnregistrement"})
@MultipartConfig // Configuration pour le multipart
public class ProduitEnregistrement extends HttpServlet {

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
            String nom = request.getParameter("nom");
            Double prix = Double.parseDouble(request.getParameter("prix"));
            Double quantite = Double.parseDouble(request.getParameter("quantite"));
            Date datePeremption = Date.valueOf(request.getParameter("datePeremption"));
            Part filePart = request.getPart("image"); // Récupération de l'image

            // Gestion du chemin de sauvegarde de l'image
            String fileName = filePart.getSubmittedFileName();
            String filePath = "C:/Users/USER/Desktop/mes projets github/DID_boutique/did_boutique/web/uploads/" + fileName;

            // Écriture du fichier uploadé
            try (FileOutputStream fos = new FileOutputStream(filePath);
                    InputStream is = filePart.getInputStream()) {
                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Création du produit
            Mproduit produit = new Mproduit(nom, prix, quantite, datePeremption, filePath);
            rproduit.insertProduit(produit);

            // Redirection vers la liste des produits
            response.sendRedirect(request.getContextPath() + "/listProduit");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur lors de l'enregistrement du produit.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}
