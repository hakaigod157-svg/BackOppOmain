package com.opham.prepa.mapper.Amm;
import com.opham.prepa.model.Amm.motifDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class motifDetailMapper implements RowMapper<motifDetail>{
    public motifDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        motifDetail cmd = new motifDetail();
        cmd.setAmm_ds_code(rs.getString(1));
        cmd.setAmm_respo(rs.getString(2));
        cmd.setAmm_type(rs.getString(3));
        cmd.setAmm_decision(rs.getInt(4));
        cmd.setAmm_classification(rs.getString(5));
        cmd.setAmm_date_decision(rs.getDate(6));
        cmd.setAmm_motif_decision(rs.getString(7));

        return cmd;
    }
}
