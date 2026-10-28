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

public class DetailMatDispo implements Serializable {
    private int mise_dispo_matr;
    private String mise_dispo_cat;
    private String mise_dispo_materiel;
    private Date mise_dispo_date_prevue;
    private Date mise_dispo_date_effect;
}
