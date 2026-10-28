package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.Trans_Detail_Lv;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Trans_Detail_LvMapper implements RowMapper<Trans_Detail_Lv>{
    public Trans_Detail_Lv mapRow(ResultSet rs, int rowNum) throws SQLException {
        Trans_Detail_Lv cmd = new Trans_Detail_Lv();
        cmd.setTrans_l_liv(rs.getString(1));
        cmd.setNb_cde(rs.getInt(2));
        cmd.setNb_colis(rs.getInt(3));

        return cmd;
    }
}
