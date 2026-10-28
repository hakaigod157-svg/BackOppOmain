package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.Chauf;
import com.opham.prepa.model.Expedition.createlv;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatelvMapper implements RowMapper<createlv> {
    @Override
    public createlv mapRow(ResultSet rs, int rowNum) throws SQLException {
        createlv D = new createlv();
        //D.setSeq(rs.getBigDecimal(1));
       // D.setSeq2(rs.getBigDecimal(2));
        D.setCode_liv(rs.getString(1));
        return D;
    }
}