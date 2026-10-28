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

public class motifDetail implements Serializable{
    private String amm_ds_code;
    private String amm_respo;
    private String amm_type;
    private int amm_decision;
    private String amm_classification;
    private Date amm_date_decision;
    private String amm_motif_decision;
}
