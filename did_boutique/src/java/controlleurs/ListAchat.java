package controlleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Machat;
import requêtes.Rachat;

/**
 * Servlet pour l'affichage de la liste des achats.
 */
@WebServlet(name = "ListAchat", urlPatterns = {"/listAchat"})
public class ListAchat extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rachat rachat;

    @Override
    public void init() throws ServletException {
        rachat = new Rachat();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Machat> listAchats = rachat.listAllAchats();
        if (listAchats == null) {
            listAchats = new ArrayList<>(); // Initialise la liste si elle est null
        }
        
        // Ajoute la liste des achats à la requête
        request.getSession().setAttribute("listAchats", listAchats);

        // Redirection vers la vue 
        // normalement cela devrait être vers la vue achat.jsp mais je l'ai combiné avec la vue client.jsp
        request.getRequestDispatcher("/vues/client.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Cette méthode peut rester vide si aucune action POST n'est nécessaire
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour l'affichage de la liste des achats";
    }
}