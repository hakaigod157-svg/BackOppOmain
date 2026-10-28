package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class indicateur_liv_detail implements Serializable {
    private String code;
    private String client;
    private String nom_client;
    private Date date_commande;
    private Timestamp date_validation_adv;
    private String cc2_vague_liv;
    private Timestamp dateliv_prevu;
    private String cc2axe;
    private String stade;
    private Timestamp livraison_ind;
    private Timestamp sortie_securite;
    private Timestamp livraison_prevu_marge;
    private int cc2_pass_bal;
    private String qualite_livraison;
}
