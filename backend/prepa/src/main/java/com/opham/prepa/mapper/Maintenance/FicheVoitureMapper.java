package com.opham.prepa.mapper.Maintenance;


import com.opham.prepa.model.Maintenance.FicheVoiture;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FicheVoitureMapper implements RowMapper<FicheVoiture> {
    @Override
    public FicheVoiture mapRow(ResultSet rs, int rowNum) throws SQLException {
        FicheVoiture fv = new FicheVoiture();
        fv.setIMMATRICULATION(rs.getString("IMMATRICULATION"));
        fv.setMARQUE(rs.getString("MARQUE"));
        fv.setMODELE(rs.getString("MODELE"));
        fv.setANNEE(rs.getInt(  "ANNEE"));
        fv.setKILOMETRE(rs.getInt("KILOMETRE"));
        fv.setETAT(rs.getInt("ETAT"));
        return fv;
    }
}
