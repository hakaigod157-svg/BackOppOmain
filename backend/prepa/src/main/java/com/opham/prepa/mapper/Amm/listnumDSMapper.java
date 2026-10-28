package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.listnumDS;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class listnumDSMapper implements RowMapper<listnumDS>{
    public listnumDS mapRow(ResultSet rs, int rowNum) throws SQLException {
        listnumDS cmd = new listnumDS();
        cmd.setNum_ds(rs.getString(1));

        return cmd;
    }
}
