package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestAvAddCmdLiv implements Serializable {
    private int is_liv;
    private int atte_liv;
    private String axe;
    private int is_cmd;
    private String cmd;
    private String modeliv;

}
