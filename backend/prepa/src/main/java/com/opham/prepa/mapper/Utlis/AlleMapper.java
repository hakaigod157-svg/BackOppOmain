package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Alle;
import com.opham.prepa.model.Utils.Depot;
import com.opham.prepa.model.Utils.Droit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlleMapper  implements RowMapper<Alle> {
    @Override
    public Alle mapRow(ResultSet rs, int rowNum) throws SQLException {
        Alle d = new Alle();
        d.setDepot(rs.getString("xDEPOT"));
        d.setAlle(rs.getString("xALLE"));
        d.setStatut(rs.getBoolean("xSTATUT"));
        return d;
    }
}
