package com.opham.prepa.model.Rh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DetailIntegration implements Serializable {
    private String ligne_etape_code;
    private String ligne_etape_etape;
    private String etape_libelle;
    private Date ligne_etape_date_prevision;
    private Date ligne_etape_date;
}
