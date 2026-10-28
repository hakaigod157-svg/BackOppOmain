package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.dossierAfournir;
import com.opham.prepa.model.Amm.etapeDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class dossierAfournirMapper implements RowMapper<dossierAfournir>{
    public dossierAfournir mapRow(ResultSet rs, int rowNum) throws SQLException {
        dossierAfournir cmd = new dossierAfournir();
        cmd.setLigne_dossier_code(rs.getString(1));
        cmd.setLigne_etat(rs.getInt(2));
        cmd.setLigne_date(rs.getDate(3));
        cmd.setDossier_lib(rs.getString(4));

        return cmd;
    }
}
