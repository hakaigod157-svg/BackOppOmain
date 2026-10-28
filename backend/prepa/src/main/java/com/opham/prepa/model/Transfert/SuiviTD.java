package com.opham.prepa.model.Transfert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuiviTD {
    private String code;
    private Date date;
    private int qte;
    private String users;
    private Timestamp deb_picking;
    private Timestamp fin_picking;
    private String etat_picking;
    private String user_picking;
    private int nb_ligne;
    private BigDecimal duree;
    private String dep_org;
    private String dep_dest;
    private BigDecimal duree_atte;
    private Timestamp date_entree;
}
