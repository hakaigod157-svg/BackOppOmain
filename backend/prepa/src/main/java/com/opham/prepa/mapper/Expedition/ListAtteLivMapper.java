package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.DureeLV;
import com.opham.prepa.model.Expedition.ListAtteLiv;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ListAtteLivMapper implements RowMapper<ListAtteLiv> {
    @Override
    public ListAtteLiv mapRow(ResultSet rs, int rowNum) throws SQLException {
        ListAtteLiv D = new ListAtteLiv();
        D.setCmd(rs.getString(1));
        D.setCode_client(rs.getString(2));
        D.setNom_client(rs.getString(3));
        D.setDate_facture(rs.getDate(4));
        D.setNbColis(rs.getInt(5));
        D.setDate_cmd(rs.getDate(6));
        D.setAdrs(rs.getString(7));
        D.setAxe(rs.getString(8));
        D.setModeLiv(rs.getString(9));
        D.setPassBal(rs.getInt(10));
        D.setBonALiv(rs.getInt(11));
        D.setBlockPaye(rs.getBoolean(12));

        return D;
    }
}