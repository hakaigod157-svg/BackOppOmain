package com.opham.prepa.mapper.Maintenance;


import com.opham.prepa.model.Maintenance.Realisation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RealisationMapper implements RowMapper<Realisation> {
    @Override
    public Realisation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Realisation real = new Realisation();
        real.setMAINT_CODE_REAL(rs.getString("MAINT_CODE_REAL"));
        real.setMAINT_INTERV_REAL(rs.getString("MAINT_INTERV_REAL"));
        real.setMAINT_DATE_REAL(rs.getDate("MAINT_DATE_REAL"));
        real.setMAINT_ACTION_REAL(rs.getString("MAINT_ACTION_REAL"));
        real.setMAINT_FNR_REAL(rs.getString("MAINT_FNR_REAL"));
        real.setMAINT_PRIX_REAL(rs.getInt("MAINT_PRIX_REAL"));
        real.setMAINT_MRAQUE_MARQUE(rs.getString("MAINT_MRAQUE_MARQUE"));
        real.setMAINT_DATE_PREVU(rs.getDate("MAINT_DATE_PREVU"));
        return real;
    }
}
