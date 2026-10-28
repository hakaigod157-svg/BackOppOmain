package com.opham.prepa.model.Transfert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfert implements Serializable {
    private String article;
    private String libelle;
    private String labo;
    private String depot_org;
    private String empl_org;
    private String depot_dest;
    private String empl_dest;
    private String lot;
    private Date datePer;
    private String lettre;
    private int qte;
    private String numarm1;
    private String numarm2;
    private String devise;
    private Double padev;
    private Double paht;
    private Double frais;
    private int ug;
    private int qteNonEt;
    private int qteEnCoursEt;
    private int qteEt;
    private String myId;
    private String ip_web;



}
