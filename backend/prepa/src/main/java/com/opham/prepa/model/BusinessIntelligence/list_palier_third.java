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

public class list_palier_third implements Serializable{
    private String palier_third;
    private BigDecimal taux_third;
    private BigDecimal milieu_third;
    private BigDecimal prime_third;
    private Double ecart_third;
}
