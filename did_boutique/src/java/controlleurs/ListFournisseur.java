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
import models.Mfournisseur;
import requêtes.Rfournisseur;

/**
 *
 * @author USER
 */
@WebServlet(name = "ListFournisseur", urlPatterns = {"/listFournisseur"})
public class ListFournisseur extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rfournisseur rfournisseur;

    @Override
    public void init() throws ServletException {
        rfournisseur = new Rfournisseur();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Mfournisseur> listFournisseurs = rfournisseur.listAllFournisseurs();
        if (listFournisseurs == null) {
            listFournisseurs = new ArrayList<>(); // Initialise la liste si elle est null
        }
        
        // Ajoute la liste des fournisseurs à la requête
        request.setAttribute("listFournisseurs", listFournisseurs);
        // Redirige vers la vue
        request.getRequestDispatcher("/vues/fournisseur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Cette méthode peut rester vide si aucune action POST n'est nécessaire
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour l'affichage de la liste des fournisseurs";
    }
}