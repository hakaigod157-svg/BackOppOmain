package com.opham.prepa.mapper.ValidationCommande;

import com.opham.prepa.model.ValidationCommande.Commande;
import com.opham.prepa.model.ValidationCommande.Reglement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandeMapper implements RowMapper<Commande> {
    public Commande mapRow(ResultSet rs, int rowNum) throws SQLException {
        Commande cmd = new Commande();
        cmd.setCode_article_cmd(rs.getString(1));
        cmd.setLibelle_cmd(rs.getString(2));
        cmd.setQte_cmd(rs.getInt(3));
        cmd.setTtc_cmd(rs.getBigDecimal(4));

        return cmd;
    }
}
