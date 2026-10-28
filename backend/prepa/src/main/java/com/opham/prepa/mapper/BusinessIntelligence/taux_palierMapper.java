package com.opham.prepa.mapper.BusinessIntelligence;

import com.opham.prepa.model.BusinessIntelligence.list_palier;
import com.opham.prepa.model.BusinessIntelligence.taux_palier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class taux_palierMapper implements RowMapper<taux_palier>{
    public taux_palier mapRow(ResultSet rs, int rowNum) throws SQLException {
        taux_palier cmd = new taux_palier();
        cmd.setMat_taux(rs.getBigDecimal(1));

        return cmd;
    }
}
