package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Labo;
import com.opham.prepa.model.Utils.LoginPlus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPlusMapper implements RowMapper<LoginPlus> {
    @Override
    public LoginPlus mapRow(ResultSet rs, int rowNum) throws SQLException {
        LoginPlus l = new LoginPlus();
        l.setXSeq(rs.getInt(1));
        l.setXUser(rs.getString(2));
        l.setXPasswd(rs.getString(3));
        l.setXType(rs.getString(4));
        l.setXAvion(rs.getString(5));
        l.setXExtra(rs.getString(6));
        l.setXEtat(rs.getBoolean(7));
        return l;
    }
}