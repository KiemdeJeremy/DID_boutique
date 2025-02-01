package controlleurs;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Machat;
import requêtes.Rachat;
import validateurs.ValiderAchat;

/**
 * Servlet pour la modification d'un achat.
 */
@WebServlet(name = "AchatModification", urlPatterns = {"/achatModification"})
public class AchatModification extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rachat rachat;

    @Override
    public void init() throws ServletException {
        rachat = new Rachat();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            // Récupération de l'ID de l'achat à modifier
            Long idAchat = Long.parseLong(request.getParameter("idAchat"));

            // Récupération des paramètres du formulaire de modification
            String dateAchatStr = request.getParameter("dateAchat");
            String montantStr = request.getParameter("montant");
            String sommeEncaisseStr = request.getParameter("sommeEncaisse");
            String remiseStr = request.getParameter("remise");
            String idUtilisateurStr = request.getParameter("idUtilisateur");
            String idClientStr = request.getParameter("idClient");

            // Validation des paramètres
            java.sql.Date dateAchat = null;
            if (dateAchatStr != null && !dateAchatStr.trim().isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = dateFormat.parse(dateAchatStr);
                    dateAchat = new java.sql.Date(parsedDate.getTime());
                } catch (ParseException e) {
                    
                }
            } else {
               
            }
            Double montant = null;
            if (montantStr != null && !montantStr.trim().isEmpty()) {
                try {
                    montant = Double.parseDouble(montantStr);
                } catch (NumberFormatException e) {
                    // Gérer l'erreur pour montant
                }
            }

            Double sommeEncaisse = null;
            if (sommeEncaisseStr != null && !sommeEncaisseStr.trim().isEmpty()) {
                try {
                    sommeEncaisse = Double.parseDouble(sommeEncaisseStr);
                } catch (NumberFormatException e) {
                    // Gérer l'erreur pour sommeEncaisse
                }
            }

            Double remise = null;
            if (remiseStr != null && !remiseStr.trim().isEmpty()) {
                try {
                    remise = Double.parseDouble(remiseStr);
                } catch (NumberFormatException e) {
                    // Gérer l'erreur pour remise
                }
            }

            Long idUtilisateur = null;
            if (idUtilisateurStr != null && !idUtilisateurStr.trim().isEmpty()) {
                try {
                    idUtilisateur = Long.parseLong(idUtilisateurStr);
                } catch (NumberFormatException e) {
                    // Gérer l'erreur pour idUtilisateur
                }
            }

            Long idClient = null;
            if (idClientStr != null && !idClientStr.trim().isEmpty()) {
                try {
                    idClient = Long.parseLong(idClientStr);
                } catch (NumberFormatException e) {
                    // Gérer l'erreur pour idClient
                }
            }

            // Création de l'objet achat avec les nouvelles valeurs
            Machat achat = new Machat(dateAchat, montant, sommeEncaisse, remise, idUtilisateur, idClient);
            achat.setIdAchat(idAchat);

            // Validation des données
            List<String> erreurs = ValiderAchat.validerAchat(achat);

            if (!erreurs.isEmpty()) {
                // En cas d'erreurs de validation
                request.setAttribute("achat", achat);
                request.setAttribute("erreursAchat", erreurs);
                response.sendRedirect(request.getContextPath() + "/listAchat");
            } else {
                // Si les données sont valides, mise à jour de l'achat
                rachat.updateAchat(achat);
                request.getSession().setAttribute("messageAchat", "Achat modifié avec succès");
                response.sendRedirect(request.getContextPath() + "/listAchat");
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
        return "Servlet pour la modification d'un achat";
    }
}
