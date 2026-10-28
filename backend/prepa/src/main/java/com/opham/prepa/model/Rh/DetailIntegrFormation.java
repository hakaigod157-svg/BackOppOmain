package com.opham.prepa.model.Rh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DetailIntegrFormation implements Serializable{
    private String form_code;
    private String type_form_libelle;
    private Date form_dateprevue;
    private Date form_datereel;
}
