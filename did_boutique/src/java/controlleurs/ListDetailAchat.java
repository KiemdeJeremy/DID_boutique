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
import models.MdetailAchat;
import models.Mproduit;
import requêtes.Rachat;
import requêtes.RdetailAchat;
import requêtes.Rproduit;

/**
 * Servlet pour l'affichage de la liste des détails d'achat.
 */
@WebServlet(name = "ListDetailAchat", urlPatterns = {"/listDetailAchat"})
public class ListDetailAchat extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RdetailAchat rdetailAchat;
    private Rachat rachat;
    private Rproduit rproduit;

    @Override
    public void init() throws ServletException {
        rdetailAchat = new RdetailAchat();
        rachat= new Rachat();
        rproduit= new Rproduit();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<MdetailAchat> listDetailsAchats = rdetailAchat.listAllDetailsAchats();
        if (listDetailsAchats == null) {
            listDetailsAchats = new ArrayList<>(); // Initialise la liste si elle est null
        }
        
        List<Machat> listAchats = rachat.listAllAchats();
        if (listAchats == null) {
            listAchats = new ArrayList<>(); // Initialise la liste si elle est null
        }
        
        List<Mproduit> listProduits = rproduit.listAllProduits();
        if (listProduits == null) {
            listProduits = new ArrayList<>(); // Initialise la liste si elle est null
        }
        
        // Ajoute la liste des détails d'achat à la requête
        request.setAttribute("listDetailsAchats", listDetailsAchats);
        // Ajoute la liste des achats à la requête
        request.setAttribute("listAchats", listAchats);
        // Ajoute la liste des produits à la requête
        request.setAttribute("listProduits", listProduits);
        // Redirige vers la vue
        request.getRequestDispatcher("/vues/detailAchat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Cette méthode peut rester vide si aucune action POST n'est nécessaire
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour l'affichage de la liste des détails d'achat";
    }
}