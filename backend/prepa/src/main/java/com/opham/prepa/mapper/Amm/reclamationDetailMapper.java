package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.reclamationDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class reclamationDetailMapper implements RowMapper<reclamationDetail> {
    public reclamationDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        reclamationDetail cmd = new reclamationDetail();
        cmd.setAmm_recl_recl(rs.getString(1));
        cmd.setLib_recl(rs.getString(2));
        cmd.setAmm_recl_date(rs.getDate(3));
        cmd.setNb_reclam(rs.getString(4));
        cmd.setAnnul_reclam(rs.getInt(5));
        cmd.setAmm_date_nbre_reclam(rs.getDate(6));


        return cmd;
    }
}
