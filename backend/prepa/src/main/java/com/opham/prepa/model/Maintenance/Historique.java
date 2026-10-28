package com.opham.prepa.model.Maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Historique {
    private String demandeCode;
    private Timestamp date;
    private int demandeEtat;
    private int userId;
    private String userNom;

}
