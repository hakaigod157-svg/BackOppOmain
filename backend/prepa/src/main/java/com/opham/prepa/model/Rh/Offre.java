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
public class Offre implements Serializable {

    private String offre_code;
    private Integer offre_type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date offre_date;
    private String offre_entite;
    private String offre_code_recr;
    private byte[] offre_piece;

}
