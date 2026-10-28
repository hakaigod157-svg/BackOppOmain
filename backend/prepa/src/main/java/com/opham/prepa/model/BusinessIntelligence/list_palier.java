package com.opham.prepa.model.BusinessIntelligence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class list_palier implements Serializable{
    private String palier;
    private BigDecimal taux;
    private BigDecimal milieu;
    private BigDecimal prime;
    private Double ecart;

}
