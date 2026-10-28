package com.opham.prepa.model.Amm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class Abrogation implements Serializable{
    private String abrog_code;
    private String abrog_libelle;
    private String abrog_titulaire;
    private String abrog_amm_interprete;
    private String abroge_amm;
    private Date abrog_ammdate;
    private int abrog_expire;
    private Date abrog_expirationdate;
    private int abrog_bouton;


}
