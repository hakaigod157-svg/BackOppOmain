package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Etape;
import com.opham.prepa.model.Utils.Labo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LaboMapper implements RowMapper<Labo> {
    @Override
    public Labo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Labo l = new Labo();
        l.setCode(rs.getString(1));
        l.setNom(rs.getString(2));
        l.setAbrev(rs.getString(3));
        return l;
    }
}
