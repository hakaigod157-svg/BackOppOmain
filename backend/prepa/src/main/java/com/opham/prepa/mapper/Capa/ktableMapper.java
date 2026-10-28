package com.opham.prepa.mapper.Capa;

import com.opham.prepa.model.Capa.ktable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class ktableMapper implements RowMapper<ktable>{
    public ktable mapRow(ResultSet rs, int rowNum) throws SQLException{
        ktable kt = new ktable();

        kt.setSeqk(rs.getInt("SEQ"));
        kt.setNomtable(rs.getString("NOMTABLE"));
        kt.setAnnee(rs.getInt("ANNEE"));
        kt.setMois(rs.getInt("MOIS"));
        kt.setValeur(rs.getInt("VALEUR"));

        return kt;
    }
}
