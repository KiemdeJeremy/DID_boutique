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
import requêtes.Rclient;

/**
 *
 * @author USER
 */
@WebServlet(name = "ClientSuppression", urlPatterns = {"/clientSuppression"})
public class ClientSuppression extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rclient rclient;

    @Override
    public void init() throws ServletException {
        rclient = new Rclient();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idParam = request.getParameter("idClient"); 
        
        long id = 0; 
        if (idParam != null && !idParam.isEmpty()) {
            try {
                id = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Gérer l'erreur si nécessaire
            }
        } else {
            System.out.println("Paramètre idClient manquant ou vide.");
        }

        // Suppression du client
        rclient.deleteClient(id);
        request.getSession().setAttribute("message", "Client supprimé avec succès");
        // Redirection vers la liste des clients
        response.sendRedirect(request.getContextPath() + "/listClient");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response); // Utilise la méthode doGet pour la suppression
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour la suppression d'un client";
    }
}