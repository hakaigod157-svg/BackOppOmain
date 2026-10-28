package com.opham.prepa.mapper.BusinessIntelligence;

import com.opham.prepa.model.BusinessIntelligence.list_palier_second;
import com.opham.prepa.model.BusinessIntelligence.list_palier_third;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class list_palier_thirdMapper implements RowMapper<list_palier_third>{
    public list_palier_third mapRow(ResultSet rs, int rowNum) throws SQLException {
        list_palier_third cmd = new list_palier_third();
        cmd.setPalier_third(rs.getString(1));
        cmd.setTaux_third(rs.getBigDecimal(2));
        cmd.setMilieu_third(rs.getBigDecimal(3));
        cmd.setPrime_third(rs.getBigDecimal(4));
        cmd.setEcart_third(rs.getDouble(5));
        return cmd;
    }
}
