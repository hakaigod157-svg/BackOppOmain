package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.DureeLV;
import com.opham.prepa.model.Expedition.TestAvAddCmdLiv;
import com.opham.prepa.model.Expedition.params_liv;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestAvAddCmdLivMapper implements RowMapper<TestAvAddCmdLiv> {
    @Override
    public TestAvAddCmdLiv mapRow(ResultSet rs, int rowNum) throws SQLException {
        TestAvAddCmdLiv D = new TestAvAddCmdLiv();
        D.setIs_liv(rs.getInt(1));
        D.setAtte_liv(rs.getInt(2));
        D.setAxe(rs.getString(3));
        D.setIs_cmd(rs.getInt(4));
        D.setCmd(rs.getString(5));
        D.setModeliv(rs.getString(6));
        return D;
    }
}
