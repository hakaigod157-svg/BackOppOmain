package com.opham.prepa.model.AppelEntrant;

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

public class AppelSortant implements Serializable {

    private String seq;
    private Timestamp calldate_sortant;
    private Date date;
    private LocalTime heure;
    private String src_sortant;
    private String numero_sortant;
    private String numero_indicatif_sortant;
    private String client_sortant;
    private String dcontext_sortant;
    private String dst_sortant;
    private String dstchannel_sortant;
    private String poste_interne_sortant;
    private int duration_sortant;
    private int billsec_sortant;
    private String disposition_sortant;
    private String uniqueid_sortant;
    private int etat_sortant;
    //private String statut;
    //private Long depuis;

}
