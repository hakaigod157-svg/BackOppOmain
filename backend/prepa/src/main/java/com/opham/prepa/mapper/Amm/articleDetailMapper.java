package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.articleDetail;
import com.opham.prepa.model.Amm.dossier;
import org.springframework.jdbc.core.RowMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class articleDetailMapper implements RowMapper<articleDetail>{
    public articleDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        articleDetail cmd = new articleDetail();
        cmd.setSeq(rs.getInt(1));
        cmd.setCode_article(rs.getString(2));
        cmd.setLibelle_article(rs.getString(3));
        cmd.setTitulaire(rs.getString(4));
        cmd.setFabricant(rs.getString(5));
        cmd.setNum_amm(rs.getString(6));
        cmd.setDate_amm(rs.getDate(7));

        // Lecture du flux binaire de la colonne DEMANDE_FICHE
        try (InputStream is = rs.getBinaryStream(8)) {
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
        }

        return cmd;
    }
}
