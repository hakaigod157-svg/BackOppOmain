package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class proximite implements Serializable {
    private String p_code;
    private String p_com;
    private String p_mode;
    private int p_duree;
    private String p_type;
}
