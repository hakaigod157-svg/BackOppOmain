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

public class indicateur_livraison implements Serializable{
    private String vague_ind;
    private Time heure_ind;
    private int total_cde_ind;
    private int prepa;
    private int ctrl;
    private int facture;
    private int livraison;
    private int livre;
    private int percent_livre;
    private int livraison_ok;
    private int percent_ok;
    private int coul_livre;
    private int coul_livraison_ok;
    private int livraison_hier;
    private int percent_liv_hier;

}
