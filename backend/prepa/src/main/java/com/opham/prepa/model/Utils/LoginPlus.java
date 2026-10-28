package com.opham.prepa.model.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginPlus {
    private  int xSeq;
    private String xUser;
    private String xPasswd;
    private String xType;
    private String xAvion;
    private String xExtra;
    private boolean xEtat;
}
