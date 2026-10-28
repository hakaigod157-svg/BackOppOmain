package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class detail_editionLv implements Serializable {
    private String code_cc;
    private String code_client;
    private String nom_client;
    private Timestamp date_validation_adv;
    private String etape;
    private int garde;
    private Date date_commande;
    private String nombre_colis;
    private int cc2_pass_bal;
    private Timestamp dateliv_prevu;
    private String modeliv;
    private String service_cde;
    private String cc2axe;
    private String pr_type;
}
