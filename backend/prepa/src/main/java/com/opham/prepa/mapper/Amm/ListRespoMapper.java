package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.ListRespo;
import com.opham.prepa.model.Amm.listnumFB;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ListRespoMapper implements RowMapper<ListRespo>{
    public ListRespo mapRow(ResultSet rs, int rowNum) throws SQLException {
        ListRespo cmd = new ListRespo();
        cmd.setResponsable(rs.getString(1));
        cmd.setActif(rs.getInt(2));

        return cmd;
    }
}
