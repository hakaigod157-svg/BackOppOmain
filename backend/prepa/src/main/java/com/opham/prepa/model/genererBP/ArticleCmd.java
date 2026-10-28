package com.opham.prepa.model.genererBP;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCmd implements Serializable {
    private String  code_CCL;
    private String article;
    private String libelle;
    private int colisage;
    private int qte_a_preparer;
    private int dispo;
    private String transfert;
    private boolean etat;
    private String depot;
}
