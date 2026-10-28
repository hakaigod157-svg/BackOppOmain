package com.opham.prepa.mapper.BusinessIntelligence;

import com.opham.prepa.model.BusinessIntelligence.list_ca_annuel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class list_ca_annuelMapper implements RowMapper<list_ca_annuel>{
    public list_ca_annuel mapRow(ResultSet rs, int rowNum) throws SQLException {
        list_ca_annuel cmd = new list_ca_annuel();
        cmd.setArticle(rs.getString(1));
        cmd.setAnnee(rs.getInt(2));
        cmd.setCa(rs.getDouble(3));
        return cmd;
    }
}
