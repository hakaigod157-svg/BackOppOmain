package com.opham.prepa.model.BusinessIntelligence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class list_ca_annuel implements Serializable{
    private String article;
    private int annee;
    private double ca;
}
