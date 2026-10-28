package com.opham.prepa.mapper.BusinessIntelligence;
import com.opham.prepa.model.Expedition.attrib_axe;
import org.springframework.jdbc.core.RowMapper;
import com.opham.prepa.model.BusinessIntelligence.list_view;

import java.sql.ResultSet;
import java.sql.SQLException;

public class list_viewMapper implements RowMapper<list_view>{
    public list_view mapRow(ResultSet rs, int rowNum) throws SQLException {
        list_view cmd = new list_view();
        cmd.setName(rs.getString(1));
        return cmd;
    }

}
