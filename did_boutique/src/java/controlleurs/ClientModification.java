/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
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
@WebServlet(name = "ClientModification", urlPatterns = {"/clientModification"})
public class ClientModification extends HttpServlet {

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
            // Récupération de l'ID du client à modifier
            Long idClient = Long.parseLong(request.getParameter("idClient"));
            
            // Récupération des paramètres du formulaire de modification
            String nom = request.getParameter("nom");
            String telephoneStr = request.getParameter("telephone");

            // Validation du numéro de téléphone
            Integer telephone = null;

            if (telephoneStr != null && !telephoneStr.trim().isEmpty()) {
                try {
                    telephone = Integer.parseInt(telephoneStr);
                } catch (NumberFormatException e) {
                    // Gérer l'erreur pour telephone
                }
            }

            // Création de l'objet client avec les nouvelles valeurs
            Mclient client = new Mclient(nom, telephone);
            client.setIdClient(idClient);

            // Validation des données
            List<String> erreurs = ValiderClient.validerClient(client);

            if (!erreurs.isEmpty()) {
                // En cas d'erreurs de validation
                request.setAttribute("client", client);
                request.setAttribute("erreurs", erreurs);
                response.sendRedirect(request.getContextPath() + "/listClient");
            } else {
                // Si les données sont valides, mise à jour du client
                rclient.updateClient(client);
                Mclient clientAchat = rclient.getClientParNomEtTelephone(nom, telephone);
                request.getSession().setAttribute("message", "Client modifié avec succès");
                request.getSession().setAttribute("clientAchat", clientAchat);
                response.sendRedirect(request.getContextPath() + "/listClient");
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
        return "Servlet pour la modification d'un client";
    }
}