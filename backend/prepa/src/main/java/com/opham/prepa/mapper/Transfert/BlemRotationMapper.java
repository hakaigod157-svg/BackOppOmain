package com.opham.prepa.mapper.Transfert;

import com.opham.prepa.model.Transfert.ProblemeStock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlemRotationMapper implements RowMapper<ProblemeStock> {
    @Override
    public ProblemeStock mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProblemeStock cmd = new ProblemeStock();
        cmd.setQteStock(rs.getInt(1));
        cmd.setEmpl(rs.getString(2));
        return cmd;
    }
}
