package com.opham.prepa.model.Maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtapeEnCours implements Serializable {
    private String MAINT_ETAPE_LIGNE;

}
