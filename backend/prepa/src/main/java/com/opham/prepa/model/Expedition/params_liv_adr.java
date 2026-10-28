package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class params_liv_adr implements Serializable{
    private String jour;
    private int jour_int;
    private int a_7h;
    private int a_8h;
    private int a_9h;
    private int a_10h;
    private int a_11h;
    private int a_12h;
    private int a_13h;
    private int a_14h;
    private int a_15h;
    private int a_16h;
    private int a_17h;
    private int a_18h;
}
