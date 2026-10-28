package com.opham.prepa.mapper.Expedition;
import com.opham.prepa.model.Expedition.classification_list;
import com.opham.prepa.model.Expedition.detail_livraison;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class classification_listMapper implements RowMapper<classification_list> {
    public classification_list mapRow(ResultSet rs, int rowNum) throws SQLException {
        classification_list cmd = new classification_list();
        cmd.setClsa(rs.getString(1));

        return cmd;
    }
}
