package controlleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Mproduit;
import requêtes.Rproduit;

/**
 * Servlet pour l'affichage de la liste des produits
 */
@WebServlet(name = "ListProduit", urlPatterns = {"/listProduit"})
public class ListProduit extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rproduit rproduit;

    @Override
    public void init() throws ServletException {
        rproduit = new Rproduit();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Mproduit> listProduits = rproduit.listAllProduits();
        if (listProduits == null) {
            listProduits = new ArrayList<>(); // Initialise la liste si elle est null
        }
        
        // Ajoute la liste des produits à la requête
        request.setAttribute("listProduits", listProduits);
        // Redirige vers la vue
        request.getRequestDispatcher("/vues/produit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Cette méthode peut rester vide si aucune action POST n'est nécessaire
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour l'affichage de la liste des produits";
    }
}