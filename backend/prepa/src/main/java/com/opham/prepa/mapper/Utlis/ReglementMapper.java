package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Proc;
import com.opham.prepa.model.Utils.Reglement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReglementMapper implements RowMapper<Reglement> {
    @Override
    public Reglement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reglement R = new Reglement();
        R.setCode(rs.getString(1));
        R.setNom(rs.getString(2));
        R.setDec(rs.getInt(3));
        R.setTypeEch(rs.getInt(4));
        R.setJourEch(rs.getInt(5));
        R.setMode(rs.getInt(6));
        return R;
    }
}
