package com.opham.prepa.mapper.Maintenance;

import com.opham.prepa.model.Maintenance.EtapeEnCours;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EtapeEnCoursMapper implements RowMapper<EtapeEnCours> {
    @Override
    public EtapeEnCours mapRow(ResultSet rs, int rowNum) throws SQLException {
        EtapeEnCours etape = new EtapeEnCours();
        etape.setMAINT_ETAPE_LIGNE(rs.getString("MAINT_ETAPE_LIGNE"));
        return etape;
    }
}
