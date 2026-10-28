package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Apreparer.InfoCommande;
import com.opham.prepa.model.Utils.Alerte;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlerteMapper implements RowMapper<Alerte> {
    @Override
    public Alerte mapRow(ResultSet rs, int rowNum) throws SQLException {
        Alerte alerte = new Alerte();
        alerte.setCode(rs.getString("code"));
        alerte.setVal(rs.getInt("val"));
        alerte.setAlerte(rs.getInt("Alerte"));
        return alerte;
    }
}
