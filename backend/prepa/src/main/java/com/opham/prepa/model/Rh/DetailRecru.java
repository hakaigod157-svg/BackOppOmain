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
public class DetailRecru implements Serializable {

    private String ligne_etape_code;
    private String ligne_etape_etape;
    private String etape_libelle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ligne_etape_date_prevision;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ligne_etape_date;

}
