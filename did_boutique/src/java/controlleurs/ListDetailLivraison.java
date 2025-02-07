package controlleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Mlivraison;
import models.MdetailLivraison;
import models.Mproduit;
import requêtes.Rlivraison;
import requêtes.RdetailLivraison;
import requêtes.Rproduit;

/**
 * Servlet pour l'affichage de la liste des détails de livraison.
 */
@WebServlet(name = "ListDetailLivraison", urlPatterns = {"/listDetailLivraison"})
public class ListDetailLivraison extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RdetailLivraison rdetailLivraison;
    private Rlivraison rlivraison;
    private Rproduit rproduit;

    @Override
    public void init() throws ServletException {
        rdetailLivraison = new RdetailLivraison();
        rlivraison = new Rlivraison();
        rproduit = new Rproduit();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<MdetailLivraison> listDetailsLivraisons = rdetailLivraison.listAllDetailsLivraisons();
        if (listDetailsLivraisons == null) {
            listDetailsLivraisons = new ArrayList<>(); // Initialise la liste si elle est null
        }
        
        List<Mlivraison> listLivraisons = rlivraison.listAllLivraisons();
        if (listLivraisons == null) {
            listLivraisons = new ArrayList<>(); // Initialise la liste si elle est null
        }
        
        List<Mproduit> listProduits = rproduit.listAllProduits();
        if (listProduits == null) {
            listProduits = new ArrayList<>(); // Initialise la liste si elle est null
        }
        
        // Ajoute la liste des détails de livraison à la requête
        request.setAttribute("listDetailsLivraisons", listDetailsLivraisons);
        // Ajoute la liste des livraisons à la requête
        request.setAttribute("listLivraisons", listLivraisons);
        // Ajoute la liste des produits à la requête
        request.setAttribute("listProduits", listProduits);
        // Redirige vers la vue
        request.getRequestDispatcher("/vues/detailLivraison.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Cette méthode peut rester vide si aucune action POST n'est nécessaire
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour l'affichage de la liste des détails de livraison";
    }
}