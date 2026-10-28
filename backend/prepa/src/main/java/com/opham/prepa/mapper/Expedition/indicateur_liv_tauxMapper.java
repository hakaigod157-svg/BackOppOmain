package com.opham.prepa.mapper.Expedition;
import com.opham.prepa.model.Expedition.indicateur_liv_detail;
import com.opham.prepa.model.Expedition.indicateur_liv_taux;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class indicateur_liv_tauxMapper implements RowMapper<indicateur_liv_taux>{
    public indicateur_liv_taux mapRow(ResultSet rs, int rowNum) throws SQLException {
        indicateur_liv_taux cmd = new indicateur_liv_taux();
        cmd.setObj(rs.getInt(1));
        return cmd;
    }
}
