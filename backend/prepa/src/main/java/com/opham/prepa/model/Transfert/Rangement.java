package com.opham.prepa.model.Transfert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rangement implements Serializable {
    private String article;
    private String libelle;
    private String lot;
    private Date datePer;
    private String depot_org;
    private String empl_org;
    private int colisage;
    private String depot_dest;
    private String empl_dest;
    private int qte;
    private boolean etat;
}
