package com.opham.prepa.model.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.fop.datatypes.Numeric;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Action {
    //code d ajout actions
    private String actionNcpscode;
    private int actionValide;
    private String actionResponsable;
    private String libelleType;
    private Date actionDateprev;
    private Date actionDateeffect;
    private int actionMiseappli;
    private String actionCommentaire;
    private int actionCausesseq;
    private String actionDescription;
    private int actionType;
    private Date actionDateAnalEffic;
    private Date actionDateRelleEffic;
    private int actionAnalyse;
}
