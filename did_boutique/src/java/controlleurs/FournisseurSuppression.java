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
import requêtes.Rfournisseur;

/**
 *
 * @author USER
 */
@WebServlet(name = "FournisseurSuppression", urlPatterns = {"/fournisseurSuppression"})
public class FournisseurSuppression extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rfournisseur rfournisseur;

    @Override
    public void init() throws ServletException {
        rfournisseur = new Rfournisseur();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idParam = request.getParameter("idFournisseur"); 
        
        long id = 0; 
        if (idParam != null && !idParam.isEmpty()) {
            try {
                id = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Gérer l'erreur si nécessaire
            }
        } else {
            System.out.println("Paramètre idFournisseur manquant ou vide.");
        }

        // Suppression du fournisseur
        rfournisseur.deleteFournisseur(id);
        request.getSession().setAttribute("message", "Fournisseur supprimé avec succès");
        // Redirection vers la liste des fournisseurs
        response.sendRedirect(request.getContextPath() + "/listFournisseur");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response); // Utilise la méthode doGet pour la suppression
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour la suppression d'un fournisseur";
    }
}