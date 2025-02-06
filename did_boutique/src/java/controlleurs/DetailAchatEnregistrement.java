package controlleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.MdetailAchat;
import requêtes.RdetailAchat;
import validateurs.ValiderDetailAchat;

/**
 * Servlet pour l'enregistrement des détails d'achat.
 */
@WebServlet(name = "DetailAchatEnregistrement", urlPatterns = {"/detailAchatEnregistrement"})
public class DetailAchatEnregistrement extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RdetailAchat rdetailAchat;

    @Override
    public void init() throws ServletException {
        rdetailAchat = new RdetailAchat();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Initialisation de la liste des erreurs
            List<String> erreurs = new ArrayList<>();

            // Récupération des paramètres
            String quantiteStr = request.getParameter("quantite");
            String prixUnitaireStr = request.getParameter("prixUnitaire");
            String coutTotalStr = request.getParameter("coutTotal");
            String idAchatStr = request.getParameter("idAchat");
            String idProduitStr = request.getParameter("idProduit");

            // Conversion et validation des paramètres
            Double quantite = null;
            Double prixUnitaire = null;
            Double coutTotal = null;
            Long idAchat = null;
            Long idProduit = null;

            // Validation de la quantité
            if (quantiteStr == null || quantiteStr.trim().isEmpty()) {
                erreurs.add("La quantité est requise.");
            } else {
                try {
                    quantite = Double.parseDouble(quantiteStr);
                } catch (NumberFormatException e) {
                    erreurs.add("La quantité doit être un nombre valide.");
                }
            }

            // Validation du prix unitaire
            if (prixUnitaireStr == null || prixUnitaireStr.trim().isEmpty()) {
                erreurs.add("Le prix unitaire est requis.");
            } else {
                try {
                    prixUnitaire = Double.parseDouble(prixUnitaireStr);
                } catch (NumberFormatException e) {
                    erreurs.add("Le prix unitaire doit être un nombre valide.");
                }
            }

            // Validation du coût total
            if (coutTotalStr == null || coutTotalStr.trim().isEmpty()) {
                erreurs.add("Le coût total est requis.");
            } else {
                try {
                    coutTotal = Double.parseDouble(coutTotalStr);
                } catch (NumberFormatException e) {
                    erreurs.add("Le coût total doit être un nombre valide.");
                }
            }

            // Validation des identifiants
            if (idAchatStr == null || idAchatStr.trim().isEmpty()) {
                erreurs.add("L'identifiant d'achat est requis.");
            } else {
                try {
                    idAchat = Long.parseLong(idAchatStr);
                } catch (NumberFormatException e) {
                    erreurs.add("L'identifiant d'achat doit être un nombre valide.");
                }
            }

            if (idProduitStr == null || idProduitStr.trim().isEmpty()) {
                erreurs.add("L'identifiant de produit est requis.");
            } else {
                try {
                    idProduit = Long.parseLong(idProduitStr);
                } catch (NumberFormatException e) {
                    erreurs.add("L'identifiant de produit doit être un nombre valide.");
                }
            }

            // Création de l'objet détail d'achat
            MdetailAchat detailAchat = new MdetailAchat(quantite, prixUnitaire, coutTotal, idAchat, idProduit);

            // Validation du détail d'achat
            erreurs = ValiderDetailAchat.validerDetailAchat(detailAchat);
            erreurs.addAll(erreurs);

            // Traitement selon la présence d'erreurs
            if (!erreurs.isEmpty()) {
                request.getSession().setAttribute("detailAchatTransmis", detailAchat);
                request.getSession().setAttribute("erreurs", erreurs);
                response.sendRedirect(request.getContextPath() + "/listDetailAchat"); // Redirige vers la liste des détails d'achat
                return; // Arrête l'exécution ici
            } else {
                // Aucune erreur, le détail d'achat est valide
                rdetailAchat.insertDetailAchat(detailAchat);
                request.getSession().setAttribute("message", "Détail d'achat enregistré avec succès");
                response.sendRedirect(request.getContextPath() + "/listDetailAchat"); // Redirige vers la liste des détails d'achat
                return; // Arrête l'exécution ici
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur système s'est produite");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Méthode laissée vide si la logique GET n'est pas nécessaire
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour l'enregistrement des détails d'achat.";
    }
}