package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.DureeLV;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DureeLVMapper implements RowMapper<DureeLV> {
    @Override
    public DureeLV mapRow(ResultSet rs, int rowNum) throws SQLException {
        DureeLV D = new DureeLV();
        D.setNomClt(rs.getString(1));
        D.setSeqliv(rs.getBigDecimal(2));
        D.setArriveClt(rs.getTimestamp(3));
        D.setDepartClt(rs.getTimestamp(4));
        D.setCodeClt(rs.getString(5));
        return D;
    }
}
