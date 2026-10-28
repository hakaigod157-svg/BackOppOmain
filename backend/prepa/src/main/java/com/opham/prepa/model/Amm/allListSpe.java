package com.opham.prepa.model.Amm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class allListSpe implements Serializable{
    private String type_dossier_spe;
    private String code_dossier_spe;
    private String dossier_lib_spe;
    private String classification_spe;
}
