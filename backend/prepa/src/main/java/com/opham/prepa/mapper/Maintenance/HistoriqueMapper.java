package com.opham.prepa.mapper.Maintenance;

import com.opham.prepa.model.Maintenance.Historique;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class HistoriqueMapper implements RowMapper<Historique> {
    @Override
    public Historique mapRow(ResultSet rs, int rowNum) throws SQLException {
        Historique tantara = new Historique();
        tantara.setDemandeCode(rs.getString("demande_code"));
        tantara.setDate(rs.getTimestamp("date"));
        tantara.setDemandeEtat(rs.getInt("demande_etat"));
        tantara.setUserId(rs.getInt("user_id"));
        tantara.setUserNom(rs.getString("user_nom"));
        return tantara;

    }
}
