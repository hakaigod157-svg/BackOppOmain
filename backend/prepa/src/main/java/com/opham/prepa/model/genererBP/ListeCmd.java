package com.opham.prepa.model.genererBP;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListeCmd implements Serializable {
    private String article;
    private String lettre;
    private String designation;
    private String lienCode;
    private int lienNum;
    private int Qte;
    private int unitfact;
    private java.math.BigDecimal prix_ht;
    private String modeliv;
    private String lignelibre;
    private String typeVente;
    private int reglement;
    private int echeancesp;
    private int abs_qte;
    private int factman;
    private int offert;
    private int artype;
    private String devise;
    private java.math.BigDecimal coursdev;
    private java.math.BigDecimal prixht_dev;
    private java.math.BigDecimal totht_dev;
    private java.math.BigDecimal rem1;
    private java.math.BigDecimal rem2;
    private java.math.BigDecimal rem3;
    private java.math.BigDecimal totPrix_HT;
    private String emplacement;
    private String attachement;
    private String lot;
    private String arreffour;
    private String cclmarche;
    private Date ccldate;
    private java.math.BigDecimal cclcolis;
    private int arqtecolis;
    private java.math.BigDecimal cclpaht;
    private String  NLOTDATEPER;
    private int BPLNUM;
    private int BPLORDRE;
    private int BPLSEQ;
    private int BPLTYPE;
    private int BPLDOTATION;
    private int BPLSTADE;
    private String depot;
    private String BPLENT;
    private String BPLBELIENCODE;
    private int BPLBELIENNUM;
    private String BPLCODE;
    private String BPLCL;
    private int ARPOIDS;
    private String STDEPOT,STNUMARM1,STNUMARM2;
    private int ARNUMEROTE;
    private int ARCOMP;
    private boolean etat;
    private int seqLib;
    private String comment_mag;
    private String cclcolisage;
    private int cclnbcolis;
    private int cclpack;
    private String rayon;
    private int cclpromo;
    private String myID;
    private String ip_web;

}
