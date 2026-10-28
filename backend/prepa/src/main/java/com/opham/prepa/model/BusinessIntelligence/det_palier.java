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
public class det_palier implements Serializable{
    private String mois;
    private Double qualitatif;
    private Double quantitatif;
    private Double total;
    private Double prime_max;
}
