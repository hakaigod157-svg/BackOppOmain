package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.listnumDS;
import com.opham.prepa.model.Amm.listnumFB;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class listnumFBMapper implements RowMapper<listnumFB>{
    public listnumFB mapRow(ResultSet rs, int rowNum) throws SQLException {
        listnumFB cmd = new listnumFB();
        cmd.setNum_fb(rs.getString(1));

        return cmd;
    }
}
