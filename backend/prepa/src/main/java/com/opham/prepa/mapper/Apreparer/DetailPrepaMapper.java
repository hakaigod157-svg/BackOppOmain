package com.opham.prepa.mapper.Apreparer;

import com.opham.prepa.model.Apreparer.DetailPrep;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailPrepaMapper implements RowMapper<DetailPrep> {
    public DetailPrep mapRow(ResultSet rs, int rowNum) throws SQLException {
        DetailPrep cmd = new DetailPrep();
        cmd.setCode_BP(rs.getString("BPCODE"));
        cmd.setDatecre_BP(rs.getTimestamp("BPDATECRE"));
        cmd.setDepot(rs.getString("BPDEPOT"));
        cmd.setStade(rs.getString("STADE"));
        cmd.setListBac(rs.getString("LISTBAC"));
        cmd.setEmpl(rs.getString("EMPL"));
        cmd.setNbColis(rs.getInt("NB_COLIS"));
        return cmd;
    }
}
