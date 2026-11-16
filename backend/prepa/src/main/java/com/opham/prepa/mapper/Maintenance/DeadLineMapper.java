package com.opham.prepa.mapper.Maintenance;

import com.opham.prepa.model.Maintenance.DeadLine;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeadLineMapper implements RowMapper<DeadLine> {
    @Override
    public DeadLine mapRow(ResultSet rs, int rowNum) throws SQLException {
        DeadLine ddl = new DeadLine();
        ddl.setMAINT_CODE(rs.getString("MAINT_CODE"));
        ddl.setETAPE_LIGNE(rs.getString("ETAPE_LIGNE"));
        ddl.setDATE_DEBUT(rs.getTimestamp("DATE_DEBUT"));
        ddl.setDATE_FIN(rs.getTimestamp("DATE_FIN"));
        ddl.setJOURS_RESTANTS(rs.getInt("JOURS_RESTANTS"));
        return ddl;
    }
}
