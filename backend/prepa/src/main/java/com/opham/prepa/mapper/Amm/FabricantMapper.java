package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.fabricant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FabricantMapper implements RowMapper<fabricant>{
    public fabricant mapRow(ResultSet rs, int rowNum) throws SQLException {
        fabricant cmd = new fabricant();
        cmd.setFab_code(rs.getString(1));
        cmd.setFab_libelle(rs.getString(2));

        return cmd;
    }
}
