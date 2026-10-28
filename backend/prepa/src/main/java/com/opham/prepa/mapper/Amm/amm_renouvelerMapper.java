package com.opham.prepa.mapper.Amm;

import com.opham.prepa.model.Amm.amm_renouveler;
import com.opham.prepa.model.Amm.dossier;
import org.springframework.jdbc.core.RowMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class amm_renouvelerMapper implements RowMapper<amm_renouveler>{
    public amm_renouveler mapRow(ResultSet rs, int rowNum) throws SQLException {
        amm_renouveler cmd = new amm_renouveler();
        cmd.setCode_article(rs.getString(1));
        cmd.setLibelle(rs.getString(2));
        cmd.setTitulaire(rs.getString(3));
        cmd.setAmm_interprete(rs.getString(4));
        cmd.setAmm_code(rs.getString(5));
        cmd.setAmm_date(rs.getDate(6));
        cmd.setExpire(rs.getString(7));



        return cmd;
    }
}
