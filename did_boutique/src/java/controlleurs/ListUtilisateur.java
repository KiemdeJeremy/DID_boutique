/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ListUtilisateur", urlPatterns = {"/listUtilisateur"})
public class ListUtilisateur extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rutilisateur rutilisateur;

    @Override
    public void init() throws ServletException {
        rutilisateur = new Rutilisateur();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Mutilisateur> listUtilisateurs = rutilisateur.listAllUtilisateur();
        if (listUtilisateurs == null) {
            listUtilisateurs = new ArrayList<>(); // Initialise la liste si elle est null
        }
      
        request.setAttribute("listUtilisateurs", listUtilisateurs);
        request.getRequestDispatcher("/vues/utilisateur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
