/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Mclient;
import requêtes.Rclient;
import validateurs.ValiderClient;

/**
 *
 * @author USER
 */
@WebServlet(name = "ClientEnregistrement", urlPatterns = {"/clientEnregistrement"})
public class ClientEnregistrement extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rclient rclient;

    @Override
    public void init() throws ServletException {
        rclient = new Rclient();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Initialisation de la liste des erreurs
            List<String> erreurs = new ArrayList<>();

            // Récupération des paramètres
            String nom = request.getParameter("nom");
            String telephoneStr = request.getParameter("telephone");

            // Validation du numéro de téléphone
            Integer telephone = null;
            if (telephoneStr == null || telephoneStr.trim().isEmpty()) {
                erreurs.add("Le numéro de téléphone est requis.");
            } else {
                try {
                    telephone = Integer.parseInt(telephoneStr);
                } catch (NumberFormatException e) {
                    erreurs.add("Le numéro de téléphone doit être un nombre valide.");
                }
            }

            // Création de l'objet client
            Mclient client = new Mclient(nom, telephone);

            // Validation du client
            List<String> erreursValidation = ValiderClient.validerClient(client);
            erreurs.addAll(erreursValidation);

            // Traitement selon la présence d'erreurs
            if (!erreurs.isEmpty()) {
                request.getSession().setAttribute("client", client);
                request.getSession().setAttribute("erreurs", erreurs);
                response.sendRedirect(request.getContextPath() + "/listClient");
                return; // Important : arrêter l'exécution ici
            } else {
                // Aucune erreur, le client est valide
                rclient.insertClient(client);
                Mclient clientAchat = rclient.getClientParNomEtTelephone(nom, telephone);
                request.getSession().setAttribute("message", "Client enregistré avec succès");
                request.getSession().setAttribute("clientAchat", clientAchat);
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
        return "Servlet pour l'enregistrement des clients.";
    }
}
