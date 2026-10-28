package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListAtteLiv {
    private String cmd;
    private String code_client;
    private String nom_client;
    private Date date_facture;
    private int nbColis;
    private Date date_cmd;
    private String adrs;
    private String axe;
    private String modeLiv;
    private int passBal;
    private int bonALiv;
    private boolean blockPaye;




}
