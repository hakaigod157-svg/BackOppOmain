package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Famille;
import com.opham.prepa.model.Utils.Labo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilleMapper implements RowMapper<Famille> {

    @Override
    public Famille mapRow(ResultSet rs, int rowNum) throws SQLException {
        Famille F = new Famille();
        F.setCode(rs.getString(1));
        F.setLibelle(rs.getString(2));
        return F;
    }
}
