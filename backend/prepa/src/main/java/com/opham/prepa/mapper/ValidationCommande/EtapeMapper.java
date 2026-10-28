package com.opham.prepa.mapper.ValidationCommande;

import com.opham.prepa.model.ValidationCommande.Etape;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EtapeMapper implements RowMapper<Etape>{
    public Etape mapRow(ResultSet rs, int rowNum) throws SQLException {
        Etape cmd = new Etape();
        cmd.setNb_cmd_Avalide(rs.getInt(1));
        cmd.setNb_cmd_valide(rs.getInt(2));
        cmd.setNb_bon_livre(rs.getInt(3));
        cmd.setNb_cmd_traite(rs.getInt(4));
        return cmd;
    }
}
