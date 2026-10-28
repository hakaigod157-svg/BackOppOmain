package com.opham.prepa.model.Maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeStatut implements Serializable {
  //  private String DEMANDE_CODE;
    private String STATUT;
    private String ETAPE_EN_COURS;
    private String LISTE;
    private int DUREE = 0;
}
