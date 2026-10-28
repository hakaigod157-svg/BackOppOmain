package com.opham.prepa.model.Maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtapeLigne implements Serializable {
    private String MAINT_CODE_LIGNE;
    private String MAINT_ETAPE_LIGNE;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date MAINT_ETAPE_PREVU;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date MAINT_ETAPE_REEL;
    private Integer MAINT_ETAPE_ETAT;
}
