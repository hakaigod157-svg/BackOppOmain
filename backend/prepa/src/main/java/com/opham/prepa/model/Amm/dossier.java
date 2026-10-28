package com.opham.prepa.model.Amm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class dossier implements Serializable{
    private int seq;
    private String amm_ds_code;
    private String ligne_code_article;
    private String ligne_libelle_article;
    private String amm_type;
    private String amm_classification;
    private String amm_decision;
    private String fab_libelle;
    private String ligne_titulaire;
    private String amm_respo;
    private String amm_etape;
    private int bouton_decision;
    private int amm_depuis;
    private Date date_depuis;
    private String estimation;
    private int bouton_reclam;
    private Date datecre;
    //private byte[] ligne_image;
    private int ligne_image;
    private int bouton_non_depos;

}
