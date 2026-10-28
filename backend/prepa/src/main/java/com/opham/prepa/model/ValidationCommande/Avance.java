package com.opham.prepa.model.ValidationCommande;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Avance  implements Serializable{
    private String code;
    private Date date;
    private String mode;
    private String reference;
    private Date echeance;
    private BigDecimal montant;
    private BigDecimal total_facture;
    private BigDecimal reste;
    private String commentaire;
}
