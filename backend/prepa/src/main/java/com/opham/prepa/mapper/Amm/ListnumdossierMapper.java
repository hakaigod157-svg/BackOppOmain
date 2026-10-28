package com.opham.prepa.mapper.Amm;

import org.springframework.jdbc.core.RowMapper;
import com.opham.prepa.model.Amm.listnumdossier;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListnumdossierMapper implements RowMapper<listnumdossier>{
    public listnumdossier mapRow(ResultSet rs, int rowNum) throws SQLException {
        listnumdossier cmd = new listnumdossier();
        cmd.setNumdossier(rs.getString(1));

        return cmd;
    }
}
