package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.Midac;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MidacMapper implements RowMapper<Midac> {
    public Midac mapRow(ResultSet rs, int rowNum) throws SQLException {
        Midac cmd = new Midac();
        cmd.setCode_prod(rs.getString(1));
        cmd.setCode_opham(rs.getString(2));
        cmd.setArlib(rs.getString(3));
        cmd.setNum_amm(rs.getString(4));
        cmd.setDate_amm(rs.getDate(5));
        cmd.setAmm_fiche_article(rs.getString(6));
        cmd.setDate_amm_fiche_article(rs.getDate(7));
        cmd.setAmm_interprete(rs.getString(8));
        cmd.setNom_com(rs.getString(9));
        cmd.setCode_cip(rs.getString(10));
        cmd.setDci(rs.getString(11));
        cmd.setMarque(rs.getString(12));
        cmd.setPresentation(rs.getString(13));
        cmd.setFabriquant(rs.getString(14));
        cmd.setTitulaire(rs.getString(15));
        cmd.setCode_hs(rs.getString(16));
        cmd.setType_colis(rs.getString(17));
        cmd.setStatus(rs.getString(18));
        cmd.setLm_mois(rs.getInt(19));
        cmd.setLm_annee(rs.getInt(20));
        cmd.setAmm_gere_opham(rs.getInt(21));

        return cmd;
    }

}
