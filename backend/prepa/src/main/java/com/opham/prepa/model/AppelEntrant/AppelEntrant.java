package com.opham.prepa.model.AppelEntrant;

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

public class AppelEntrant implements Serializable {

    private String seq;
    private Timestamp calldate;
    private Date date;
    private LocalTime heure;
    private String src;
    private String numero;
    private String numero_indicatif;
    private String client;
    private String dst;
    private String poste_interne;
    private int duration;
    private int billsec;
    private String disposition;
    private int etat;
    private String statut;
    private Long depuis;
    private Timestamp daterappel_1;
    private Timestamp daterappel_2;
    private String poste_rap1;
    private String poste_rap2;
    private int visible_btn;
    private int resolu;
    private int couleur;
    private int rappel;

  //  private Timestamp datecre;

}
