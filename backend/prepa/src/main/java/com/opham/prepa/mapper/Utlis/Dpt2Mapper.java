package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Dpt2;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dpt2Mapper implements RowMapper<String>  {

    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {

        return rs.getString("NCPS_DEPARTEMENT");

    }
}
