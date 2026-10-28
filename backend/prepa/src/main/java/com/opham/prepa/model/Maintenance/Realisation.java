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
public class Realisation implements Serializable {
    private String MAINT_CODE_REAL;
    private String MAINT_INTERV_REAL;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date MAINT_DATE_REAL;
    private String MAINT_FNR_REAL;
    private Integer MAINT_PRIX_REAL;
    private String MAINT_ACTION_REAL;
    private String MAINT_MRAQUE_MARQUE;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date MAINT_DATE_PREVU;

}
