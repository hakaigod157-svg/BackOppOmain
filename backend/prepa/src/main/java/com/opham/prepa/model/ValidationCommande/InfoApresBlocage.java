package com.opham.prepa.model.ValidationCommande;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoApresBlocage implements Serializable {
    private int stat;
    private String numero;
    private String notification;
    private String nom_com;
}
