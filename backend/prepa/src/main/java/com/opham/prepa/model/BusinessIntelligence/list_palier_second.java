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

public class list_palier_second implements Serializable{
    private String palier_second;
    private BigDecimal taux_second;
    private BigDecimal milieu_second;
    private BigDecimal prime_second;
    private Double ecart_second;
}
