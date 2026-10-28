package com.opham.prepa.model.Transfert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemeStock implements Serializable {

    private String article;
    private String libelle;
    private String depot;
    private String lot;
    private String lettre;
    private String empl;
    private int qteStock;
    private int qteTransfert;
    private int rest;
}
