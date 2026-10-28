package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Userinsert;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserinsertMapper implements RowMapper<Userinsert> {
    @Override
    public Userinsert mapRow(ResultSet rs, int rowNum) throws SQLException {
        Userinsert U = new Userinsert();
        U.setNcpsuserusercode(rs.getString("NCPS_USER_USERCODE"));
        U.setNcpsuserncpscode(rs.getString("NCPS_USER_NCPSCODE"));

        return U;
    }
}
