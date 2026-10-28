package com.opham.prepa.model.ValidationCommande;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CommandeAlivrer implements Serializable{
    private String facture;
    private String codeclient;
    private String nomclient;
    private Timestamp date;
    private BigDecimal ttc;
    private int depuis;
    private String commande;
    private String commentaires;
    private String etape;
    private BigDecimal montant_payer;

}
