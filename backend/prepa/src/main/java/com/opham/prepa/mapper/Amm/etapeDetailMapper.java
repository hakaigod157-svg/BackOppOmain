package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.etapeDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class etapeDetailMapper implements RowMapper<etapeDetail>{
    public etapeDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        etapeDetail cmd = new etapeDetail();
        cmd.setEtape(rs.getString(1));
        cmd.setEtat(rs.getInt(2));
        cmd.setDate_etape(rs.getTimestamp(3));
        cmd.setAuto(rs.getInt(4));

        return cmd;
    }
}
