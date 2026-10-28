package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chauf {
    private String m_desc;
    private String num_voit;
    private String m_chauf;
    private boolean m_dispo;
    private String m_type;
    private String m_code;
    private String m_typeLivraison;
}
