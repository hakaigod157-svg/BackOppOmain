package com.opham.prepa.model.Amm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Midac implements Serializable  {
    private String code_prod;
    private String nom_com;
    private String code_cip;
    private String dci;
    private String marque;
    private String presentation;
    private String fabriquant;
    private String titulaire;
    private String code_hs;
    private String type_colis;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_amm;
    private String num_amm;
    private String status;
    private int vdate;
    private int vmois;
    private String code_opham;
    private String arlib;
    private String amm_fiche_article;
    private String amm_interprete;
    private Date date_amm_fiche_article;
    private int lm_mois;
    private int lm_annee;
    private int amm_gere_opham;
}