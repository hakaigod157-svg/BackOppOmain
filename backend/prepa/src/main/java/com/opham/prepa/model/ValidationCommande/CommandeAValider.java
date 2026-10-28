package com.opham.prepa.model.ValidationCommande;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CommandeAValider implements Serializable{
    private Long ccseq;
    private String code;
    private Date date;
    private String code_client;
    private String nom_client;
    private String mode;
    private String condition;
    private String comptant;
    private double plafond;
    private String etape_adv;
    private double solde;
    private double facture_echue;
    private double cmd_encours;
    private double cmd;
    private double rg_encours;
    private String validation_auto;
    private String commentaire;
    private int couleur;
    private int bloque;
    private String user_bloque;
    private String adr_liv;
    private String garde;
    private String valeur_client;
    private String mode_liv;
    private BigDecimal depassement;
    private String commentaire_client;
    private String com_a_valider;
}
