package com.opham.prepa.mapper.Capa;

import com.opham.prepa.model.Capa.action;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class actionMapper implements RowMapper<action>{
    public action mapRow(ResultSet rs, int rowNum) throws SQLException{
        action act = new action();

        int ActionAnalyse = rs.getInt("ACTION_ANALYSE");
        act.setActionAnalyse(rs.wasNull() ? 0 : ActionAnalyse);

        return act;
    }
}
