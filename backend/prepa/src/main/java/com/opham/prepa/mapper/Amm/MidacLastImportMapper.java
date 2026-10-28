package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.MidacLastImport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MidacLastImportMapper implements RowMapper<MidacLastImport>{
    public MidacLastImport mapRow(ResultSet rs, int rowNum) throws SQLException {
        MidacLastImport cmd = new MidacLastImport();
        cmd.setMois(rs.getString(1));
        cmd.setAnnee(rs.getInt(2));

        return cmd;
    }
}
