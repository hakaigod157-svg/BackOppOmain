package com.opham.prepa.mapper.Catalogue;

import org.springframework.jdbc.core.RowMapper;
import com.opham.prepa.model.Catalogue.catalogue;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CatalogueMapper implements RowMapper<catalogue>{
    public catalogue mapRow(ResultSet rs, int rowNum) throws SQLException {
        catalogue cmd = new catalogue();
        cmd.setCode_client(rs.getString(1));
        cmd.setNom_client(rs.getString(2));
        cmd.setCategorie(rs.getString(3));
        cmd.setTarif(rs.getString(4));
        cmd.setMois(rs.getString(5));
        cmd.setAnnee(rs.getString(6));
        cmd.setCc(rs.getString(7));
        return cmd;
    }
}
