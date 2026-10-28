package com.opham.prepa.model.genererBP;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RayonCmd implements Serializable {
    private String rayon;
    private String depot;
    private boolean etat;
    private int nb_article;
}
