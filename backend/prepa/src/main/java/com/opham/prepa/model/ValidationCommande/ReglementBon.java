package com.opham.prepa.model.ValidationCommande;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReglementBon implements Serializable {
    private String code_rgbon;
    private String nom_client_rgbon;
    private Date date_rgbon;
    private String mode_rgbon;
    private String ref_rgchqbon;
    private String refban_rgbon;
    private String facture_rgbon;
    private BigDecimal montant_rgbon;
}
