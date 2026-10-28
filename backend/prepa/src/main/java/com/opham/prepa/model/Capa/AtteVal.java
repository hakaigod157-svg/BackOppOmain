package com.opham.prepa.model.Capa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtteVal implements Serializable {
    private int ncpsSeq;
    private Date ncpsDate;
    private String ncpsEmetteur;
    private String ncpsDescription;
    private String ncpsDepartement;
    private int ncpsValide;
}
