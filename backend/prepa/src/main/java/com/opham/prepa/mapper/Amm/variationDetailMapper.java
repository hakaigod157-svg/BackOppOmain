package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.variationDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class variationDetailMapper implements RowMapper<variationDetail>{
    public variationDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        variationDetail cmd = new variationDetail();
        cmd.setVar_libelle(rs.getString(1));
        cmd.setAmm_ref_variation(rs.getString(2));

        return cmd;
    }

}
