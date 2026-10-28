package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class edition_lv implements Serializable{
    private String liv_code;
    private Timestamp liv_sortie;
    private String liv_com;
    private String liv_chauf;
    private String liv_voit;
    private String saisie;
    private String liv_livreur;
    private String liv_axe;
    private int livrempli;
    private int liv_saisie;
    private int liv_seq2;
    private BigDecimal liv_seq;
    private int inc1;
    private int inc2;
    private BigDecimal inc3;
    private int cpt_depart;
    private int cpt_arrive;
    private Timestamp ret_vehicule;
    private String xCode;
    private int is_sortiesec;
    private Integer duree;
    private Timestamp sortie_sec;
    private String type;
}
