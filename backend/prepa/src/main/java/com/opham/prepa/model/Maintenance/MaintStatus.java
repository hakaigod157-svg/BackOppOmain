package com.opham.prepa.model.Maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintStatus implements Serializable {
    private String MAINT_CODE_LIGNE;
    private String MAINT_ETAPE_LIGNE;
    private Date MAINT_ETAPE_PREVU;
    private Date MAIT_ETAPE_REEL ;
    private Integer MAINT_ETAPE_ETAT;
}
