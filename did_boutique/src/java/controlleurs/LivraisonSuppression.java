/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import requêtes.Rlivraison;

/**
 * Servlet pour la suppression d'une livraison.
 */
@WebServlet(name = "LivraisonSuppression", urlPatterns = {"/livraisonSuppression"})
public class LivraisonSuppression extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rlivraison rlivraison;

    @Override
    public void init() throws ServletException {
        rlivraison = new Rlivraison(); // Initialisation de l'objet Rlivraison
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idParam = request.getParameter("idLivraison"); 
        
        long id = 0; 
        if (idParam != null && !idParam.isEmpty()) {
            try {
                id = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Gérer l'erreur si nécessaire
            }
        } else {
            System.out.println("Paramètre idLivraison manquant ou vide.");
        }

        // Suppression de la livraison
        rlivraison.deleteLivraison(id);
        request.getSession().setAttribute("messageLivraison", "Livraison supprimée avec succès");
        // Redirection vers la liste des livraisons
        response.sendRedirect(request.getContextPath() + "/listLivraison");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response); // Utilise la méthode doGet pour la suppression
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour la suppression d'une livraison.";
    }
}