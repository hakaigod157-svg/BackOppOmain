package com.opham.prepa.model.Apreparer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommande implements Serializable {
    private String code_article;
    private String libelle;
    private int qte_cmd;
    private int qte_reste;
    private int qte_expedie;
    private String coms;
}
