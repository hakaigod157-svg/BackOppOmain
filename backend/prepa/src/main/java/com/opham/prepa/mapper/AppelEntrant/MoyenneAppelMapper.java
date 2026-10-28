package com.opham.prepa.mapper.AppelEntrant;
import com.opham.prepa.model.AppelEntrant.AppelEntrant;
import com.opham.prepa.model.AppelEntrant.MoyenneAppel;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MoyenneAppelMapper implements RowMapper<MoyenneAppel> {
    public MoyenneAppel mapRow(ResultSet rs, int rowNum) throws SQLException {
        MoyenneAppel cmd = new MoyenneAppel();
        cmd.setDate(rs.getDate(1));
        cmd.setA_rappeler(rs.getInt(2));
        cmd.setRappele(rs.getString(3));
        cmd.setRappele_a_temp(rs.getString(4));
        cmd.setMoyenne(rs.getInt(5));

        return cmd;

    }
}
