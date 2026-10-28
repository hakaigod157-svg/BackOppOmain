package com.opham.prepa.model.Maintenance;

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
public class DemandeMaint implements Serializable {
    private String DEMANDE_CODE;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DEMANDE_DATE;
    private String DEMANDE_VOITURE;
    private String DEMANDE_MARQUE;
    private String DEMANDE_TYPE;
    private String DEMANDE_AUTEUR;
    private String DEMANDE_DESCR;
    private int DEMANDE_ETAT;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp DEMANDE_DATE_CRE;
    private int DEMANDE_USER_CRE;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp DEMANDE_DATE_MODIF;
    private int DEMANDE_USER_MODIF;






}
