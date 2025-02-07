package controlleurs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import requêtes.RdetailLivraison;

/**
 * Servlet pour la suppression d'un détail de livraison.
 */
@WebServlet(name = "DetailLivraisonSuppression", urlPatterns = {"/detailLivraisonSuppression"})
public class DetailLivraisonSuppression extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RdetailLivraison rdetailLivraison;

    @Override
    public void init() throws ServletException {
        rdetailLivraison = new RdetailLivraison();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idParam = request.getParameter("idDetailLivraison"); 
        
        long id = 0; 
        if (idParam != null && !idParam.isEmpty()) {
            try {
                id = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Gérer l'erreur si nécessaire
            }
        } else {
            System.out.println("Paramètre idDetailLivraison manquant ou vide.");
        }

        // Suppression du détail de livraison
        rdetailLivraison.deleteDetailLivraison(id);
        request.getSession().setAttribute("message", "Détail de livraison supprimé avec succès");
        // Redirection vers la liste des détails de livraison
        response.sendRedirect(request.getContextPath() + "/listDetailLivraison");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response); // Utilise la méthode doGet pour la suppression
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour la suppression d'un détail de livraison";
    }
}