package com.opham.prepa.model.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.fop.datatypes.Numeric;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CauseRacine {

    private int causeracseq;
    private String causeracncpscode;
    private Date causeracdate;
    private String causeracdescription;
    private int nbaction;
    private String causeracprocimput;
    private String etat;
    private int causeracvalide;
}
