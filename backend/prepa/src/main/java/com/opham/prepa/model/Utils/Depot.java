package com.opham.prepa.model.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Depot implements Serializable {
    private String code;
    private String nom;
    private boolean central;
    private boolean colis;
    private boolean vente;
    private boolean transfert;
    private boolean prepa;

}
