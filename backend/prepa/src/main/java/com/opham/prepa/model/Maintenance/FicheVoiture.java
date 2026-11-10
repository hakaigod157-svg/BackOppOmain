package com.opham.prepa.model.Maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheVoiture implements Serializable {
    private String IMMATRICULATION;
    private String MARQUE;
    private Integer ANNEE;
    private String MODELE;
    private Integer KILOMETRE;
    private Integer ETAT;
}
