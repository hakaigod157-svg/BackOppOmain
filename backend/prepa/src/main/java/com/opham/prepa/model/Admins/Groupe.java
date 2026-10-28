package com.opham.prepa.model.Admins;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Groupe implements Serializable {
    private String code;
    private String libelle;
}
