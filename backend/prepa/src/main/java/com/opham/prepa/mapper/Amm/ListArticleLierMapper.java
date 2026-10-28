package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.ListArticleLier;
import com.opham.prepa.model.Amm.list_amm_dossier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListArticleLierMapper implements RowMapper<ListArticleLier>{
    public ListArticleLier mapRow(ResultSet rs, int rowNum) throws SQLException {
        ListArticleLier cmd = new ListArticleLier();
        cmd.setArcode(rs.getString(1));
        cmd.setArlib(rs.getString(2));
        cmd.setArfo(rs.getString(3));

        return cmd;
    }

}
