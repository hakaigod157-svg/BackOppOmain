package com.opham.prepa.model.ValidationCommande;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Etape implements Serializable{
    private int nb_cmd_Avalide;
    private int nb_cmd_valide;
    private int nb_bon_livre;
    private int nb_cmd_traite;

}
