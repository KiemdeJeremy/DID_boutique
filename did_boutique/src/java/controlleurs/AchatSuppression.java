package controlleurs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import requêtes.Rachat;

/**
 * Servlet pour la suppression d'un achat.
 */
@WebServlet(name = "AchatSuppression", urlPatterns = {"/achatSuppression"})
public class AchatSuppression extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rachat rachat;

    @Override
    public void init() throws ServletException {
        rachat = new Rachat();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idParam = request.getParameter("idAchat"); 
        
        long id = 0; 
        if (idParam != null && !idParam.isEmpty()) {
            try {
                id = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Gérer l'erreur si nécessaire
            }
        } else {
            System.out.println("Paramètre idAchat manquant ou vide.");
        }

        // Suppression de l'achat
        rachat.deleteAchat(id);
        request.getSession().setAttribute("messageAchat", "Achat supprimé avec succès");
        // Redirection vers la liste des achats
        response.sendRedirect(request.getContextPath() + "/listAchat");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response); // Utilise la méthode doGet pour la suppression
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour la suppression d'un achat";
    }
}