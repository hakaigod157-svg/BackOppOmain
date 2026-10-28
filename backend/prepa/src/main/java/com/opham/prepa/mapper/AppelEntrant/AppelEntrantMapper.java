package com.opham.prepa.mapper.AppelEntrant;

import com.opham.prepa.model.AppelEntrant.AppelEntrant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.sql.Timestamp;

//
public class
AppelEntrantMapper implements RowMapper<AppelEntrant> {
    public AppelEntrant mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppelEntrant cmd = new AppelEntrant();
        cmd.setSeq(rs.getString(1));
        cmd.setCalldate(rs.getTimestamp(2));
        cmd.setDate(rs.getDate(3));
        cmd.setHeure(rs.getTime(4).toLocalTime());
        cmd.setSrc(rs.getString(5));
        cmd.setNumero(rs.getString(6));
        cmd.setNumero_indicatif(rs.getString(7));
        cmd.setClient(rs.getString(8));
        cmd.setDst(rs.getString(9));
        cmd.setPoste_interne(rs.getString(10));
        cmd.setDuration(rs.getInt(11));
        cmd.setBillsec(rs.getInt(12));
        cmd.setDisposition(rs.getString(13));
        cmd.setEtat(rs.getInt(14));
        cmd.setStatut(rs.getString(15));
        cmd.setDepuis(rs.getLong(16));
        cmd.setDaterappel_1(rs.getTimestamp(17));
        cmd.setDaterappel_2(rs.getTimestamp(18));
        cmd.setPoste_rap1(rs.getString(19));
        cmd.setPoste_rap2(rs.getString(20));
        cmd.setVisible_btn(rs.getInt(21));
        cmd.setResolu(rs.getInt(22));
        cmd.setCouleur(rs.getInt(23));
        cmd.setRappel(rs.getInt(24));
        return cmd;

    }
}
