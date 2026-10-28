package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.DureeLV;
import com.opham.prepa.model.Expedition.Situation_axe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Situation_axeMapper implements RowMapper<Situation_axe> {
    @Override
    public Situation_axe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Situation_axe D = new Situation_axe();
        D.setM_modeliv(rs.getString(1));
        D.setM_axe(rs.getString(2));
        D.setM_liv(rs.getInt(3));
        D.setM_fact(rs.getInt(4));
        D.setM_ctrl(rs.getInt(5));
        D.setM_prep(rs.getInt(6));
        return D;
    }
}
