package com.opham.prepa.mapper.Rh;

import com.opham.prepa.model.Rh.paramsFormation;
import com.opham.prepa.model.Rh.paramsMiseAdispo;
import org.springframework.jdbc.core.RowMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class paramsFormationMapper implements RowMapper<paramsFormation>{
    public paramsFormation mapRow(ResultSet rs, int rowNum) throws SQLException {
        paramsFormation cmd = new paramsFormation();
        cmd.setDep(rs.getString(1));
        cmd.setNom_dep(rs.getString(2));
        cmd.setCode_poste(rs.getString(3));
        cmd.setNom_poste(rs.getString(4));
        cmd.setFormation_pharm_m1(rs.getInt(5));
        cmd.setFormation_pharm_m2(rs.getInt(6));
        cmd.setFormation_pharm_m3(rs.getInt(7));
        cmd.setFormation_pharm_m4(rs.getInt(8));
        cmd.setFormation_pharm_m5(rs.getInt(9));
        cmd.setFormation_pharm_m6(rs.getInt(10));
        cmd.setFormation_pharm_m7(rs.getInt(11));

        return cmd;
    }
}
