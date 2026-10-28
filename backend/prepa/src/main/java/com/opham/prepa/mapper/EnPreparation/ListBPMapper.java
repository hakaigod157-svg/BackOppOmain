package com.opham.prepa.mapper.EnPreparation;

import com.opham.prepa.model.EnPreparation.ListLigneBP;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListBPMapper implements RowMapper<ListLigneBP> {
    @Override
    public ListLigneBP mapRow(ResultSet rs, int rowNum) throws SQLException {
        ListLigneBP cmd = new ListLigneBP();
        cmd.setArticle(rs.getString("article"));
        cmd.setLibelle(rs.getString("libelle"));
        cmd.setLot(rs.getString("lot"));
        cmd.setDatePer(rs.getDate("datePer"));
        cmd.setEmpl(rs.getString("empl"));
        cmd.setQte(rs.getInt("QTE"));

        return cmd;
    }
}
