package com.opham.prepa.model.Maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etape implements Serializable{
    private String CLASSIFICATION;
    private String CODE;
    private String VALEUR;
    private int ORDRE;
    private int DUREE;
    private int ETAT_ID;

}