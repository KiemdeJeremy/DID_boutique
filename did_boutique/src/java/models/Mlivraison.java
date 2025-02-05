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
public class Mlivraison {
    
    private Long idLivraison;
    private Date dateLivraison;
    private String libelle;
    private Double montantLivraison;

    public Mlivraison() {
    }

    public Mlivraison(Date dateLivraison, String libelle, Double montantLivraison) {
        this.dateLivraison = dateLivraison;
        this.libelle = libelle;
        this.montantLivraison = montantLivraison;
    }

    public Long getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(Long idLivraison) {
        this.idLivraison = idLivraison;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getMontantLivraison() {
        return montantLivraison;
    }

    public void setMontantLivraison(Double montantLivraison) {
        this.montantLivraison = montantLivraison;
    }
    
    
}
