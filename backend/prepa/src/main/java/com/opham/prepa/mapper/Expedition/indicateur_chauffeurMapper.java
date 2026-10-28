package com.opham.prepa.mapper.Expedition;
import com.opham.prepa.model.Expedition.indicateur_chauffeur;
import com.opham.prepa.model.Expedition.indicateur_livraison;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class indicateur_chauffeurMapper implements RowMapper<indicateur_chauffeur>{
    public indicateur_chauffeur mapRow(ResultSet rs, int rowNum) throws SQLException {
        indicateur_chauffeur cmd = new indicateur_chauffeur();
        cmd.setXchauff(rs.getString(1));
        cmd.setXnum_voit(rs.getString(2));
        cmd.setXtype(rs.getString(3));
        cmd.setXdispo(rs.getInt(4));
        cmd.setTourne_1(rs.getString(5));
        cmd.setTourne_1_duree(rs.getInt(6));
        cmd.setTourne_2(rs.getString(7));
        cmd.setTourne_2_duree(rs.getInt(8));
        cmd.setTourne_3(rs.getString(9));
        cmd.setTourne_3_duree(rs.getInt(10));

        
        return cmd;
    }
}
