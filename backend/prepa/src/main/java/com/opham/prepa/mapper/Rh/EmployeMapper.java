package com.opham.prepa.mapper.Rh;

import com.opham.prepa.model.Rh.Employe;
import com.opham.prepa.model.Rh.paramsMiseAdispo;
import org.springframework.jdbc.core.RowMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeMapper implements RowMapper<Employe>{
    public Employe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employe cmd = new Employe();
        cmd.setEmpl_code(rs.getString(1));
        cmd.setEmpl_matricule(rs.getString(2));
        cmd.setEmpl_nom(rs.getString(3));
        cmd.setEmpl_prenom(rs.getString(4));
        cmd.setEmpl_date_naiss(rs.getDate(5));
        cmd.setEmpl_code_cv(rs.getString(6));
        cmd.setEmpl_dep(rs.getString(7));
        cmd.setLib_dep(rs.getString(8));
        cmd.setEmpl_poste(rs.getString(9));
        cmd.setPoste_libelle(rs.getString(10));
        cmd.setEmpl_prise_fonction(rs.getDate(11));
        cmd.setEmpl_contrat(rs.getString(12));
        cmd.setEmpl_code_recr(rs.getString(13));
        cmd.setEmpl_date_fin_essai1(rs.getDate(14));
        cmd.setEmpl_date_fin_essai2(rs.getDate(15));
        cmd.setEmpl_etat(rs.getInt(16));

        return cmd;
    }
}
