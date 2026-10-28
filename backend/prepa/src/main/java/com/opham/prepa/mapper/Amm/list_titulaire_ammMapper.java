package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.list_amm_dossier;
import com.opham.prepa.model.Amm.list_titulaire_amm;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class list_titulaire_ammMapper implements RowMapper<list_titulaire_amm> {
    public list_titulaire_amm mapRow(ResultSet rs, int rowNum) throws SQLException {
        list_titulaire_amm cmd = new list_titulaire_amm();
        cmd.setFocode(rs.getString(1));
        cmd.setFonom(rs.getString(2));
        cmd.setFonom1(rs.getString(3));

        return cmd;
    }

}
