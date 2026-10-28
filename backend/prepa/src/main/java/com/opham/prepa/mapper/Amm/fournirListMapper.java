package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.etapeList;
import com.opham.prepa.model.Amm.fournirList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class fournirListMapper implements RowMapper<fournirList>{
    public fournirList mapRow(ResultSet rs, int rowNum) throws SQLException {
        fournirList cmd = new fournirList();
        cmd.setDossier_code(rs.getString(1));
        cmd.setDossier_lib(rs.getString(2));

        return cmd;
    }
}
