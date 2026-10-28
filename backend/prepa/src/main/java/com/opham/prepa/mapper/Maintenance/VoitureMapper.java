package com.opham.prepa.mapper.Maintenance;

import com.opham.prepa.model.Maintenance.Voiture;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class VoitureMapper implements RowMapper<Voiture> {
    @Override
   /* public Voiture mapRow(ResultSet rs, int rowNum) throws SQLException {
        Voiture voiture = new Voiture();
        voiture.setVOIT_NUM(rs.getString("VOIT_NUM"));

        voiture.setVOIT_DATE( rs.getTimestamp("VOIT_DATE"));
        voiture.setVOIT_KM(rs.getInt("VOIT_KM"));
        voiture.setVOIT_REF(rs.getString("VOIT_REF"));
        return voiture;
    } */
    public Voiture mapRow(ResultSet rs, int rowNum) throws SQLException {
        Voiture voiture = new Voiture();
        voiture.setVOIT_NUM(rs.getString("VOIT_NUM"));
        voiture.setVOIT_KM(rs.getInt("VOIT_KM"));
        voiture.setDATE( rs.getTimestamp("DATE"));
        return voiture;
    }
}
