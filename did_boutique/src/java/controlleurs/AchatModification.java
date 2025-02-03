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
            Long idAchat = Long.parseLong(request.getParameter("idAchat"));

            String dateAchatStr = request.getParameter("dateAchat");
            String montantStr = request.getParameter("montant");
            String sommeEncaisseStr = request.getParameter("sommeEncaisse");
            String remiseStr = request.getParameter("remise");
            String idUtilisateurStr = request.getParameter("idUtilisateur");
            String idClientStr = request.getParameter("idClient");

            java.sql.Date dateAchat = null;
            List<String> erreurs = new ArrayList<>();

            // Validation de la date
            if (dateAchatStr != null && !dateAchatStr.trim().isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = dateFormat.parse(dateAchatStr);
                    dateAchat = new java.sql.Date(parsedDate.getTime());
                } catch (ParseException e) {
                    erreurs.add("Format de date invalide.");
                }
            } else {
                erreurs.add("La date d'achat est requise.");
            }

            // Validation des montants
            Double montant = parseDouble(montantStr, erreurs, "Montant");
            Double sommeEncaisse = parseDouble(sommeEncaisseStr, erreurs, "Somme Encaissée");
            Double remise = parseDouble(remiseStr, erreurs, "Remise");
            Long idUtilisateur = parseLong(idUtilisateurStr, erreurs, "ID Utilisateur");
            Long idClient = parseLong(idClientStr, erreurs, "ID Client");

            Machat achat = new Machat(dateAchat, montant, sommeEncaisse, remise, idUtilisateur, idClient);
            achat.setIdAchat(idAchat);

            if (!erreurs.isEmpty()) {
                request.getSession().setAttribute("achat", achat);
                request.getSession().setAttribute("erreursAchat", erreurs);
                response.sendRedirect(request.getContextPath() + "/listClient");
            } else {
                rachat.updateAchat(achat);
                request.getSession().setAttribute("messageAchat", "Achat modifié avec succès");
                response.sendRedirect(request.getContextPath() + "/listClient");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Une erreur est survenue : " + e.getMessage());
            request.getRequestDispatcher("/erreur.jsp").forward(request, response);
        }
    }

    private Double parseDouble(String value, List<String> erreurs, String fieldName) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                erreurs.add(fieldName + " doit être un nombre valide.");
            }
        }
        return null;
    }

    private Long parseLong(String value, List<String> erreurs, String fieldName) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                erreurs.add(fieldName + " doit être un nombre valide.");
            }
        }
        return null;
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
