package com.opham.prepa.model.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etape  implements Serializable {
    private int AttePrep;
    private int EncoursPrep;
    private  int AtteCtrl;
    private int EncoursCtrl;
    private int emballage;
    private int encoursPrepBP;
    private int atteCtrlBP;
    private int encoursCtrlBP;
    private int finCtrlBP;
    private int encoursPP;

}
