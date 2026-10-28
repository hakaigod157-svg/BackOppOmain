package com.opham.prepa.model.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private String article;
    private String libelle;
    private String labo;
    private String nomLabo;
    private String famille;
    private String nomFamille;
    private String categorie;
    private String nomCategorie;
    private String gamme;
    private String nomGamme;
    private String distributeur;
    private String nomDistributeur;
    private String fournisPrincipal;
    private String nomFournisPr;
    private String fabricant;
    private String nomFabricant;
    private String plateforme;
    private String nomPlateforme;
    private String sousCategorie;
    private String nomSousCat;
    private String dci;
    private String nomCom;
    private int colisage;
    private String type;
    private String conservation;
    private String statut;

}
