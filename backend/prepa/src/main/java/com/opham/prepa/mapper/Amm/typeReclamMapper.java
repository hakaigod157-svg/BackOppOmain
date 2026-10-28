package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.typeReclam;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class typeReclamMapper implements RowMapper<typeReclam>{
    public typeReclam mapRow(ResultSet rs, int rowNum) throws SQLException {
        typeReclam cmd = new typeReclam();
        cmd.setType_recl_code(rs.getString(1));
        cmd.setLib_recl(rs.getString(2));

        return cmd;
    }
}
