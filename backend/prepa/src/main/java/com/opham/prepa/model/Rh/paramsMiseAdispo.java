package com.opham.prepa.model.Rh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class paramsMiseAdispo implements Serializable {
    private String dep;
    private String nom_dep;
    private String code_poste;
    private String nom_poste;
    private int ascoma_assurance;
    private int casier_casier;
    private int blouse_epi;
    private int casque_epi;
    private int chaussure_epi;
    private int combinaison_epi;
    private int doudoune_epi;
    private int gant_epi;
    private int gilet_epi;
    private int impermeable_epi;
    private int laptop_mat_info;
    private int pc_bureau_mat_info;
    private int tablette_mat_info;
    private int carte_carburant_roulant;
    private int moto_roulant;
    private int vehicule_roulant;
    private int appel_telephonique;
    private int data_telephonique;
    private int poste_telephonique;

}
