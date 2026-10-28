package com.opham.prepa.mapper.Rh;

import com.opham.prepa.model.Rh.DetailIntegration;
import com.opham.prepa.model.Rh.DetailMatDispo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailMatDispoMapper implements RowMapper<DetailMatDispo>{
    public DetailMatDispo mapRow(ResultSet rs, int rowNum) throws SQLException {
        DetailMatDispo cmd = new DetailMatDispo();

        cmd.setMise_dispo_matr(rs.getInt(1));
        cmd.setMise_dispo_cat(rs.getString(2));
        cmd.setMise_dispo_materiel(rs.getString(3));
        cmd.setMise_dispo_date_prevue(rs.getDate(4));
        cmd.setMise_dispo_date_effect(rs.getDate(5));

        return cmd;
    }
}
