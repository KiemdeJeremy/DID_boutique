package controlleurs;

import java.io.IOException;
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
 * Servlet pour la modification des détails d'achat.
 */
@WebServlet(name = "DetailAchatModification", urlPatterns = {"/detailAchatModification"})
public class DetailAchatModification extends HttpServlet {

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
            // Récupération de l'ID du détail d'achat à modifier
            Long idDetailAchat = Long.parseLong(request.getParameter("idDetailAchat"));
            
            // Récupération des paramètres du formulaire de modification
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
                request.setAttribute("erreurQuantite", "La quantité est requise.");
            } else {
                try {
                    quantite = Double.parseDouble(quantiteStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("erreurQuantite", "La quantité doit être un nombre valide.");
                }
            }

            // Validation du prix unitaire
            if (prixUnitaireStr == null || prixUnitaireStr.trim().isEmpty()) {
                request.setAttribute("erreurPrixUnitaire", "Le prix unitaire est requis.");
            } else {
                try {
                    prixUnitaire = Double.parseDouble(prixUnitaireStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("erreurPrixUnitaire", "Le prix unitaire doit être un nombre valide.");
                }
            }

            // Validation du coût total
            if (coutTotalStr == null || coutTotalStr.trim().isEmpty()) {
                request.setAttribute("erreurCoutTotal", "Le coût total est requis.");
            } else {
                try {
                    coutTotal = Double.parseDouble(coutTotalStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("erreurCoutTotal", "Le coût total doit être un nombre valide.");
                }
            }

            // Validation des identifiants
            if (idAchatStr == null || idAchatStr.trim().isEmpty()) {
                request.setAttribute("erreurIdAchat", "L'identifiant d'achat est requis.");
            } else {
                try {
                    idAchat = Long.parseLong(idAchatStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("erreurIdAchat", "L'identifiant d'achat doit être un nombre valide.");
                }
            }

            if (idProduitStr == null || idProduitStr.trim().isEmpty()) {
                request.setAttribute("erreurIdProduit", "L'identifiant de produit est requis.");
            } else {
                try {
                    idProduit = Long.parseLong(idProduitStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("erreurIdProduit", "L'identifiant de produit doit être un nombre valide.");
                }
            }

            // Création de l'objet détail d'achat avec les nouvelles valeurs
            MdetailAchat detailAchat = new MdetailAchat(quantite, prixUnitaire, coutTotal, idAchat, idProduit);
            detailAchat.setIdDetailAchat(idDetailAchat);

            // Validation des données
            List<String> erreurs = ValiderDetailAchat.validerDetailAchat(detailAchat);
            if (!erreurs.isEmpty()) {
                request.setAttribute("detailAchat", detailAchat);
                request.setAttribute("erreurs", erreurs);
                response.sendRedirect(request.getContextPath() + "/listDetailAchat");
            } else {
                // Si les données sont valides, mise à jour du détail d'achat
                rdetailAchat.updateDetailAchat(detailAchat);
                request.getSession().setAttribute("message", "Détail d'achat modifié avec succès");
                response.sendRedirect(request.getContextPath() + "/listDetailAchat");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/erreur.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Méthode laissée vide si la logique GET n'est pas nécessaire
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour la modification d'un détail d'achat";
    }
}