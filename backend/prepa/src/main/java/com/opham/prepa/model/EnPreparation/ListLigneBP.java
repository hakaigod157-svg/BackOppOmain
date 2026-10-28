package com.opham.prepa.model.EnPreparation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListLigneBP implements Serializable {
    private String article;
    private String libelle;
    private String lot;
    private Date datePer;
    private String empl;
    private int qte;


}
