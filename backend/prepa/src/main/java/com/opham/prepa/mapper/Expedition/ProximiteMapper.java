package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.Situation_axe;
import com.opham.prepa.model.Expedition.proximite;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProximiteMapper implements RowMapper<proximite> {
    @Override
    public proximite mapRow(ResultSet rs, int rowNum) throws SQLException {
        proximite D = new proximite();
        D.setP_code(rs.getString(1));
        D.setP_com(rs.getString(2));
        D.setP_mode(rs.getString(3));
        D.setP_duree(rs.getInt(4));
        D.setP_type(rs.getString(5));
        return D;
    }
}