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

public class det_palier_second implements Serializable {
    private String mois_second;
    private Double qualitatif_second;
    private Double quantitatif_second;
    private Double total_second;
    private Double prime_max_second;
}
