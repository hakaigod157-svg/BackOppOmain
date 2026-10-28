package com.opham.prepa.model.AppelEntrant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MoyenneAppel implements Serializable {
    private Date date;
    private int a_rappeler;
    private String rappele;
    private String rappele_a_temp;
    private int moyenne;
}
