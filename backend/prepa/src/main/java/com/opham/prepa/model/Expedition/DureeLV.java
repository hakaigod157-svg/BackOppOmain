package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DureeLV implements Serializable {
    private String nomClt;
    private BigDecimal seqliv;
    private Timestamp arriveClt;
    private Timestamp departClt;
    private String codeClt;
}
