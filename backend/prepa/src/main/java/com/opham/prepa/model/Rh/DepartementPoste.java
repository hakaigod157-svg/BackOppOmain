package com.opham.prepa.model.Rh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DepartementPoste implements Serializable {

    private String code_departement;
    private String lib_departement;
    private String code_poste;
    private String poste_libelle;

}
