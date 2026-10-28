package com.opham.prepa.model.EnPreparation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnCoursPrepBP implements Serializable {
    private String codeBP;
    private String code;
    private Date date;
    private String depot;
    private String codeClient;
    private String nomClient;
    private String etat_BP;
    private int qteTotal;
    private int nbLigne;
    private int depuis;
    private String detail;
    private String empl;
    private int sortieMag;
    private String situation_Mag;
    private String codeBP_traite;
    private String codeCC_traite;
    private int urgent;
    private int aprendre;
    private int prioritaire;
    private int garde;
    private int ao;
    private String valeur;
    private int province;
    private String depuis_traite;



}
