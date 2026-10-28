package com.opham.prepa.mapper.Rh;


import com.opham.prepa.model.Rh.Offre;
import org.springframework.jdbc.core.RowMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OffreMapper implements RowMapper<Offre> {

    @Override
    public Offre mapRow(ResultSet rs, int rowNum) throws SQLException {
        Offre of = new Offre();
        of.setOffre_code(rs.getString("OFFRE_CODE"));
        of.setOffre_type(rs.getInt("CAB_TYPE"));
        of.setOffre_date(rs.getDate("OFFRE_DATE"));
        of.setOffre_entite(rs.getString("OFFRE_ENTITE"));
        of.setOffre_code_recr(rs.getString("OFFRE_CODE_RECR"));
        try (InputStream is = rs.getBinaryStream("OFFRE_PIECE")) {
            if (is != null) {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] data = new byte[16384]; // plus grand buffer
                int bytesRead;
                while ((bytesRead = is.read(data)) != -1) {
                    buffer.write(data, 0, bytesRead);
                }
                buffer.flush();
                byte[] cvBytes = buffer.toByteArray();

                if (cvBytes.length > 0) {
                    of.setOffre_piece(cvBytes);
                   /* System.out.println(" Piece trouvée : " + cvBytes.length + " octets"); */
                } else {
                   /* System.out.println("Piece vide"); */
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return of;
    }
}
