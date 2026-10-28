package com.opham.prepa.mapper.Apreparer;

import com.opham.prepa.model.Apreparer.pp_list;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class pp_listMapper implements RowMapper<pp_list> {
    public pp_list mapRow(ResultSet rs, int rowNum) throws SQLException {
        pp_list cmd = new pp_list();
        cmd.setPpL_code(rs.getString(1));
        cmd.setCode_client(rs.getString(2));
        cmd.setNom_client(rs.getString(3));
        cmd.setFp_date(rs.getDate(4));
        cmd.setPpL_facture(rs.getString(5));
        cmd.setPpL_FpCode(rs.getString(6));
        cmd.setPp_imprime(rs.getInt(7));
        return cmd;
    }
}
