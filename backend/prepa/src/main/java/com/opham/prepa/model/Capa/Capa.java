package com.opham.prepa.model.Capa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Capa implements Serializable {
    private String code;
    private Date date;
    private String modeDetect;
    private String emetteur;

    private String departement;
    private int etat;
    private int valide;
    private String libellemodedetect;
    private String procdetect;
    private String libprocdetect;
    private int causevalid;
    private int causeatte;
    private int attevalid;
    private int attemiseappli;
    private int applique;

    private String libelleDep;
    private String libelleType;
    private String description;
    private String type;
    private int nbCauseRacine;
    private int nbAction;

    //code de mis a jour :
    private int ncpsSeq;
    private String ncpsCode;
    private Date ncpsDate;
    private String ncpsDescription;
    private String ncpsDepartement;
    private String ncpsType;
    private String ncpsEmetteur;
    private String ncpsModeDetect;
    private String ncpsProcessusDetect;
    private int ncpsEtat;
    private int ncpsValide;

}
