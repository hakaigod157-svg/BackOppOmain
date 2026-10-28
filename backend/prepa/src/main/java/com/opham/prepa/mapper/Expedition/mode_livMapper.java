package com.opham.prepa.mapper.Expedition;
import com.opham.prepa.model.Expedition.axe_list;
import com.opham.prepa.model.Expedition.mode_liv;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class mode_livMapper implements RowMapper<mode_liv>{
    public mode_liv mapRow(ResultSet rs, int rowNum) throws SQLException {
        mode_liv cmd = new mode_liv();
        cmd.setAdrmodelivr(rs.getString(1));

        return cmd;
    }

}
