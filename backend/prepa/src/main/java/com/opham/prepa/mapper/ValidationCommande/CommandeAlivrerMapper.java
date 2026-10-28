package com.opham.prepa.mapper.ValidationCommande;

import com.opham.prepa.model.ValidationCommande.CommandeAlivrer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CommandeAlivrerMapper implements RowMapper<CommandeAlivrer>{

    public CommandeAlivrer mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommandeAlivrer cmd = new CommandeAlivrer();
        cmd.setFacture(rs.getString(1));
        cmd.setCodeclient(rs.getString(2));
        cmd.setNomclient(rs.getString(3));
        cmd.setDate(rs.getTimestamp(4));
        cmd.setTtc(rs.getBigDecimal(5));
        cmd.setDepuis(rs.getInt(6));
        cmd.setCommande(rs.getString(7));
        cmd.setCommentaires(rs.getString(8));
        cmd.setEtape(rs.getString(9));
        cmd.setMontant_payer(rs.getBigDecimal(10));
        return cmd;
    }
}
