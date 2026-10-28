package com.opham.prepa.mapper.Apreparer;

import com.opham.prepa.model.Apreparer.InfoCommande;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoCmdMapper implements RowMapper<InfoCommande> {
    @Override
    public InfoCommande mapRow(ResultSet rs, int rowNum) throws SQLException {
        InfoCommande cmd = new InfoCommande();
        cmd.setCode_cc(rs.getString("CODE_CC"));
        cmd.setNom_client(rs.getString("NOM_CLIENT"));
        cmd.setAdresse(rs.getString("adresse"));
        cmd.setAxe(rs.getString("axe"));
        cmd.setDate_liv(rs.getTimestamp("DATE_LIV"));
        cmd.setComs_client(rs.getString("coms_client"));
        cmd.setComs_mag(rs.getString("coms_mag"));
        cmd.setContact(rs.getString("contact"));
        cmd.setRaisonDateLiv(rs.getString("raisonDateLiv"));
        cmd.setDate_l(rs.getDate("date_l"));
        cmd.setHeure_l(rs.getTime("heure_l"));
        return cmd;
    }
}
