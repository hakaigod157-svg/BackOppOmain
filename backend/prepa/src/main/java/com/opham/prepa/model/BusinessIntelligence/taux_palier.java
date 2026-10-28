package com.opham.prepa.model.BusinessIntelligence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class taux_palier implements Serializable {
    private BigDecimal mat_taux;
}
