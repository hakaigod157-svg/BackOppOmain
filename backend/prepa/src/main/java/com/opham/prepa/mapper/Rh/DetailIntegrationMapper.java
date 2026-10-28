package com.opham.prepa.mapper.Rh;

import com.opham.prepa.model.Rh.DetailIntegration;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailIntegrationMapper implements RowMapper<DetailIntegration>{
    public DetailIntegration mapRow(ResultSet rs, int rowNum) throws SQLException {
        DetailIntegration cmd = new DetailIntegration();

        cmd.setLigne_etape_code(rs.getString(1));
        cmd.setLigne_etape_etape(rs.getString(2));
        cmd.setEtape_libelle(rs.getString(3));
        cmd.setLigne_etape_date_prevision(rs.getDate(4));
        cmd.setLigne_etape_date(rs.getDate(5));

        return cmd;
    }
}
