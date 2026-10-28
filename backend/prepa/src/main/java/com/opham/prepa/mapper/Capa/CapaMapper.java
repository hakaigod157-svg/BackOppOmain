package com.opham.prepa.mapper.Capa;

import com.opham.prepa.model.Capa.Capa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class CapaMapper implements RowMapper<Capa> {
    public Capa mapRow(ResultSet rs, int rowNum) throws SQLException{
        Capa cpa = new Capa();

// Vérifier si la valeur est nulle avant d'assigner à l'objet Capa
        cpa.setCode(rs.getString("NCPS_CODE") != null ? rs.getString("NCPS_CODE") : "");

        cpa.setDate(rs.getDate("NCPS_DATE") != null ? rs.getDate("NCPS_DATE") : new java.sql.Date(System.currentTimeMillis())); // ou toute autre valeur par défaut

        cpa.setModeDetect(rs.getString("NCPS_MODEDETECT") != null ? rs.getString("NCPS_MODEDETECT") : "");

        cpa.setEmetteur(rs.getString("NCPS_EMETTEUR") != null ? rs.getString("NCPS_EMETTEUR") : "");

        cpa.setLibelleDep(rs.getString("LIBELLE_DEP") != null ? rs.getString("LIBELLE_DEP") : "");
        cpa.setLibelleType(rs.getString("LIBELE_TYPE") != null ? rs.getString("LIBELE_TYPE") : "");

        cpa.setDepartement(rs.getString("NCPS_DEPARTEMENT") != null ? rs.getString("NCPS_DEPARTEMENT") : "");

        int etatValue = rs.getInt("NCPS_ETAT");
        cpa.setEtat(rs.wasNull() ? 0 : etatValue);

        int valideValue = rs.getInt("NCPS_VALIDE");
        cpa.setValide(rs.wasNull() ? 0 : valideValue);

        cpa.setLibellemodedetect(rs.getString("LIBELLE_MODE_DETECT") != null ? rs.getString("LIBELLE_MODE_DETECT") : "");

        cpa.setProcdetect(rs.getString("NCPS_PROCESSUS_DETECT") != null ? rs.getString("NCPS_PROCESSUS_DETECT") : "");

        cpa.setLibprocdetect(rs.getString("LIBELLE_PROCESSUS_DETECT") != null ? rs.getString("LIBELLE_PROCESSUS_DETECT") : "");

// Vérifier si la valeur est nulle pour les entiers
        int causeValidValue = rs.getInt("CAUSE_VALID");
        cpa.setCausevalid(rs.wasNull() ? 0 : causeValidValue);

        int causeAtteValue = rs.getInt("CAUSE_ATTE");
        cpa.setCauseatte(rs.wasNull() ? 0 : causeAtteValue);

        int atteValidValue = rs.getInt("ATTE_VALID");
        cpa.setAttevalid(rs.wasNull() ? 0 : atteValidValue);

        int atteMiseAppliValue = rs.getInt("ATTE_MISE_APPLI");
        cpa.setAttemiseappli(rs.wasNull() ? 0 : atteMiseAppliValue);

        int atteAppliqueValue = rs.getInt("APPLIQUE");
        cpa.setApplique(rs.wasNull() ? 0 : atteAppliqueValue);

        String description = rs.getString("NCPS_DESCRIPTION");
        cpa.setDescription(description != null ? description : "");

        cpa.setType(rs.getString("NCPS_TYPE") != null ? rs.getString("NCPS_TYPE") : "");

        int nbCauseRacine = rs.getInt("NB_CAUSE_RACINE");
        cpa.setNbCauseRacine(rs.wasNull() ? 0 : nbCauseRacine);

        int nbAction = rs.getInt("NBACTION");
        cpa.setNbAction(rs.wasNull() ? 0 : nbAction);


        //code de mis a jour :
        cpa.setNcpsSeq(rs.getInt("NCPS_SEQ"));
        cpa.setNcpsCode(rs.getString("NCPS_CODE"));
        cpa.setNcpsDate(rs.getDate("NCPS_DATE"));
        cpa.setNcpsDescription(rs.getString("NCPS_DESCRIPTION"));
        cpa.setNcpsEmetteur(rs.getString("NCPS_EMETTEUR"));
        cpa.setNcpsDepartement(rs.getString("NCPS_DEPARTEMENT"));
        cpa.setNcpsType(rs.getString("NCPS_TYPE"));
        cpa.setNcpsModeDetect(rs.getString("NCPS_MODEDETECT"));
        cpa.setNcpsProcessusDetect(rs.getString("NCPS_PROCESSUS_DETECT"));
        cpa.setNcpsEtat(rs.getInt("NCPS_ETAT"));
        cpa.setNcpsValide(rs.getInt("NCPS_VALIDE"));

        return cpa;
    }
}
