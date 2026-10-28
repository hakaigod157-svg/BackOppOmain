package com.opham.prepa.mapper.Rh;


import com.opham.prepa.model.Rh.DepartementPoste;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class DepartementPosteMapper implements RowMapper<DepartementPoste> {


    @Override
    public DepartementPoste mapRow(ResultSet rs, int rowNum) throws SQLException {
        DepartementPoste cmd = new DepartementPoste();
        cmd.setCode_departement(rs.getString(1));
        cmd.setLib_departement(rs.getString(2));
        cmd.setCode_poste(rs.getString(3));
        cmd.setPoste_libelle(rs.getString(4));
        return cmd;
    }
}
