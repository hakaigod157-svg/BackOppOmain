package com.opham.prepa.mapper.AppelEntrant;

import com.opham.prepa.model.AppelEntrant.AppelSortant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppelSortantMapper implements RowMapper<AppelSortant> {
    public AppelSortant mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppelSortant cmd = new AppelSortant();
        cmd.setSeq(rs.getString(1));
        cmd.setCalldate_sortant(rs.getTimestamp(2));
        cmd.setDate(rs.getDate(3));
        cmd.setHeure(rs.getTime(4).toLocalTime());
        cmd.setSrc_sortant(rs.getString(5));
        cmd.setNumero_sortant(rs.getString(6));
        cmd.setNumero_indicatif_sortant(rs.getString(7));
        cmd.setClient_sortant(rs.getString(8));
        cmd.setDcontext_sortant(rs.getString(9));
        cmd.setDst_sortant(rs.getString(10));
        cmd.setDstchannel_sortant(rs.getString(11));
        cmd.setPoste_interne_sortant(rs.getString(12));
        cmd.setDuration_sortant(rs.getInt(13));
        cmd.setBillsec_sortant(rs.getInt(14));
        cmd.setDisposition_sortant(rs.getString(15));
        cmd.setUniqueid_sortant(rs.getString(16));
        cmd.setEtat_sortant(rs.getInt(17));

        //cmd.setStatut(rs.getString(16));
        //cmd.setDepuis(rs.getLong(17));
        return cmd;

    }
}
