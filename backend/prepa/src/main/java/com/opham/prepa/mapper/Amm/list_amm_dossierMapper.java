package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.list_amm_dossier;
import com.opham.prepa.model.Amm.listnumDS;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class list_amm_dossierMapper implements RowMapper<list_amm_dossier>{
    public list_amm_dossier mapRow(ResultSet rs, int rowNum) throws SQLException {
        list_amm_dossier cmd = new list_amm_dossier();
        cmd.setAmm_ds_code(rs.getString(1));
        cmd.setAmm_reclam(rs.getInt(2));
        cmd.setAmm_type(rs.getString(3));
        cmd.setAmm_ref_variation(rs.getString(4));


        return cmd;
    }

}
