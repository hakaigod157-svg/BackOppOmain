package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class attrib_axe implements Serializable{
    private int seq;
    private String code_client;
    private String nom_client;
    private String nom;
    private String adresse_1;
    private String adresse_2;
    private String ville;
    private String axe;
    private String type;
    private String mod_liv;
    private int btn;
    private String code_postal;
    private String clsa;
    private Timestamp date_modif_modeliv;
    private String user_modif_modelive;
    private Timestamp date_modif_axe;
    private String user_modif_axe;

}
