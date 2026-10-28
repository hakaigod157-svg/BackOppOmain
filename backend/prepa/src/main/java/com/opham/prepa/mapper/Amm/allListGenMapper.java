package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.allListGen;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class allListGenMapper implements RowMapper<allListGen>{
    public allListGen mapRow(ResultSet rs, int rowNum) throws SQLException {
        allListGen cmd = new allListGen();
        cmd.setType_dossier_gen(rs.getString(1));
        cmd.setCode_dossier_gen(rs.getString(2));
        cmd.setDossier_lib_gen(rs.getString(3));
        cmd.setClassification_gen(rs.getString(4));

        return cmd;
    }
}
