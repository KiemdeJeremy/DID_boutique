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
import models.Mcredit;
import requêtes.Rachat;
import requêtes.Rcredit;
import validateurs.ValiderAchat;

/**
 * Servlet pour l'enregistrement des achats.
 */
@WebServlet(name = "AchatEnregistrement", urlPatterns = {"/achatEnregistrement"})
public class AchatEnregistrement extends HttpServlet {

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
            // Initialisation de la liste des erreurs
            List<String> erreurs = new ArrayList<>();

            // Récupération des paramètres
            String dateAchatStr = request.getParameter("dateAchat");
            String montantStr = request.getParameter("montant");
            String sommeEncaisseStr = request.getParameter("sommeEncaisse");
            String remiseStr = request.getParameter("remise");
            String idUtilisateurStr = request.getParameter("idUtilisateur");
            String idClientStr = request.getParameter("idClient");

            // Validation de la date avant conversion
            java.sql.Date dateAchat = null;
            if (dateAchatStr != null && !dateAchatStr.trim().isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = dateFormat.parse(dateAchatStr);
                    dateAchat = new java.sql.Date(parsedDate.getTime());
                } catch (ParseException e) {
                    erreurs.add("Le format de la date d'achat est invalide");
                }
            } else {
                erreurs.add("La date d'achat est requise.");
            }

            // Validation des autres paramètres
            Double montant = null;
            if (montantStr != null && !montantStr.trim().isEmpty()) {
                try {
                    montant = Double.parseDouble(montantStr);
                    if (montant < 0) {
                        erreurs.add("Le montant doit être positif.");
                    }
                } catch (NumberFormatException e) {
                    erreurs.add("Le montant doit être un nombre valide.");
                }
            } else {
                erreurs.add("Le montant est requis.");
            }

            Double sommeEncaisse = null;
            if (sommeEncaisseStr != null && !sommeEncaisseStr.trim().isEmpty()) {
                try {
                    sommeEncaisse = Double.parseDouble(sommeEncaisseStr);
                    if (sommeEncaisse < 0) {
                        erreurs.add("La somme encaissée doit être positive.");
                    }
                } catch (NumberFormatException e) {
                    erreurs.add("La somme encaissée doit être un nombre valide.");
                }
            } else {
                erreurs.add("La somme encaissée est requise.");
            }

            Double remise = null;
            if (remiseStr != null && !remiseStr.trim().isEmpty()) {
                try {
                    remise = Double.parseDouble(remiseStr);
                } catch (NumberFormatException e) {
                    erreurs.add("La remise doit être un nombre valide.");
                }
            }

            Long idUtilisateur = null;
            if (idUtilisateurStr != null && !idUtilisateurStr.trim().isEmpty()) {
                try {
                    idUtilisateur = Long.parseLong(idUtilisateurStr);
                    if (idUtilisateur <= 0) {
                        erreurs.add("L'identifiant de l'utilisateur doit être positif.");
                    }
                } catch (NumberFormatException e) {
                    erreurs.add("L'identifiant de l'utilisateur doit être un nombre valide.");
                }
            } else {
                erreurs.add("L'identifiant de l'utilisateur est requis.");
            }

            Long idClient = null;
            if (idClientStr != null && !idClientStr.trim().isEmpty()) {
                try {
                    idClient = Long.parseLong(idClientStr);
                    if (idClient <= 0) {
                        erreurs.add("L'identifiant du client doit être positif.");
                    }
                } catch (NumberFormatException e) {
                    erreurs.add("L'identifiant du client doit être un nombre valide.");
                }
            } else {
                erreurs.add("L'identifiant du client est requis.");
            }

            // Création de l'objet achat
            Machat achat = new Machat(dateAchat, montant, sommeEncaisse, remise, idUtilisateur, idClient);

            // Validation de l'achat
            List<String> erreursValidation = ValiderAchat.validerAchat(achat);
            erreurs.addAll(erreursValidation);

            // Traitement selon la présence d'erreurs
            if (!erreurs.isEmpty()) {
                request.getSession().setAttribute("achat", achat);
                request.getSession().setAttribute("erreursAchat", erreurs);
                response.sendRedirect(request.getContextPath() + "/listClient");
                return; // Important : arrêter l'exécution ici
            } else {
                // Aucune erreur, l'achat est valide 
                rachat.insertAchat(achat);
                request.getSession().setAttribute("messageAchat", "Achat enregistré avec succès");

                Machat achatTicket = rachat.getMaxAchatByClient(achat);
                // Générer le ticket de caisse
                rachat.generateTicket(achatTicket);

                //insertion dans la table credit si la somme encaissée est inférieur au montant de l'achet
                if (achatTicket.getSommeEncaisse() < achatTicket.getMontant()) {

                    Mcredit credit = new Mcredit();
                    credit.setDateCredit(dateAchat);
                    credit.setMontantCredit(remise);
                    Date dateReglement = null;
                    credit.setDateReglement(dateReglement);
                    credit.setStatut("non paye");
                    credit.setIdAchat(achatTicket.getIdAchat());
                    //insertion
                    Rcredit rcredit = new Rcredit();
                    rcredit.insertCredit(credit);
                }
                response.sendRedirect(request.getContextPath() + "/listClient");
                return; // Important : arrêter l'exécution ici
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
        return "Servlet pour l'enregistrement des achats.";
    }
}
