package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<String>  {

    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString("USER_NOM");
    }
}
