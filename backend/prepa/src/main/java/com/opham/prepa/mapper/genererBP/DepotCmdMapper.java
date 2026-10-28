package com.opham.prepa.mapper.genererBP;

import com.opham.prepa.model.Apreparer.Commande;
import com.opham.prepa.model.genererBP.DepotCmd;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepotCmdMapper implements RowMapper<DepotCmd> {
    @Override
    public DepotCmd mapRow(ResultSet rs, int rowNum) throws SQLException {
        DepotCmd cmd = new DepotCmd();
        cmd.setCode_CC(rs.getString("COMMANDE"));
        cmd.setDepot(rs.getString("DEPOT"));
        cmd.setNb_article(rs.getInt("ARTICLE"));
        return cmd;
    }
}
