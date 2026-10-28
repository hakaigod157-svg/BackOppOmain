package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.allListSpe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class allListSpeMapper implements RowMapper<allListSpe>{
    public allListSpe mapRow(ResultSet rs, int rowNum) throws SQLException {
        allListSpe cmd = new allListSpe();
        cmd.setType_dossier_spe(rs.getString(1));
        cmd.setCode_dossier_spe(rs.getString(2));
        cmd.setDossier_lib_spe(rs.getString(3));
        cmd.setClassification_spe(rs.getString(4));

        return cmd;
    }
}
