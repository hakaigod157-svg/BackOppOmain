package com.opham.prepa.model.genererBP;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepotCmd implements Serializable {
    private String code_CC;
    private String depot;
    private int nb_article;
}
