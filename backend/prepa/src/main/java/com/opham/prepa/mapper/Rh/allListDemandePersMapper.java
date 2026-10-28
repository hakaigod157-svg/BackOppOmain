package com.opham.prepa.mapper.Rh;

import com.opham.prepa.model.Rh.DemandePers;
import org.springframework.jdbc.core.RowMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class allListDemandePersMapper implements RowMapper<DemandePers> {

    @Override
    public DemandePers mapRow(ResultSet rs, int rowNum) throws SQLException {
        DemandePers cmd = new DemandePers();

        // Lecture des colonnes classiques
        cmd.setDemande_code(rs.getString("DEMANDE_CODE"));
        cmd.setDemande_date(rs.getDate("DEMANDE_DATE"));
        cmd.setDemande_prise_fonction(rs.getDate("DEMANDE_PRISE_FONCTION"));
        cmd.setDemande_departement(rs.getString("DEMANDE_DEPARTEMENT"));
        cmd.setDemande_motif(rs.getString("DEMANDE_MOTIF"));
        cmd.setDemande_contrat(rs.getString("DEMANDE_CONTRAT"));
        cmd.setDemande_poste(rs.getString("DEMANDE_POSTE"));
        cmd.setDemande_effectif(rs.getInt("DEMANDE_EFFECTIF"));
        cmd.setDemande_etat(rs.getInt("DEMANDE_ETAT"));

        // Lecture du flux binaire de la colonne DEMANDE_FICHE
        try (InputStream is = rs.getBinaryStream("DEMANDE_FICHE")) {
            if (is != null) {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] data = new byte[16384]; // plus grand buffer
                int bytesRead;
                while ((bytesRead = is.read(data)) != -1) {
                    buffer.write(data, 0, bytesRead);
                }
                buffer.flush();
                byte[] ficheBytes = buffer.toByteArray();
                if (ficheBytes.length > 0) {
                    cmd.setDemande_fiche(ficheBytes);
                    cmd.setDemande_fiche_base64(Base64.getEncoder().encodeToString(ficheBytes));
                    cmd.setFicheExists(true);
                   /* System.out.println(" Fiche trouvée : " + ficheBytes.length + " octets"); */
                } else {
                    cmd.setFicheExists(false);
                }
            } else {
                cmd.setFicheExists(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            cmd.setFicheExists(false);
        }


        return cmd;
    }
}
