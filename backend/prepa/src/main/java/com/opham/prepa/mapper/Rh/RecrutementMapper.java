package com.opham.prepa.mapper.Rh;

import com.opham.prepa.model.Rh.Recrutement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RecrutementMapper implements RowMapper<Recrutement> {

    @Override
    public Recrutement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Recrutement rct = new Recrutement();
        rct.setRecru_code(rs.getString("RECRU_CODE"));
        rct.setRecru_date(rs.getDate("RECRU_DATE"));
        rct.setRecru_demande(rs.getString("RECRU_DEMANDE"));
        rct.setDemande_date(rs.getDate("DEMANDE_DATE"));
        rct.setRecru_motif(rs.getString("RECRU_MOTIF"));
        rct.setRecru_contrat(rs.getString("RECRU_CONTRAT"));
        rct.setRecru_departement(rs.getString("RECRU_DEPARTEMENT"));
        rct.setRecru_poste(rs.getString("RECRU_POSTE"));
        rct.setRecru_effectif(rs.getInt("RECRU_EFFECTIF"));
        rct.setEtat(rs.getString("ETAT"));

        return rct;
    }
}
