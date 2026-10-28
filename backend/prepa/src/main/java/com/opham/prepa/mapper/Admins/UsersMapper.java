package com.opham.prepa.mapper.Admins;

import com.opham.prepa.model.Admins.Groupe;
import com.opham.prepa.model.Admins.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMapper implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users U = new Users();
        U.setName(rs.getString(1));
        U.setFullname(rs.getString(2));
        return U;
    }
}
