package com.opham.prepa.model.Rh;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DemandePers implements Serializable {
    private String demande_code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date demande_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date demande_prise_fonction;
    private String demande_departement;
    private String demande_motif;
    private String demande_contrat;
    private String demande_poste;
    private Integer demande_effectif;
    private Integer demande_etat;


    private byte[] demande_fiche;


    @JsonProperty("demande_fiche_base64")
    private String demande_fiche_base64;

    @JsonProperty("ficheExists")
    private boolean ficheExists;


}
