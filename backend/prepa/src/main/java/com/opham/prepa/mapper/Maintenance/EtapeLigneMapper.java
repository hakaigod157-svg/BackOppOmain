package com.opham.prepa.mapper.Maintenance;


import com.opham.prepa.model.Maintenance.EtapeLigne;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EtapeLigneMapper implements RowMapper<EtapeLigne> {
    @Override
    public EtapeLigne mapRow(ResultSet rs, int rowNum) throws SQLException {
        EtapeLigne lgn = new EtapeLigne();
       lgn.setMAINT_CODE_LIGNE(rs.getString("MAINT_CODE_LIGNE"));
        lgn.setMAINT_ETAPE_LIGNE(rs.getString("MAINT_ETAPE_LIGNE"));
        lgn.setMAINT_ETAPE_PREVU(rs.getDate("MAINT_ETAPE_PREVU"));
        lgn.setMAINT_ETAPE_REEL(rs.getDate("MAINT_ETAPE_REEL"));
        lgn.setMAINT_ETAPE_ETAT(rs.getInt("MAINT_ETAPE_ETAT"));
        return lgn;
    }
}
