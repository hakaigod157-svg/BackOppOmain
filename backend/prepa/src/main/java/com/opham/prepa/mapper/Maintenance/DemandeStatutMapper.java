package com.opham.prepa.mapper.Maintenance;

import com.opham.prepa.model.Maintenance.DemandeMaint;
import com.opham.prepa.model.Maintenance.DemandeStatut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DemandeStatutMapper implements RowMapper<DemandeStatut> {
    public DemandeStatut mapRow(ResultSet rs, int rowNum) throws SQLException {
        DemandeStatut demandStatut = new DemandeStatut();
       // demandStatut.setDEMANDE_CODE(rs.getString("DEMANDE_CODE"));
        demandStatut.setSTATUT(rs.getString("STATUT"));
        demandStatut.setETAPE_EN_COURS(rs.getString("ETAPE_EN_COURS"));
        demandStatut.setLISTE(rs.getString("LISTE"));
        demandStatut.setDUREE(rs.getInt("DUREE"));
        return demandStatut;
    }
}
