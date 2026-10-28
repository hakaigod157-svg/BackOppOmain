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
public class CV implements Serializable {
    private String cv_code;
    private String cv_nom;
    private String cv_prenom;
    private String cv_contact1;
    private String cv_contact_2;
    private String cv_email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cv_date;

    private byte[] cv_cv;
    private byte[] cv_motivation;
    private String cv_recru_code;

    private Integer cv_ligne_selectionne;
    private Integer cv_ligne_test_entretien;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp cv_ligne_date_test;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp cv_ligne_date_entretien;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cv_ligne_date_select;

    private Integer cv_ligne_validation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cv_ligne_validation_date;
    private Date cv_ligne_date_ajout;
    private Date cv_ligne_date_proposition;




}
