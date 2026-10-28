package com.opham.prepa.mapper.genererBP;

import com.opham.prepa.model.genererBP.DepotCmd;
import com.opham.prepa.model.genererBP.RayonCmd;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RayonCmdMapper implements RowMapper<RayonCmd> {
    @Override
    public RayonCmd mapRow(ResultSet rs, int rowNum) throws SQLException {
        RayonCmd cmd = new RayonCmd();
        cmd.setRayon(rs.getString(1));
        cmd.setDepot(rs.getString("depot"));
        cmd.setEtat(rs.getBoolean(3));
        cmd.setNb_article(rs.getInt(4));
        return cmd;
    }
}
