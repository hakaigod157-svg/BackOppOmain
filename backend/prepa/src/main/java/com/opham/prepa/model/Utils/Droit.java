package com.opham.prepa.model.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Droit implements Serializable {
    private int lire;
    private int write;
    private String groupe;
    private String user;
    private String descAcces;

}
