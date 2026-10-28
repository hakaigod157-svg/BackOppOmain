package com.opham.prepa.model.EnPreparation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailBP implements Serializable {
    private String codeBP;
    private String etat_BP;
    private String rayon;
    private Timestamp debut_prep_rayon;
    private Timestamp fin_prep_rayon;
    private int duree_prep_rayon;
    private String agent_prep_rayon;
    private int qte_total;
    private String agent_ctrl;
    private Timestamp debut_ctrl;
    private Timestamp fin_ctrl;
    private int duree_ctrl;
    private int nbColis;
}
