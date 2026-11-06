package com.opham.prepa.mapper.Maintenance;
import com.opham.prepa.model.Maintenance.MaintStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaintStatusMapper implements RowMapper<MaintStatus> {
    @Override
    public MaintStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
        MaintStatus mtnce = new MaintStatus();
        mtnce.setMAINT_CODE_LIGNE(rs.getString("MAINT_CODE_LIGNE"));
        mtnce.setMAINT_ETAPE_LIGNE(rs.getString("MAINT_ETAPE_LIGNE"));
        mtnce.setMAINT_ETAPE_PREVU(rs.getDate("MAINT_ETAPE_PREVU"));
        mtnce.setMAIT_ETAPE_REEL(rs.getDate("MAIT_ETAPE_REEL"));
        mtnce.setMAINT_ETAPE_ETAT(rs.getInt("MAINT_ETAPE_ETAT"));

        return mtnce;
    }
}
