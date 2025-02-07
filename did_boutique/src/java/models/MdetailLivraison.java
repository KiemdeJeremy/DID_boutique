/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author USER
 */
public class MdetailLivraison {
    
    private Long idDetailLivraison;
    private Double quantite;
    private Double prixUnitaire;
    private Double coutTotal;
    private Long idLivraison;
    private Long idProduit;

    public MdetailLivraison() {
    }

    public MdetailLivraison(Double quantite, Double prixUnitaire, Double coutTotal, Long idLivraison, Long idProduit) {
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.coutTotal = coutTotal;
        this.idLivraison = idLivraison;
        this.idProduit = idProduit;
    }

    public Long getIdDetailLivraison() {
        return idDetailLivraison;
    }

    public void setIdDetailLivraison(Long idDetailLivraison) {
        this.idDetailLivraison = idDetailLivraison;
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

    public Long getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(Long idLivraison) {
        this.idLivraison = idLivraison;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }
    
    
    
}
