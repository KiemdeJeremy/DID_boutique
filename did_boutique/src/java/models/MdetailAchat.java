/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class MdetailAchat {
    
    private Long idDetailAchat;
    private Double quantite;
    private Double prixUnitaire;
    private Double coutTotal;
    private Long idAchat;
    private Long idProduit;

    public MdetailAchat() {
    }

    public MdetailAchat(Double quantite, Double prixUnitaire, Double coutTotal, Long idAchat, Long idProduit) {
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.coutTotal = coutTotal;
        this.idAchat = idAchat;
        this.idProduit = idProduit;
    }

    public Long getIdDetailAchat() {
        return idDetailAchat;
    }

    public void setIdDetailAchat(Long idDetailAchat) {
        this.idDetailAchat = idDetailAchat;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(Double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public Long getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(Long idAchat) {
        this.idAchat = idAchat;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

}
