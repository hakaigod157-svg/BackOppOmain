package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.etapeList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class etapeListMapper implements RowMapper<etapeList>{
    public etapeList mapRow(ResultSet rs, int rowNum) throws SQLException {
        etapeList cmd = new etapeList();
        cmd.setEtape_dossier(rs.getString(1));

        return cmd;
    }
}
