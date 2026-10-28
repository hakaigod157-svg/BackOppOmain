package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Situation_axe implements Serializable {
    private String m_modeliv;
    private String  m_axe;
    private int  m_liv;
    private int  m_fact;

    private int m_ctrl;
    private int m_prep;
}
