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

public class det_palier_third implements Serializable {
    private String mois_third;
    private Double qualitatif_third;
    private Double quantitatif_third;
    private Double total_third;
    private Double prime_max_third;
}
