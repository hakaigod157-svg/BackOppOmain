package com.opham.prepa.model.ValidationCommande;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestApresValide implements Serializable {
    private int mode;
    private String traiteEncours;
    private int stade;
    private String numero;
    private String notification;
    private String nom_com;
}
