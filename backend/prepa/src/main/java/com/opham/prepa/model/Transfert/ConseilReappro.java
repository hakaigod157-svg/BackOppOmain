package com.opham.prepa.model.Transfert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConseilReappro implements Serializable  {
    private String article;
    private String libelle;
    private int colisage;
    private int moyenne_vte_j;
    private double couverture_det;
    private double couverture_besion;
    private int stick_gros;
    private int qte_appro_exacte;
    private int qte_appro;
    private String urg;
    private int stock_det;
}
