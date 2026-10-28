package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoitureCh {
    private String voit;
    private String chauf;
    private int dispo;
    private String disponibilite;
    private String code;
    private String type_liv;
    private String type_service;
    private String seq;

}
