package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.Trans_Detail_Nb_cmd;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Trans_Detail_Nb_cmdMapper implements RowMapper<Trans_Detail_Nb_cmd>{
    public Trans_Detail_Nb_cmd mapRow(ResultSet rs, int rowNum) throws SQLException {
        Trans_Detail_Nb_cmd cmd = new Trans_Detail_Nb_cmd();
        cmd.setCccode(rs.getString(1));
        cmd.setDate_commande(rs.getDate(2));
        cmd.setFacode(rs.getString(3));
        cmd.setNb_colis(rs.getInt(4));

        return cmd;
    }
}
