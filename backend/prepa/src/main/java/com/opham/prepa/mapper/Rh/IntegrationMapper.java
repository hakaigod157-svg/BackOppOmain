package com.opham.prepa.mapper.Rh;
import com.opham.prepa.model.Rh.Employe;
import com.opham.prepa.model.Rh.Integration;
import org.springframework.jdbc.core.RowMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegrationMapper implements RowMapper<Integration>{
    public Integration mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integration cmd = new Integration();

        cmd.setEmpl_matricule(rs.getString(1));
        cmd.setEmpl_nom(rs.getString(2));
        cmd.setEmpl_prenom(rs.getString(3));
        cmd.setEmpl_date_naiss(rs.getDate(4));
        cmd.setEmpl_dep(rs.getString(5));
        cmd.setLib_dep(rs.getString(6));
        cmd.setEmpl_poste(rs.getString(7));
        cmd.setPoste_libelle(rs.getString(8));
        cmd.setEmpl_prise_fonction(rs.getDate(9));
        cmd.setEmpl_contrat(rs.getString(10));
        cmd.setEmpl_date_fin_essai1(rs.getDate(11));
        cmd.setEmpl_date_fin_essai2(rs.getDate(12));
        cmd.setIntegr_code(rs.getString(13));

        return cmd;
    }
}
