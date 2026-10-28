package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Emetteur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmetteurMapper implements RowMapper<String>  {
    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString("NCPS_EMETTEUR");

    }
}