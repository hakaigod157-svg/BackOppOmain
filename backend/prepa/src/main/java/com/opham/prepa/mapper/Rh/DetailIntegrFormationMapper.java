package com.opham.prepa.mapper.Rh;

import com.opham.prepa.model.Rh.DetailIntegrFormation;
import com.opham.prepa.model.Rh.DetailIntegration;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailIntegrFormationMapper implements RowMapper<DetailIntegrFormation>{
    public DetailIntegrFormation mapRow(ResultSet rs, int rowNum) throws SQLException {
        DetailIntegrFormation cmd = new DetailIntegrFormation();

        cmd.setForm_code(rs.getString(1));
        cmd.setType_form_libelle(rs.getString(2));
        cmd.setForm_dateprevue(rs.getDate(3));
        cmd.setForm_datereel(rs.getDate(4));

        return cmd;
    }
}
