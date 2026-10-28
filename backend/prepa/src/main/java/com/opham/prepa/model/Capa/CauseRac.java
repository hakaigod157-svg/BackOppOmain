package com.opham.prepa.model.Capa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CauseRac implements Serializable {

    private int actionSeq;
    private String actionNcpscode;
    private int actionValide;
    private String actionResponsable;
    private String libelleType;
    private Date actionDateprev;
    private Date actionDateeffect;
    private int actionMiseappli;
    private int actionCausesseq;
    private String actionDescription;
    private String actionValidationLibelle;
    private Date actionDateanaleffic;
    private Date actionDaterelleeffic;
    private String actionCommentaire;
    //private int actionAnalyse;
}
