package com.opham.prepa.mapper.BusinessIntelligence;

import com.opham.prepa.model.BusinessIntelligence.list_ca_annuel;
import com.opham.prepa.model.BusinessIntelligence.list_palier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class list_palierMapper implements RowMapper<list_palier>{
    public list_palier mapRow(ResultSet rs, int rowNum) throws SQLException {
        list_palier cmd = new list_palier();
        cmd.setPalier(rs.getString(1));
        cmd.setTaux(rs.getBigDecimal(2));
        cmd.setMilieu(rs.getBigDecimal(3));
        cmd.setPrime(rs.getBigDecimal(4));
        cmd.setEcart(rs.getDouble(5));
        return cmd;
    }
}
