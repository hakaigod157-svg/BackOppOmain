package com.opham.prepa.mapper.Catalogue;

import com.opham.prepa.model.Catalogue.listLabo;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class listLaboMapper implements RowMapper<listLabo> {
    public listLabo mapRow(ResultSet rs, int rowNum) throws SQLException {
        listLabo cmd = new listLabo();
        cmd.setFocode(rs.getString(1));
        cmd.setFonom(rs.getString(2));

        return cmd;
    }
}
