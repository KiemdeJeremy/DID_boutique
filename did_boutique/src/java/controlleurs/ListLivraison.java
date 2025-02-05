/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Mlivraison;
import requêtes.Rlivraison;

/**
 * Servlet pour l'affichage de la liste des livraisons.
 */
@WebServlet(name = "ListLivraison", urlPatterns = {"/listLivraison"})
public class ListLivraison extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rlivraison rlivraison;

    @Override
    public void init() throws ServletException {
        rlivraison = new Rlivraison(); // Initialisation de l'objet Rlivraison
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Mlivraison> listLivraisons = rlivraison.listAllLivraisons();
        if (listLivraisons == null) {
            listLivraisons = new ArrayList<>(); // Initialise la liste si elle est null
        }
        
        // Ajoute la liste des livraisons à la requête
        request.setAttribute("listLivraisons", listLivraisons);
        // Redirige vers la vue
        request.getRequestDispatcher("/vues/livraison.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Cette méthode peut rester vide si aucune action POST n'est nécessaire
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour l'affichage de la liste des livraisons.";
    }
}