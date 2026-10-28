package com.opham.prepa.mapper.EnPreparation;

import com.opham.prepa.model.EnPreparation.DetailBP;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailBPCTRLMapper implements RowMapper<DetailBP> {
    @Override
    public DetailBP mapRow(ResultSet rs, int rowNum) throws SQLException {
        DetailBP cmd = new DetailBP();
        cmd.setCodeBP(rs.getString("BPCODE"));
        cmd.setEtat_BP(rs.getString("ETAT_CONTROL"));
        cmd.setAgent_ctrl(rs.getString("CONTROLEUR"));
        cmd.setDebut_ctrl(rs.getTimestamp("DEBUT_CTRL"));
        cmd.setFin_ctrl(rs.getTimestamp("FIN_CTRL"));
        cmd.setDuree_ctrl(rs.getInt("DUREE_CTRL"));
        cmd.setQte_total(rs.getInt("QTE_TOTAL"));
        cmd.setNbColis(rs.getInt("NB_COLIS"));
        return cmd;
    }
}
