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
public class detail_livraisonDemain implements Serializable{
    private String code_cc;
    private String code_client;
    private String nom_client;
    private Timestamp date_validation_adv;
    private String etape;
    private int tana;
    private String cc2axe;
    private int a_prendre;
    private int garde;
    private Timestamp date_commande;
    private String situation;
    private Time vague_heure;
    private int vague_min;
    private String vague_axe;
    private String nombre_colis;
    private int cc2_pass_bal;
    private Timestamp dateliv_prevu;
    private String cc2_vague_liv;
    private String modeliv;
    private String vague_a_livrer;
    private Timestamp date_a_livrer;
}
