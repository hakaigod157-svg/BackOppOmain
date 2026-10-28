package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Depot;
import com.opham.prepa.model.Utils.Transporteur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransporteurMapper implements RowMapper<Transporteur>{
    @Override
    public Transporteur mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transporteur cmd = new Transporteur();
        cmd.setXNum(rs.getString(1));
        cmd.setXType(rs.getString(2));
        cmd.setXCode(rs.getBigDecimal(3));
        cmd.setXCategorie(rs.getString(4));
        cmd.setXType_service(rs.getString(5));
        cmd.setXlibelle(rs.getString(6));

        return cmd;
    }
}
