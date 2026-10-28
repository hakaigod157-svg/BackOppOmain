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

public class paramsFormation implements Serializable{
    private String dep;
    private String nom_dep;
    private String code_poste;
    private String nom_poste;
    private int formation_pharm_m1;
    private int formation_pharm_m2;
    private int formation_pharm_m3;
    private int formation_pharm_m4;
    private int formation_pharm_m5;
    private int formation_pharm_m6;
    private int formation_pharm_m7;
}
