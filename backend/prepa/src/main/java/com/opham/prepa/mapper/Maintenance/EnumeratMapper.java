package com.opham.prepa.mapper.Maintenance;

import com.opham.prepa.model.Maintenance.Enumerat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumeratMapper implements RowMapper<Enumerat> {
    @Override
    public Enumerat mapRow(ResultSet rs, int rowNum) throws SQLException {
        Enumerat en = new Enumerat();
        en.setENUM_CODE(rs.getString("ENUM_CODE"));
       // en.setENUM_VALEUR(rs.getString("ENUM_VALEUR"));

        return en;
    }
}
