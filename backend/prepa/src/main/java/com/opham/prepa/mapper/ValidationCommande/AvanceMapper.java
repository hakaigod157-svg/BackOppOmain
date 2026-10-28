package com.opham.prepa.mapper.ValidationCommande;

import com.opham.prepa.model.ValidationCommande.Avance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AvanceMapper implements RowMapper<Avance> {
    public Avance mapRow(ResultSet rs, int rowNum) throws SQLException {
        Avance cmd = new Avance();
        cmd.setCode(rs.getString(1));
        cmd.setDate(rs.getDate(2));
        cmd.setMode(rs.getString(3));
        cmd.setReference(rs.getString(4));
        cmd.setEcheance(rs.getDate(5));
        cmd.setMontant(rs.getBigDecimal(6));
        cmd.setTotal_facture(rs.getBigDecimal(7));
        cmd.setReste(rs.getBigDecimal(8));
        cmd.setCommentaire(rs.getString(9));

        return cmd;
    }
}
