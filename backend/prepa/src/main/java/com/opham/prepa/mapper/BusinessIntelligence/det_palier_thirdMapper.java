package com.opham.prepa.mapper.BusinessIntelligence;
import com.opham.prepa.model.BusinessIntelligence.det_palier_second;
import com.opham.prepa.model.BusinessIntelligence.det_palier_third;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class det_palier_thirdMapper implements RowMapper<det_palier_third>{
    public det_palier_third mapRow(ResultSet rs, int rowNum) throws SQLException {
        det_palier_third cmd = new det_palier_third();
        cmd.setMois_third(rs.getString(1));
        cmd.setQualitatif_third(rs.getDouble(2));
        cmd.setQuantitatif_third(rs.getDouble(3));
        cmd.setTotal_third(rs.getDouble(4));
        cmd.setPrime_max_third(rs.getDouble(5));
        return cmd;
    }
}
