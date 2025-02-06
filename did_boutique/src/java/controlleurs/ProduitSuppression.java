/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Mproduit;
import requêtes.Rproduit;

/**
 *
 * @author USER
 */
@WebServlet(name = "ProduitSuppression", urlPatterns = {"/produitSuppression"})
public class ProduitSuppression extends HttpServlet {

    private Rproduit rproduit;

    @Override
    public void init() throws ServletException {
        rproduit = new Rproduit();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Récupération de l'ID du produit
            String idParam = request.getParameter("idProduit");
            if (idParam == null || idParam.trim().isEmpty()) {
                throw new ServletException("ID du produit manquant");
            }
            long idProduit = Long.parseLong(idParam);

            // Récupérer le produit pour obtenir le chemin de l'image
            Mproduit produit = rproduit.getProduit(idProduit);
            if (produit != null && produit.getImage() != null) {
                // Supprimer l'image physique
                String imagePath = getServletContext().getRealPath("/") + produit.getImage();
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    imageFile.delete();
                }
            }

            // Supprimer le produit de la base de données
            rproduit.deleteProduit(idProduit);
            request.getSession().setAttribute("messageProduit", "produit supprimé avec succes !!!");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("messageProduit", "Erreur produit non supprimé !!!");
        }

        // Redirection vers la liste des produits
        response.sendRedirect(request.getContextPath() + "/listProduit");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si vous avez besoin de gérer des suppressions via POST
        doGet(request, response);
    }

}
