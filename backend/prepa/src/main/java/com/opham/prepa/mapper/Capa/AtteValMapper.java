package com.opham.prepa.mapper.Capa;

import com.opham.prepa.model.Capa.AtteVal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AtteValMapper implements RowMapper<AtteVal>{
    public AtteVal mapRow(ResultSet rs, int rowNum) throws SQLException{
        AtteVal atte = new AtteVal();

        atte.setNcpsSeq(rs.getInt("NCPS_SEQ"));
        atte.setNcpsDate(rs.getDate("NCPS_DATE"));
        atte.setNcpsEmetteur(rs.getString("NCPS_EMETTEUR"));
        atte.setNcpsDescription(rs.getString("NCPS_DESCRIPTION"));
        atte.setNcpsDepartement(rs.getString("NCPS_DEPARTEMENT"));

        return atte;
    }
}
