package com.opham.prepa.mapper.Maintenance;

import com.opham.prepa.model.Maintenance.Enumerat;
import com.opham.prepa.model.Maintenance.Etape;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EtapeMapper implements  RowMapper<Etape>{
        @Override
        public Etape mapRow(ResultSet rs, int rowNum) throws SQLException {
            Etape vaovao = new Etape();
            vaovao.setCLASSIFICATION(rs.getString("CLASSIFICATION"));
            vaovao.setCODE(rs.getString("CODE"));
            vaovao.setVALEUR(rs.getString("VALEUR"));
            vaovao.setORDRE(rs.getInt("ORDRE"));
            vaovao.setDUREE(rs.getInt("DUREE"));
            vaovao.setETAT_ID(rs.getInt("ETAT_ID"));

            return vaovao;
        }
}
