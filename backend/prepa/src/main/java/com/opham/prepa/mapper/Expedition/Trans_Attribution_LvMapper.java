package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.Trans_Attribution_Lv;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Trans_Attribution_LvMapper implements RowMapper<Trans_Attribution_Lv> {
    public Trans_Attribution_Lv mapRow(ResultSet rs, int rowNum) throws SQLException {
        Trans_Attribution_Lv cmd = new Trans_Attribution_Lv();
        cmd.setTrans_code(rs.getString(1));
        cmd.setTrans_transport(rs.getString(2));
        cmd.setTrans_num_fact(rs.getString(3));
        cmd.setTrans_date_fact(rs.getDate(4));
        cmd.setTrans_montant(rs.getBigDecimal(5));
        cmd.setNb_lv(rs.getInt(6));
        cmd.setNb_cmd(rs.getInt(7));
        cmd.setNb_colis(rs.getString(8));
        cmd.setTrans_valide(rs.getInt(9));
        return cmd;
    }
}
