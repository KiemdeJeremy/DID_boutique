/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Mproduit;
import models.Mutilisateur;
import org.mindrot.jbcrypt.BCrypt;
import requêtes.Rproduit;
import requêtes.Rutilisateur;

/**
 *
 * @author USER
 */
@WebServlet(name = "ConnexionServlet", urlPatterns = {"/connexionServlet"})
public class ConnexionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Mutilisateur userConnect = null;
        Rutilisateur rutilisateur = new Rutilisateur();
        String matricule = request.getParameter("matricule");
        String password = request.getParameter("password");

        userConnect = rutilisateur.getUtilisateurByMatricule(matricule);
        if (userConnect != null) {
            if (BCrypt.checkpw(password, userConnect.getPassword())) {
                request.getSession().setAttribute("userConnect", userConnect);
                String userRole = userConnect.getRole();
                String headerJSP;
                switch (userRole.toLowerCase()) {
                    case "caissier":
                        headerJSP = "/vues/mesInclusions/caissierHeader.jsp";
                        break;
                    case "administrateur":
                        headerJSP = "/vues/mesInclusions/adminHeader.jsp";
                        break;
                    case "livreur":
                        headerJSP = "/vues/mesInclusions/livreurHeader.jsp";
                        break;
                    case "magasinier":
                        headerJSP = "/vues/mesInclusions/magasinierHeader.jsp";
                        break;
                    // Ajoutez d'autres cas pour chaque rôle...
                    default:
                        headerJSP = "/vues/mesInclusions/default.jsp"; // Un en-tête par défaut si le rôle n'est pas reconnu
                    }
                request.getSession().setAttribute("headerJSP", headerJSP);

                Rproduit rproduit = new Rproduit();
                List<Mproduit> listDesProduits = rproduit.listAllProduits();
                request.getSession().setAttribute("listDesProduits", listDesProduits);
                request.getRequestDispatcher("/index.jsp").forward(request, response);

            } else {
                request.getSession().setAttribute("error", "matricule ou mot de passe incorrect");
                request.getRequestDispatcher("/connexion.jsp").forward(request, response);
            }
        } else {
            request.getSession().setAttribute("error", "matricule ou mot de passe incorrect");
            request.getRequestDispatcher("/connexion.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Détruire l'attribut de session
        HttpSession session = request.getSession();
        session.removeAttribute("userConnect");

        // Rediriger vers connexion.jsp
        response.sendRedirect(request.getContextPath() + "/connexion.jsp");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
