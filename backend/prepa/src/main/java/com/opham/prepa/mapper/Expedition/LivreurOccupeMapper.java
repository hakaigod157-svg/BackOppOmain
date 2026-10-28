package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.DureeLV;
import com.opham.prepa.model.Utils.LoginPlus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LivreurOccupeMapper implements RowMapper<LoginPlus> {
    @Override
    public LoginPlus mapRow(ResultSet rs, int rowNum) throws SQLException {
        LoginPlus D = new LoginPlus();
        D.setXUser(rs.getString(1));
        return D;
    }
}