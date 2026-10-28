package com.opham.prepa.model.Rh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.bcel.generic.DADD;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recrutement implements Serializable {
    private String recru_code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recru_date;
    private String recru_demande;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date demande_date;
    private String recru_motif;
    private String recru_contrat;
    private String recru_departement;
    private String recru_poste;
    private Integer recru_effectif;
    private String etat;



}
