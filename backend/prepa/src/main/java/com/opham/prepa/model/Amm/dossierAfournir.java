package com.opham.prepa.model.Amm;

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

public class dossierAfournir implements Serializable{
    private String ligne_dossier_code;
    private int ligne_etat;
    private Date ligne_date;
    private String dossier_lib;
}
