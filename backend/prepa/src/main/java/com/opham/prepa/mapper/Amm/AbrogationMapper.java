package com.opham.prepa.mapper.Amm;


import com.opham.prepa.model.Amm.Abrogation;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbrogationMapper implements RowMapper<Abrogation> {

        public Abrogation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Abrogation cmd = new Abrogation();
            cmd.setAbrog_code(rs.getString(1));
            cmd.setAbrog_libelle(rs.getString(2));
            cmd.setAbrog_titulaire(rs.getString(3));
            cmd.setAbrog_amm_interprete(rs.getString(4));
            cmd.setAbroge_amm(rs.getString(5));
            cmd.setAbrog_ammdate(rs.getDate(6));
            cmd.setAbrog_expire(rs.getInt(7));
            cmd.setAbrog_expirationdate(rs.getDate(8));
            cmd.setAbrog_bouton(rs.getInt(9));

            return cmd;
        }
}
