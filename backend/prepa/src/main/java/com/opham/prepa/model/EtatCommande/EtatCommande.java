package com.opham.prepa.model.EtatCommande;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtatCommande {
    private String code;
    private String code_client;
    private String Nom_client;
    private String axe;
    private Date commande;
    private Timestamp valid_fact;
    private String mode;
    private Timestamp valid_adv;
    private Timestamp deb_prep;
    private Timestamp fin_prep;
    private Timestamp deb_ctrl;
    private Timestamp fin_ctrl;
    private Timestamp fin_emb;
    private Timestamp sortie_fact;
    private String commentaire;
    private String commentaire_mag;
    private Timestamp sortie_opham;
    private String livree_par;
    private String voiture_liv;
    private String retour_liv;
    private String bon_a_livree;
    private Timestamp atte_bal_exp;
    private Timestamp date_bal_adv;
    private Timestamp date_annul_Bal_adv;
}
