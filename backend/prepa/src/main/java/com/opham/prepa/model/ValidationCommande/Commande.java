package com.opham.prepa.model.ValidationCommande;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Commande implements Serializable{
   private String code_article_cmd;
   private String libelle_cmd;
   private int qte_cmd;
   private BigDecimal ttc_cmd;
}
