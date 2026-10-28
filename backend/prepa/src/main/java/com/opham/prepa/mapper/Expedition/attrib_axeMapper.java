package com.opham.prepa.mapper.Expedition;
import com.opham.prepa.model.Expedition.attrib_axe;
import com.opham.prepa.model.Expedition.params_liv;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class attrib_axeMapper implements RowMapper<attrib_axe>{
    public attrib_axe mapRow(ResultSet rs, int rowNum) throws SQLException {
        attrib_axe cmd = new attrib_axe();
        cmd.setSeq(rs.getInt(1));
        cmd.setCode_client(rs.getString(2));
        cmd.setNom_client(rs.getString(3));
        cmd.setNom(rs.getString(4));
        cmd.setAdresse_1(rs.getString(5));
        cmd.setAdresse_2(rs.getString(6));
        cmd.setVille(rs.getString(7));
        cmd.setAxe(rs.getString(8));
        cmd.setType(rs.getString(9));
        cmd.setMod_liv(rs.getString(10));
        cmd.setBtn(rs.getInt(11));
        cmd.setCode_postal(rs.getString(12));
        cmd.setClsa(rs.getString(13));
        cmd.setDate_modif_modeliv(rs.getTimestamp(14));
        cmd.setUser_modif_modelive(rs.getString(15));
        cmd.setDate_modif_axe(rs.getTimestamp(16));
        cmd.setUser_modif_axe(rs.getString(17));

        return cmd;
    }
}
