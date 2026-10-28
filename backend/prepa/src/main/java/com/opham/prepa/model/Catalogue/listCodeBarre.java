package com.opham.prepa.model.Catalogue;

import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class listCodeBarre implements Serializable{
    private String labo;
    private String article;
    private String pays;
    private String code_labo;
    private String code_pays;
    private String code_article;
    private int cle_ctrl;
    private String code_ean13;
    private Date date_cre;
    private int user_cre;

}
