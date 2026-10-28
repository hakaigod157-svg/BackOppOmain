package com.opham.prepa.model.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transporteur implements Serializable{
    private String xNum;
    private String xType;
    private BigDecimal xCode;
    private String xCategorie;
    private String xType_service;
    private String xlibelle;
}
