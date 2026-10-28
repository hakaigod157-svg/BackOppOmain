package com.opham.prepa.mapper.Expedition;


import com.opham.prepa.model.Expedition.ListCmdLivAsuppr;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListCmdLivAsupprMapper implements RowMapper<ListCmdLivAsuppr> {
    @Override
    public ListCmdLivAsuppr mapRow(ResultSet rs, int rowNum) throws SQLException {
        ListCmdLivAsuppr D = new ListCmdLivAsuppr();
        D.setFacture(rs.getString(1));
        D.setCommande(rs.getString(2));
        return D;
    }
}