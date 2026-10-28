package com.opham.prepa.mapper.Rh;

import com.opham.prepa.model.Rh.Categorie;
import com.opham.prepa.model.Rh.DetailMatDispo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategorieMapper implements RowMapper<Categorie>{
    public Categorie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Categorie cmd = new Categorie();

        cmd.setCode_cat(rs.getString(1));
        cmd.setCadre_cat(rs.getInt(2));

        return cmd;
    }
}
