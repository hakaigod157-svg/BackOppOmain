package com.opham.prepa.model.ValidationCommande;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CommandeValider implements Serializable {
    private String code_valider;
    private String nom_client_valider;
    private Date date_commande_valider;
    private Timestamp date_validation_adv_valider;
    private String condition_client_valider;
    private String comptant_valider;
    private BigDecimal plafond_valider;
    private BigDecimal solde_client_valider;
    private BigDecimal facture_echue_valider;
    private BigDecimal cmd_encours_valider;
    private BigDecimal montant_cmd_valider;
    private BigDecimal reglement_encours_valider;
    private String commentaire_valider;
    private int cheque_valider;
    private BigDecimal depassement_valider;
    private String etape_valider;
    private String user_valid_valider;
    private int garde_valider;
    private int bon_a_livrer_valider;
    private String client_valider;
}
