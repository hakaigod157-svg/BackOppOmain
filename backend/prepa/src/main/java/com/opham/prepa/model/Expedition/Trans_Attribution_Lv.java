package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Trans_Attribution_Lv implements Serializable{
    private String trans_code;
    private String trans_transport;
    private String trans_num_fact;
    private Date trans_date_fact;
    private BigDecimal trans_montant;
    private int nb_lv;
    private int nb_cmd;
    private String nb_colis;
    private int trans_valide;

}
