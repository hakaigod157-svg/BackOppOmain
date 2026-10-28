package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Axe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AxeMapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString("CLPR");

    }
}
