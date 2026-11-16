package com.opham.prepa.model.Maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeadLine implements Serializable {
    private String MAINT_CODE;
    private String ETAPE_LIGNE;
    private Timestamp DATE_DEBUT;
    private Timestamp DATE_FIN;
    private Integer JOURS_RESTANTS;
}
