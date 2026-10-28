package com.opham.prepa.mapper.Utlis;


import com.opham.prepa.model.Utils.Lock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LockMapper  implements RowMapper<Lock> {
    @Override
    public Lock mapRow(ResultSet rs, int rowNum) throws SQLException {
        Lock l = new Lock();
        l.setEtat(rs.getInt("etat"));
        l.setMyUser(rs.getString("myuser"));
        return l;
    }
}
