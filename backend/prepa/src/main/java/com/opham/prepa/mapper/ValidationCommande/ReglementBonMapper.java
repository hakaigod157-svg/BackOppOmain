package com.opham.prepa.mapper.ValidationCommande;

import com.opham.prepa.model.ValidationCommande.Reglement;
import com.opham.prepa.model.ValidationCommande.ReglementBon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReglementBonMapper implements RowMapper<ReglementBon> {
    public ReglementBon mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReglementBon cmd = new ReglementBon();
        cmd.setCode_rgbon(rs.getString(1));
        cmd.setNom_client_rgbon(rs.getString(2));
        cmd.setDate_rgbon(rs.getDate(3));
        cmd.setMode_rgbon(rs.getString(4));
        cmd.setRef_rgchqbon(rs.getString(5));
        cmd.setRefban_rgbon(rs.getString(6));
        cmd.setFacture_rgbon(rs.getString(7));
        cmd.setMontant_rgbon(rs.getBigDecimal(8));

        return cmd;
    }
}
