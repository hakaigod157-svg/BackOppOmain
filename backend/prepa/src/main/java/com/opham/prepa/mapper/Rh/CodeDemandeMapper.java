package com.opham.prepa.mapper.Rh;

import org.springframework.jdbc.core.RowMapper;
import com.opham.prepa.model.Rh.CodeDemande;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CodeDemandeMapper implements RowMapper<CodeDemande> {
    public CodeDemande mapRow(ResultSet rs, int rowNum) throws SQLException {
        CodeDemande cmd = new CodeDemande();
        cmd.setCodenouveaudemande(rs.getString(1));
        return cmd;
    }
}
