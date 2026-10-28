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

public class list_amm_dossier implements Serializable{
    private String amm_ds_code;
    private int amm_reclam;
    private String amm_type;
    private String amm_ref_variation;
}
