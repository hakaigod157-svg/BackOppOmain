package com.opham.prepa.mapper.Maintenance;

import com.opham.prepa.model.Maintenance.DemandeMaint;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DemandeMaintenanceMapper implements RowMapper<DemandeMaint> {

    @Override
    public DemandeMaint mapRow(ResultSet rs, int rowNum) throws SQLException {
        DemandeMaint DEMANDE = new DemandeMaint();
        DEMANDE.setDEMANDE_CODE(rs.getString("DEMANDE_CODE"));
        DEMANDE.setDEMANDE_DATE(rs.getDate("DEMANDE_DATE"));
        DEMANDE.setDEMANDE_VOITURE(rs.getString("DEMANDE_VOITURE"));
        DEMANDE.setDEMANDE_MARQUE(rs.getString("DEMANDE_MARQUE"));
        DEMANDE.setDEMANDE_AUTEUR(rs.getString("DEMANDE_AUTEUR"));
        DEMANDE.setDEMANDE_TYPE(rs.getString("DEMANDE_TYPE"));
        DEMANDE.setDEMANDE_DESCR(rs.getString("DEMANDE_DESCR"));
        DEMANDE.setDEMANDE_ETAT(rs.getInt("DEMANDE_ETAT"));
        DEMANDE.setDEMANDE_USER_CRE(rs.getInt("DEMANDE_USER_CRE"));
        DEMANDE.setDEMANDE_USER_MODIF(rs.getInt("DEMANDE_USER_MODIF"));
        DEMANDE.setDEMANDE_DATE_CRE(rs.getTimestamp("DEMANDE_DATE_CRE"));
        DEMANDE.setDEMANDE_DATE_MODIF(rs.getTimestamp("DEMANDE_DATE_MODIF"));
        return DEMANDE;
    }
}