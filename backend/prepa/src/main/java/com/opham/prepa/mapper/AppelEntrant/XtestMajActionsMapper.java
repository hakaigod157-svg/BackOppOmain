package com.opham.prepa.mapper.AppelEntrant;

import com.opham.prepa.model.AppelEntrant.ActionFirstAppel;
import com.opham.prepa.model.AppelEntrant.XtestMajActions;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class XtestMajActionsMapper implements RowMapper<XtestMajActions> {
    public XtestMajActions mapRow(ResultSet rs, int rowNum) throws SQLException {
        XtestMajActions cmd = new XtestMajActions();
        cmd.setSeq(rs.getInt(1));

        return cmd;
    }
}
