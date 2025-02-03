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
public class Mcredit {

    private Long idCredit;
    private Date dateCredit;
    private Double montantCredit;
    private Date dateReglement;
    private String statut;
    private Long idAchat;

    public Mcredit() {
        
    }

    public Mcredit(Date dateCredit, Double montantCredit, Date dateReglement, String statut, Long idAchat) {
        this.dateCredit = dateCredit;
        this.montantCredit = montantCredit;
        this.dateReglement = dateReglement;
        this.statut = statut;
        this.idAchat = idAchat;
    }

    public Long getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(Long idCredit) {
        this.idCredit = idCredit;
    }

    public Date getDateCredit() {
        return dateCredit;
    }

    public void setDateCredit(Date dateCredit) {
        this.dateCredit = dateCredit;
    }

    public Double getMontantCredit() {
        return montantCredit;
    }

    public void setMontantCredit(Double montantCredit) {
        this.montantCredit = montantCredit;
    }

    public Date getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(Date dateReglement) {
        this.dateReglement = dateReglement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Long getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(Long idAchat) {
        this.idAchat = idAchat;
    }
    
    
}
