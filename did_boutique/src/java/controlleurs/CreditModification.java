/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Mcredit;
import requêtes.Rcredit;

/**
 *
 * @author USER
 */
@WebServlet(name = "CreditModification", urlPatterns = {"/creditModification"})
public class CreditModification extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Rcredit rcredit;

    @Override
    public void init() throws ServletException {
        rcredit = new Rcredit();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Récupération de l'ID du crédit à modifier
            Long idCredit = Long.parseLong(request.getParameter("idCredit"));
            
            // Récupération des paramètres du formulaire de modification
            String dateCreditStr = request.getParameter("dateCredit");
            String montantCreditStr = request.getParameter("montantCredit");
            String dateReglementStr = request.getParameter("dateReglement");
            String statut = request.getParameter("statut");
            Long idAchat = Long.parseLong(request.getParameter("idAchat"));

            // Formatage des dates
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCredit = dateFormat.parse(dateCreditStr);
            Double montantCredit = Double.parseDouble(montantCreditStr);
            Date dateReglement = dateFormat.parse(dateReglementStr);

            // Création de l'objet crédit avec les nouvelles valeurs
            Mcredit credit = new Mcredit(new java.sql.Date(dateCredit.getTime()), 
                                         montantCredit, 
                                         new java.sql.Date(dateReglement.getTime()), 
                                         statut, 
                                         idAchat);
            credit.setIdCredit(idCredit);

            // Mise à jour du crédit dans la base de données
            rcredit.updateCredit(credit);
            request.getSession().setAttribute("message", "Crédit modifié avec succès");
            response.sendRedirect(request.getContextPath() + "/listCredit");
        } catch (ParseException e) {
            response.sendRedirect(request.getContextPath() + "/erreur.jsp");
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
        return "Servlet pour la modification d'un crédit";
    }
}