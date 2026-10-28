package com.opham.prepa.mapper.BusinessIntelligence;
import com.opham.prepa.model.BusinessIntelligence.det_palier;
import com.opham.prepa.model.BusinessIntelligence.list_palier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class det_palierMapper implements RowMapper<det_palier>{
    public det_palier mapRow(ResultSet rs, int rowNum) throws SQLException {
        det_palier cmd = new det_palier();
        cmd.setMois(rs.getString(1));
        cmd.setQualitatif(rs.getDouble(2));
        cmd.setQuantitatif(rs.getDouble(3));
        cmd.setTotal(rs.getDouble(4));
        cmd.setPrime_max(rs.getDouble(5));
        return cmd;
    }
}
