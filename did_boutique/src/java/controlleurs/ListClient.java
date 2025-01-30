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
import models.Mclient;
import requêtes.Rclient;

/**
 *
 * @author USER
 */
@WebServlet(name = "ListClient", urlPatterns = {"/listClient"})
public class ListClient extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rclient rclient;

    @Override
    public void init() throws ServletException {
        rclient = new Rclient();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Mclient> listClients = rclient.listAllClients();
        if (listClients == null) {
            listClients = new ArrayList<>(); // Initialise la liste si elle est null
        }
        
        // Ajoute la liste des clients à la requête
        request.setAttribute("listClients", listClients);
        // Redirige vers la vue
        request.getRequestDispatcher("/vues/client.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Cette méthode peut rester vide si aucune action POST n'est nécessaire
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour l'affichage de la liste des clients";
    }
}