package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Depot;
import com.opham.prepa.model.Utils.Etape;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepotMapper implements RowMapper<Depot> {
    @Override
    public Depot mapRow(ResultSet rs, int rowNum) throws SQLException {
        Depot d = new Depot();
        d.setCode(rs.getString("DPCODE"));
        d.setNom(rs.getString("DPNOM"));
        d.setCentral(rs.getBoolean("DPCENTRAL"));
        d.setColis(rs.getBoolean("DPCOLIS"));
        d.setVente(rs.getBoolean("xDPVTE"));
        d.setTransfert(rs.getBoolean("DP_TRANSFERT"));
        d.setPrepa(rs.getBoolean("DP_PREPA"));
        return d;
    }
}
