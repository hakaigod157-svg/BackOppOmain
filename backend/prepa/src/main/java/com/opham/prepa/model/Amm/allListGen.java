package com.opham.prepa.model.Amm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class allListGen implements Serializable{
    private String type_dossier_gen;
    private String code_dossier_gen;
    private String dossier_lib_gen;
    private String classification_gen;
}
