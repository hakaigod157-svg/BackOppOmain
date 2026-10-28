package com.opham.prepa.model.Apreparer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class pp_list implements Serializable {
    private String ppL_code;
    private String code_client;
    private String nom_client;
    private Date fp_date;
    private String ppL_facture;
    private String ppL_FpCode;
    private int pp_imprime;
}
