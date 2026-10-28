package com.opham.prepa.model.Apreparer;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Commande implements Serializable {
    //cListeCde_CCCODE,cListeCde_DateCOM,cListeCde_CL,cListeCde_CCNOM,cListeCde_CCCOMMENT,cListeCde_Seq,cListeCde_DateEx,cListeCde_Resume,CC2URGENT,
    // CC2APRENDRE,ZPNB1,cMin,cProvince,CC2GARDE,cNbLigne,CC2AO,cDATE_prevueLiv,CC2SPEC,c_SORTIE_MAGASIN_PREVUE,c_MAG_RESTANT,c_SITUATION_MAG,c_SORTIE_EXPEDITION,c_EXP_RESTANT,c_SITUATION_EXP,c_CC2AXE,cNB_BP,CLVALEUR}

    private String code;
    private Date date_com;
    private String code_client;
    private String nom_client;
    private String commentaire;
    private Long seq_cc;
    private Date date_valide_adv;
    private String resume;
    private int urgent;
    private int aprendre;
    private int prioritaire;
    private int duree_atte_prep;
    private int province;
    private int garde;
    private int nbLigne;
    private int ao;
    private Timestamp date_prevue_liv;
    private int spec_cc;
    private Date date_sortie_magasin;
    private int duree_mag_restant;
    private String situation_mag;
    private Date date_sortie_exp;
    private int duree_exp_restant;
    private String situation_exp;
    private String axe;
    private int nb_BP;
    private String valeur;
    private String code_traite;


}
