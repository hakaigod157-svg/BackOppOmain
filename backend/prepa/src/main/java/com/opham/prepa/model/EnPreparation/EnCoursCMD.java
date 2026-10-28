package com.opham.prepa.model.EnPreparation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnCoursCMD implements Serializable {
    private String code;
    private Date date_com;
    private String nom_client;
    private String type_tana;
    private int ccseq;
    private String etape;
    private int duree_atte_prep;
    private int prepspecif;
    private int urgent;
    private int aprendre;
    private int prioritaire;
    private int province;
    private int garde;
    private int nbLigne;
    private int ao;
    private int depuis_adv;
    private int depuis_prepa;
    private Timestamp date_prevue_liv;
    private int cc_bloque_fact;
    private Timestamp sortie_magasin_prevue;
    private int mag_restant;
    private String reste_22;
    private Timestamp sortie_expedition;
    private int exp_restant;
    private String situation_exp;
    private String axe;
    private String valeur;
    private String code_traite;


}
