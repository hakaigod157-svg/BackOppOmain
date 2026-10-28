package com.opham.prepa.mapper.Utlis;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TarifMapper implements RowMapper<String>{
    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString("ARTTARIF");
    }
}
