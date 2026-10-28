package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.ListTitulaire;
import com.opham.prepa.model.Amm.listnumdossier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListTitulaireMapper implements RowMapper<ListTitulaire>{
    public ListTitulaire mapRow(ResultSet rs, int rowNum) throws SQLException {
        ListTitulaire cmd = new ListTitulaire();
        cmd.setFocode(rs.getString(1));
        cmd.setFonom(rs.getString(2));

        return cmd;
    }
}
