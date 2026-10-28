package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.AllDossierList;
import com.opham.prepa.model.Amm.dossierAfournir;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AllDossierListMapper implements RowMapper<AllDossierList>{
    public AllDossierList mapRow(ResultSet rs, int rowNum) throws SQLException {
        AllDossierList cmd = new AllDossierList();
        cmd.setDossier_code(rs.getString(1));
        cmd.setDossier_lib(rs.getString(2));

        return cmd;
    }
}
