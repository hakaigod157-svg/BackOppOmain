package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class indicateur_chauffeur implements Serializable {
    private String xchauff;
    private String xnum_voit;
    private String xtype;
    private int xdispo;
    private String tourne_1;
    private int tourne_1_duree;
    private String tourne_2;
    private int tourne_2_duree;
    private String tourne_3;
    private int tourne_3_duree;
}
