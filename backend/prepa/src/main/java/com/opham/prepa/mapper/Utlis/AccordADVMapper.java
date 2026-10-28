package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.AccordADV;
import com.opham.prepa.model.Utils.Alerte;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccordADVMapper implements RowMapper<AccordADV> {
    @Override
    public AccordADV mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccordADV A = new AccordADV();
        A.setCode(rs.getString(1));
        A.setDesignation(rs.getString(2));
        return A;
    }
}
