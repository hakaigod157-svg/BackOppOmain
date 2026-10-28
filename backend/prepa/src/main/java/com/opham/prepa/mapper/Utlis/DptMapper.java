package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Dpt;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DptMapper implements RowMapper<String>  {

    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {

        return rs.getString("LIBELLE_DEP");

    }
}
