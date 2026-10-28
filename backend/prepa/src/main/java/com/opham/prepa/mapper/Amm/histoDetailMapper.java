package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.articleDetail;
import com.opham.prepa.model.Amm.histoDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class histoDetailMapper implements RowMapper<histoDetail>{
    public histoDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        histoDetail cmd = new histoDetail();
        cmd.setAmm_histo_seq(rs.getInt(1));
        cmd.setAmm_histo_date(rs.getDate(2));
        cmd.setAmm_histo_comment(rs.getString(3));

        return cmd;
    }

}
