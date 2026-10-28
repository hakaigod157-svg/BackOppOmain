package com.opham.prepa.model.ValidationCommande;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reglement implements Serializable {
    private String code_rg;
    private String nom_client_rg;
    private Date date_rg;
    private BigDecimal montant_rg;
    private String mode_rg;
    private Date echeance_rg;
    private String reference_rg;
    private String commentaire_rg;

}
