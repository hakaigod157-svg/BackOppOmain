package com.opham.prepa.model.ValidationCommande;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Traite implements Serializable {
    private String code_traite;
    private String code_client_traite;
    private String nom_client_traite;
    private Timestamp date_commande_traite;
    private Timestamp date_valide_adv_traite;
    private String etape_traite;
    private String condition_cmd_traite;
    private String condition_client_traite;

}
