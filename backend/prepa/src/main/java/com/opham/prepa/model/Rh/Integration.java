package com.opham.prepa.model.Rh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Integration implements Serializable{
    private String empl_matricule;
    private String empl_nom;
    private String empl_prenom;
    private Date empl_date_naiss;
    private String empl_code_cv;
    private String empl_dep;
    private String lib_dep;
    private String empl_poste;
    private String poste_libelle;
    private Date empl_prise_fonction;
    private String empl_contrat;
    private Date empl_date_fin_essai1;
    private Date empl_date_fin_essai2;
    private String integr_code;
}
