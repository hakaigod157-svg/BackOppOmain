package com.opham.prepa.model.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reglement implements Serializable {

    private String code;
    private String nom;
    private int dec;
    private int typeEch;
    private int jourEch;
    private int mode;
}
