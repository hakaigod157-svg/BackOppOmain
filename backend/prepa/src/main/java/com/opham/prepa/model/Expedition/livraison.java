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

public class livraison implements Serializable{
    private String vague;
    private String type;
    private int centre;
    private int centre_colis;
    private int est;
    private int est_colis;
    private int itaosy;
    private int itaosy_colis;
    private int ivato;
    private int ivato_colis;
    private int nord;
    private int nord_colis;
    private int sixsept;
    private int six_sept_colis;
    private int sud;
    private int sud_colis;
    private int sud_est;
    private int sud_est_colis;
    private int total;
    private int total_colis;

}
