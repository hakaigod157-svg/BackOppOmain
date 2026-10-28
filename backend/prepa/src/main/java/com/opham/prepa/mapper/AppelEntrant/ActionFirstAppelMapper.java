package com.opham.prepa.mapper.AppelEntrant;

import com.opham.prepa.model.AppelEntrant.ActionFirstAppel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionFirstAppelMapper  implements RowMapper<ActionFirstAppel> {
    public ActionFirstAppel mapRow(ResultSet rs, int rowNum) throws SQLException {
        ActionFirstAppel cmd = new ActionFirstAppel();
            cmd.setId(rs.getInt(1));
            cmd.setName(rs.getString(2));
            cmd.setResolu(rs.getInt(3));
            cmd.setVisible_btn(rs.getInt(4));
            cmd.set_status(rs.getBoolean(5));
        return cmd;
    }
}


