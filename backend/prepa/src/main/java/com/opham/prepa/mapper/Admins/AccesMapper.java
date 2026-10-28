package com.opham.prepa.mapper.Admins;

import com.opham.prepa.model.Admins.Acces;
import com.opham.prepa.model.Admins.Groupe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesMapper implements RowMapper<Acces> {
    @Override
    public Acces mapRow(ResultSet rs, int rowNum) throws SQLException {
        Acces A = new Acces();
        A.setCode(rs.getString(1));
        A.setDesc(rs.getString(2));
        A.setGroupe(rs.getString(3));
        A.setRead(rs.getBoolean(4));
        A.setWrite(rs.getBoolean(5));
        A.setType(rs.getString(6));
        A.setCategorie(rs.getString(7));
        return A;
    }
}
