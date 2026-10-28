package com.opham.prepa.model.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Client  implements Serializable{
    private int seq;
    private String clcode;
    private String clnom1;
    private String cladr1;
    private String cladr2;
    private int clstatus;
    private String clsa;
    private String clvaleur;
    private String clcat;
}
