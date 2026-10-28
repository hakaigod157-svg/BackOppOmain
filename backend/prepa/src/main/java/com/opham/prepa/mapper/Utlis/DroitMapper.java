package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Droit;
import com.opham.prepa.model.Utils.Etape;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DroitMapper implements RowMapper<Droit> {
    @Override
    public Droit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Droit d = new Droit();
        d.setGroupe(rs.getString("CodeGroupe"));
        d.setLire(rs.getInt("LIRE"));
        d.setWrite(rs.getInt("WRITE"));
        d.setUser(rs.getString("xUser"));
        d.setDescAcces(rs.getString("xDroit"));
        return d;
    }
}
