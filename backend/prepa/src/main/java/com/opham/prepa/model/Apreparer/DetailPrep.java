package com.opham.prepa.model.Apreparer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailPrep implements Serializable {
    private String code_BP;
    private Timestamp datecre_BP;

    private String depot;
    private String stade;
    private String listBac;

    private String empl;

    private int nbColis;
}
