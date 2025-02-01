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
public class Machat {
    
    private Long idAchat;
    private Date dateAchat;
    private Double montant;
    private Double sommeEncaisse;
    private Double remise;
    private Long idUtilisateur;
    private Long idClient;

    public Machat() {
    }

    
    public Machat(Date dateAchat, Double montant, Double sommeEncaisse, Double remise, Long idUtilisateur, Long idClient) {
        this.dateAchat = dateAchat;
        this.montant = montant;
        this.sommeEncaisse = sommeEncaisse;
        this.remise = remise;
        this.idUtilisateur = idUtilisateur;
        this.idClient = idClient;
    }

    public Long getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(Long idAchat) {
        this.idAchat = idAchat;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Double getSommeEncaisse() {
        return sommeEncaisse;
    }

    public void setSommeEncaisse(Double sommeEncaisse) {
        this.sommeEncaisse = sommeEncaisse;
    }

    public Double getRemise() {
        return remise;
    }

    public void setRemise(Double remise) {
        this.remise = remise;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }
    
    
}
