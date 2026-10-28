package com.opham.prepa.mapper.Expedition;
import com.opham.prepa.model.Expedition.livraison;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class livraisonMapper implements RowMapper<livraison>{
    public livraison mapRow(ResultSet rs, int rowNum) throws SQLException {
        livraison cmd = new livraison();
        cmd.setVague(rs.getString(1));
        cmd.setType(rs.getString(2));
        cmd.setCentre(rs.getInt(3));
        cmd.setCentre_colis(rs.getInt(4));
        cmd.setEst(rs.getInt(5));
        cmd.setEst_colis(rs.getInt(6));
        cmd.setItaosy(rs.getInt(7));
        cmd.setItaosy_colis(rs.getInt(8));
        cmd.setIvato(rs.getInt(9));
        cmd.setIvato_colis(rs.getInt(10));
        cmd.setNord(rs.getInt(11));
        cmd.setNord_colis(rs.getInt(12));
        cmd.setSixsept(rs.getInt(13));
        cmd.setSix_sept_colis(rs.getInt(14));
        cmd.setSud(rs.getInt(15));
        cmd.setSud_colis(rs.getInt(16));
        cmd.setSud_est(rs.getInt(17));
        cmd.setSud_est_colis(rs.getInt(18));
        cmd.setTotal(rs.getInt(19));
        cmd.setTotal_colis(rs.getInt(20));

        return cmd;
    }

}
