package com.opham.prepa.mapper.Admins;

import com.opham.prepa.model.Admins.Groupe;
import com.opham.prepa.model.Utils.Alle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupeMapper implements RowMapper<Groupe> {
    @Override
    public Groupe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Groupe G = new Groupe();
        G.setCode(rs.getString(1));
        G.setLibelle(rs.getString(2));
        return G;
    }
}
