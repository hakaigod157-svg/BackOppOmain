package com.opham.prepa.mapper.BusinessIntelligence;

import com.opham.prepa.model.BusinessIntelligence.list_palier;
import com.opham.prepa.model.BusinessIntelligence.list_palier_second;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class list_palier_secondMapper implements RowMapper<list_palier_second>{
    public list_palier_second mapRow(ResultSet rs, int rowNum) throws SQLException {
        list_palier_second cmd = new list_palier_second();
        cmd.setPalier_second(rs.getString(1));
        cmd.setTaux_second(rs.getBigDecimal(2));
        cmd.setMilieu_second(rs.getBigDecimal(3));
        cmd.setPrime_second(rs.getBigDecimal(4));
        cmd.setEcart_second(rs.getDouble(5));
        return cmd;
    }
}
