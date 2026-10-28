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

public class amm_renouveler implements Serializable {
    private String code_article;
    private String libelle;
    private String titulaire;
    private String amm_interprete;
    private String amm_code;
    private Date amm_date;
    private String expire;
}
