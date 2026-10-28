package com.opham.prepa.model.Capa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ktable implements Serializable {
    private int seqk;
    private String nomtable;
    private int mois;
    private int annee;
    private int valeur;
}
