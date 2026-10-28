package com.opham.prepa.mapper.Expedition;
import com.opham.prepa.model.Expedition.attrib_axe;
import com.opham.prepa.model.Expedition.axe_list;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class axe_ListMapper implements RowMapper<axe_list>{
    public axe_list mapRow(ResultSet rs, int rowNum) throws SQLException {
        axe_list cmd = new axe_list();
        cmd.setAdraxe(rs.getString(1));

        return cmd;
    }
}
