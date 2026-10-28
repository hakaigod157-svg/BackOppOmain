package com.opham.prepa.model.ValidationCommande;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CommandeEncours implements Serializable{
    private String code;
    private Date date_commande;
    private Timestamp validation_adv;
    private String valide_par;
    private String etape;
    private BigDecimal montant_code;
}
