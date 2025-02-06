package controlleurs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import requêtes.RdetailAchat;

/**
 * Servlet pour la suppression d'un détail d'achat.
 */
@WebServlet(name = "DetailAchatSuppression", urlPatterns = {"/detailAchatSuppression"})
public class DetailAchatSuppression extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RdetailAchat rdetailAchat;

    @Override
    public void init() throws ServletException {
        rdetailAchat = new RdetailAchat();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idParam = request.getParameter("idDetailAchat"); 
        
        long id = 0; 
        if (idParam != null && !idParam.isEmpty()) {
            try {
                id = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Gérer l'erreur si nécessaire
            }
        } else {
            System.out.println("Paramètre idDetailAchat manquant ou vide.");
        }

        // Suppression du détail d'achat
        rdetailAchat.deleteDetailAchat(id);
        request.getSession().setAttribute("message", "Détail d'achat supprimé avec succès");
        // Redirection vers la liste des détails d'achat
        response.sendRedirect(request.getContextPath() + "/listDetailAchat");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response); // Utilise la méthode doGet pour la suppression
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour la suppression d'un détail d'achat";
    }
}