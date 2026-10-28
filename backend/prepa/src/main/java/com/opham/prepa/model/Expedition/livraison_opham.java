package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class livraison_opham implements Serializable {
    private String vague;
    private Time heure;
    private int sans_axe_cde;
    private int sans_axe_colis;
    private int sans_axe_couleur;

    private int anosy_cde;
    private int anosy_colis;
    private int anosy_couleur;

    private int centre_cde;
    private int centre_colis;
    private int centre_couleur;


    private int est_meteo_cde;
    private int est_meteo_colis;
    private int est_meteo_couleur;

    private int est_rn2_cde;
    private int est_rn2_colis;
    private int est_rn2_couleur;

    private int itaosy_cde;
    private int itaosy_colis;
    private int itaosy_couleur;


    private int itaosy_rn1_cde;
    private int itaosy_rn1_colis;
    private int itaosy_rn1_couleur;


    private int ivato_cde;
    private int ivato_colis;
    private int ivato_couleur;


    private int mahitsy_rn4_cde;
    private int mahitsy_rn4_colis;
    private int mahitsy_rn4_couleur;

    private int nord_cde;
    private int nord_colis;
    private int nord_couleur;

    private int sixsept_cde;
    private int sixsept_colis;
    private int sixsept_couleur;

    private int sud_cde;
    private int sud_colis;
    private int sud_couleur;

    private int sud_est_cde;
    private int sud_est_colis;
    private int sud_est_couleur;

    private int total_cde;
    private int total_colis;
    private int total_couleur;

}
