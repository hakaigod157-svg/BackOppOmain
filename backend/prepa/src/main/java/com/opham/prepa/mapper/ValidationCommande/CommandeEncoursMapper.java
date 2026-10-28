package com.opham.prepa.mapper.ValidationCommande;

import com.opham.prepa.model.ValidationCommande.CommandeEncours;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandeEncoursMapper implements RowMapper<CommandeEncours> {

    public CommandeEncours mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommandeEncours cmd = new CommandeEncours();
        cmd.setCode(rs.getString(1));
        cmd.setDate_commande(rs.getDate(2));
        cmd.setValidation_adv(rs.getTimestamp(3));
        cmd.setValide_par(rs.getString(4));
        cmd.setEtape(rs.getString(5));
        cmd.setMontant_code(rs.getBigDecimal(6));

        return cmd;
    }
}
