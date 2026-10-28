package com.opham.prepa.model.Apreparer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class pp_list_det implements Serializable{
    private String ppL_article;
    private String ppL_libelle;
    private int ppL_qte;
    private String ppL_lot;
    private String ppL_depot;
    private String ppL_empl;
    private int ppL_offert;
    private String ppL_code;
}
