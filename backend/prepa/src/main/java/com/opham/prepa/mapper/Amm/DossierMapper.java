package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.dossier;
import com.opham.prepa.model.Amm.fabricant;
import org.springframework.jdbc.core.RowMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DossierMapper implements RowMapper<dossier>{
    public dossier mapRow(ResultSet rs, int rowNum) throws SQLException {
        dossier cmd = new dossier();
        cmd.setSeq(rs.getInt(1));
        cmd.setAmm_ds_code(rs.getString(2));
        cmd.setLigne_code_article(rs.getString(3));
        cmd.setLigne_libelle_article(rs.getString(4));
        cmd.setAmm_type(rs.getString(5));
        cmd.setAmm_classification(rs.getString(6));
        cmd.setAmm_decision(rs.getString(7));
        cmd.setFab_libelle(rs.getString(8));
        cmd.setLigne_titulaire(rs.getString(9));
        cmd.setAmm_respo(rs.getString(10));
        cmd.setAmm_etape(rs.getString(11));
        cmd.setBouton_decision(rs.getInt(12));
        cmd.setAmm_depuis(rs.getInt(13));
        cmd.setDate_depuis(rs.getDate(14));
        cmd.setEstimation(rs.getString(15));
        cmd.setBouton_reclam(rs.getInt(16));
        cmd.setDatecre(rs.getDate(17));
        cmd.setLigne_image(rs.getInt(18));
        cmd.setBouton_non_depos(rs.getInt(19));
        // Lecture du flux binaire de la colonne DEMANDE_FICHE
   /*    try (InputStream is = rs.getBinaryStream(18)) {
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
                    cmd.setLigne_image(ficheBytes);
                    System.out.println("✅ Fiche trouvée : " + ficheBytes.length + " octets");
                } else {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } */

        return cmd;
    }
}
