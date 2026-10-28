package com.opham.prepa.model.Amm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class reclamationDetail implements Serializable{
    private String amm_recl_recl;
    private String lib_recl;
    private Date amm_recl_date;
    private String nb_reclam;
    private int annul_reclam;
    private Date amm_date_nbre_reclam;

}
