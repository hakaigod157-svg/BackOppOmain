package com.opham.prepa.model.Transfert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuiviTDDetail implements Serializable {
    private String code;
    private Date date;
    private int qte;
    private String users;
    private Timestamp deb_picking;
    private Timestamp fin_picking;
    private String etat_picking;
    private String user_picking;
    private BigDecimal duree;
    private String dep_org;
    private String dep_dest;
    private String empl_org;
    private String libelle;
    private String empl_dest;
    private String commentaire;
    private String motif;
    private Timestamp date_entree;
    private BigDecimal duree_atte;



}
