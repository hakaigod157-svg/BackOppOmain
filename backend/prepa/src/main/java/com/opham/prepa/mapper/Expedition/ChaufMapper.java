package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.Chauf;
import com.opham.prepa.model.Expedition.DureeLV;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;



public class ChaufMapper implements RowMapper<Chauf> {
    @Override
    public Chauf mapRow(ResultSet rs, int rowNum) throws SQLException {
        Chauf D = new Chauf();
        D.setM_desc(rs.getString(1));
        D.setNum_voit(rs.getString(2));
        D.setM_chauf(rs.getString(3));
        D.setM_dispo(rs.getBoolean(4));
        D.setM_code(rs.getString(5));
        return D;
    }
}