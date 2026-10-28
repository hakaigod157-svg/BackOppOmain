package com.opham.prepa.model.Catalogue;

import lombok.*;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class catalogue implements Serializable{
    private String code_client;
    private String nom_client;
    private String categorie;
    private String tarif;
    private String mois;
    private String annee;
    private String cc;
}
