package com.opham.prepa.mapper.Rh;

import com.opham.prepa.model.Rh.CV;
import com.opham.prepa.model.Rh.DemandePers;
import org.springframework.jdbc.core.RowMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class CvMapper implements RowMapper<CV>{

    @Override
    public CV mapRow(ResultSet rs, int rowNum) throws SQLException {
        CV cv = new CV();
        cv.setCv_code(rs.getString("CV_CODE"));
        cv.setCv_nom(rs.getString("CV_NOM"));
        cv.setCv_prenom(rs.getString("CV_PRENOM"));
        cv.setCv_contact1(rs.getString("CV_CONTACT1"));
        cv.setCv_contact_2(rs.getString("CV_CONTACT_2"));
        cv.setCv_email(rs.getString("CV_EMAIL"));
        cv.setCv_date(rs.getDate("CV_DATE"));

        cv.setCv_ligne_selectionne(rs.getInt("CV_LIGNE_SELECTIONNE"));
        cv.setCv_ligne_test_entretien(rs.getInt("CV_LIGNE_TEST_ENTRETIEN"));
        cv.setCv_ligne_date_test(rs.getTimestamp("CV_LIGNE_DATE_TEST"));
        cv.setCv_ligne_date_entretien(rs.getTimestamp("CV_LIGNE_DATE_ENTRETIEN"));

        cv.setCv_ligne_date_select(rs.getDate("CV_LIGNE_DATE_SELECT"));

        cv.setCv_ligne_validation(rs.getInt("CV_LIGNE_VALIDATION"));
        cv.setCv_ligne_validation_date(rs.getDate("CV_LIGNE_VALIDATION_DATE"));



        try (InputStream is = rs.getBinaryStream("CV_CV");
             InputStream is1 = rs.getBinaryStream("CV_MOTIVATION") ) {
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
                    cv.setCv_cv(cvBytes);
                  /*  System.out.println(" CV trouvée : " + cvBytes.length + " octets"); */
                } else {
                    System.out.println("CV vide");
                }
            }
            if(is1 != null) {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] data = new byte[16384]; // plus grand buffer
                int bytesRead;
                while ((bytesRead = is1.read(data)) != -1) {
                    buffer.write(data, 0, bytesRead);
                }
                buffer.flush();
                byte[] cvBytes = buffer.toByteArray();

                if (cvBytes.length > 0) {
                    cv.setCv_motivation(cvBytes);
                   /* System.out.println(" LM trouvée : " + cvBytes.length + " octets"); */
                } else {
                    System.out.println("LM vide");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        cv.setCv_ligne_date_ajout(rs.getDate("CV_LIGNE_DATE_AJOUT"));
        cv.setCv_ligne_date_proposition(rs.getDate("CV_LIGNE_DATE_PROPOSITION"));

        return cv;
    }
}
