package com.opham.prepa.mapper.BusinessIntelligence;
import com.opham.prepa.model.BusinessIntelligence.det_palier;
import com.opham.prepa.model.BusinessIntelligence.det_palier_second;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class det_palier_secondMapper implements RowMapper<det_palier_second>{
    public det_palier_second mapRow(ResultSet rs, int rowNum) throws SQLException {
        det_palier_second cmd = new det_palier_second();
        cmd.setMois_second(rs.getString(1));
        cmd.setQualitatif_second(rs.getDouble(2));
        cmd.setQuantitatif_second(rs.getDouble(3));
        cmd.setTotal_second(rs.getDouble(4));
        cmd.setPrime_max_second(rs.getDouble(5));
        return cmd;
    }
}
