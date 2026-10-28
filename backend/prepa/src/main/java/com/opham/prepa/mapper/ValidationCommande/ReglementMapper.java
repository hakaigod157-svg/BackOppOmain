package com.opham.prepa.mapper.ValidationCommande;

import com.opham.prepa.model.ValidationCommande.Reglement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReglementMapper implements RowMapper<Reglement> {
    public Reglement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reglement cmd = new Reglement();
        cmd.setCode_rg(rs.getString(1));
        cmd.setNom_client_rg(rs.getString(2));
        cmd.setDate_rg(rs.getDate(3));
        cmd.setMontant_rg(rs.getBigDecimal(4));
        cmd.setMode_rg(rs.getString(5));
        cmd.setEcheance_rg(rs.getDate(6));
        cmd.setReference_rg(rs.getString(7));
        cmd.setCommentaire_rg(rs.getString(8));

        return cmd;
    }
}
