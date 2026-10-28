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
public class Maintenance implements Serializable {
    private String MAINT_CODE;
    private String MAINT_DEMANDE;
    private String MAINT_CLASSIFICATION;
    private String MAINT_TYPE;
    private String MAINT_KM;
    private int MAINT_USERCRE;
    private int MAINT_USERMODIF;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date MAINT_DATE;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date MAINT_DATECRE;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date MAINT_DATEMODIF;

}
