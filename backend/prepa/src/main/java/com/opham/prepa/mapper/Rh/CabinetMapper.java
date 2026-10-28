package com.opham.prepa.mapper.Rh;

import com.opham.prepa.model.Rh.Cabinet;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CabinetMapper implements RowMapper<Cabinet>  {

    @Override
    public Cabinet mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cabinet c = new Cabinet();
        c.setCab_nom(rs.getString("CAB_NOM"));
        return c;
    }
}
