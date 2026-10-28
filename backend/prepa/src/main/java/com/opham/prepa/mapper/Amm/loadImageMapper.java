package com.opham.prepa.mapper.Amm;
import com.opham.prepa.model.Amm.loadImage;
import org.springframework.jdbc.core.RowMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loadImageMapper implements RowMapper<loadImage>{
    public loadImage mapRow(ResultSet rs, int rowNum) throws SQLException {
        loadImage cmd = new loadImage();
        // Lecture du flux binaire de la colonne DEMANDE_FICHE
       try (InputStream is = rs.getBinaryStream(1)) {
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
                } else {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmd;
    }
}
