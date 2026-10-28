package com.opham.prepa.model.ValidationCommande;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class ClientSolde implements Serializable{
    private String piece;
    private Date date;
    private Date echeance;
    private BigDecimal montantinit;
    private BigDecimal reste;
    private String comment1;
    private String comment2;
    private BigDecimal nbjour;
    private String ecllib;
    private int partiel;
    private int farsrv;

}
