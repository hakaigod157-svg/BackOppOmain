package com.opham.prepa.mapper.ValidationCommande;


import com.opham.prepa.model.ValidationCommande.Traite;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TraiteMapper implements RowMapper<Traite>{
    public Traite mapRow(ResultSet rs, int rowNum) throws SQLException {
        Traite cmd = new Traite();
        cmd.setCode_traite(rs.getString(1));
        cmd.setCode_client_traite(rs.getString(2));
        cmd.setNom_client_traite(rs.getString(3));
        cmd.setDate_commande_traite(rs.getTimestamp(4));
        cmd.setDate_valide_adv_traite(rs.getTimestamp(5));
        cmd.setEtape_traite(rs.getString(6));
        cmd.setCondition_cmd_traite(rs.getString(7));
        cmd.setCondition_client_traite(rs.getString(8));

        return cmd;
    }
}
