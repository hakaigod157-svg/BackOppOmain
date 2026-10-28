package com.opham.prepa.mapper.EnPreparation;

import com.opham.prepa.model.EnPreparation.DetailBP;
import com.opham.prepa.model.EnPreparation.ListLigneBP;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailBPMapper implements RowMapper<DetailBP> {
    @Override
    public DetailBP mapRow(ResultSet rs, int rowNum) throws SQLException {
        DetailBP cmd = new DetailBP();
        cmd.setCodeBP(rs.getString("BPCODE"));
        cmd.setEtat_BP(rs.getString("ETAT_PREPA_RAYON"));
        cmd.setRayon(rs.getString("RAYON_PREPARATION"));
        cmd.setDebut_prep_rayon(rs.getTimestamp("DEBUT_PREPA_RAYON"));
        cmd.setFin_prep_rayon(rs.getTimestamp("FIN_PREPA_RAYON"));
        cmd.setDuree_prep_rayon(rs.getInt("DUREE_PREPA_RAYON"));
        cmd.setAgent_prep_rayon(rs.getString("AGENT_PREPA_RAYON"));
        cmd.setQte_total(rs.getInt("QTE_TOTAL"));
        return cmd;
    }
}
