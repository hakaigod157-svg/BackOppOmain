package com.opham.prepa.mapper.EnPreparation;

import com.opham.prepa.model.EnPreparation.EnCoursPrepBP;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoEncoursBPMapper implements RowMapper<EnCoursPrepBP> {
    @Override
    public EnCoursPrepBP mapRow(ResultSet rs, int rowNum) throws SQLException {
        EnCoursPrepBP cmd = new EnCoursPrepBP();
        cmd.setCodeBP(rs.getString("BPCODE"));
        cmd.setEtat_BP(rs.getString("ETAT_BP"));
        cmd.setEmpl(rs.getString("xEmp"));
        return cmd;
    }
}
