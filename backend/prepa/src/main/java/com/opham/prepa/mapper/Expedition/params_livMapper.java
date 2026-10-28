package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.params_liv;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class params_livMapper implements RowMapper<params_liv>{
    public params_liv mapRow(ResultSet rs, int rowNum) throws SQLException {
        params_liv cmd = new params_liv();
        cmd.setJour(rs.getString(1));
        cmd.setJour_int(rs.getInt(2));
        cmd.setA_7h(rs.getInt(3));
        cmd.setA_8h(rs.getInt(4));
        cmd.setA_9h(rs.getInt(5));
        cmd.setA_10h(rs.getInt(6));
        cmd.setA_11h(rs.getInt(7));
        cmd.setA_12h(rs.getInt(8));
        cmd.setA_13h(rs.getInt(9));
        cmd.setA_14h(rs.getInt(10));
        cmd.setA_15h(rs.getInt(11));
        cmd.setA_16h(rs.getInt(12));
        cmd.setA_17h(rs.getInt(13));
        cmd.setA_18h(rs.getInt(14));


        return cmd;
    }
}
