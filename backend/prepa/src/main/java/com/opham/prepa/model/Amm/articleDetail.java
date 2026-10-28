package com.opham.prepa.model.Amm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class articleDetail implements Serializable{
    private int seq;
    private String code_article;
    private String libelle_article;
    private String titulaire;
    private String fabricant;
    private String num_amm;
    private Date date_amm;
    private byte[] ligne_image;
}
