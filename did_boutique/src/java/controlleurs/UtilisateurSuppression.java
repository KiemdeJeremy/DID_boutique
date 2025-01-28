/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Mutilisateur;
import requêtes.Rutilisateur;

/**
 *
 * @author USER
 */
@WebServlet(name = "UtilisateurSuppression", urlPatterns = {"/utilisateurSuppression"})
public class UtilisateurSuppression extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rutilisateur rutilisateur;

    @Override
    public void init() throws ServletException {
        rutilisateur = new Rutilisateur();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idParam = request.getParameter("idUtilisateur"); 
        
      
        long id = 0; 
        if (idParam != null && !idParam.isEmpty()) {
            try {
                id = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
             
                e.printStackTrace(); 
            }
        } else {
            
            System.out.println("Paramètre idUtilisateu manquant ou vide.");
        }

        // Suppression de l'utilisateur
        rutilisateur.deleteUtilisateur(id);
        request.getSession().setAttribute("message", "Utilisateur supprimé avec succès");
        // Redirection vers la liste des utilisateurs
        response.sendRedirect(request.getContextPath() + "/listUtilisateur");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }

}
