package com.opham.prepa.mapper.Transfert;

import com.opham.prepa.model.Transfert.Transfert;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransfertMapper implements RowMapper<Transfert> {

    @Override
    public Transfert mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transfert cmd = new Transfert();
        cmd.setArticle(rs.getString(1));
        cmd.setLibelle(rs.getString(2));
        cmd.setLabo(rs.getString(3));
        cmd.setDepot_org(rs.getString(4));
        cmd.setEmpl_org(rs.getString(5));
        cmd.setDepot_dest(rs.getString(6));
        cmd.setEmpl_dest(rs.getString(7));
        cmd.setLot(rs.getString(8));
        cmd.setDatePer(rs.getDate(9));
        cmd.setLettre(rs.getString(10));
        cmd.setQte(rs.getInt(11));
        cmd.setNumarm1(rs.getString(12));
        cmd.setNumarm2(rs.getString(13));
        cmd.setDevise(rs.getString(14));
        cmd.setPadev(rs.getDouble(15));
        cmd.setPaht(rs.getDouble(16));
        cmd.setFrais(rs.getDouble(17));
        cmd.setUg(rs.getInt(18));
        cmd.setQteNonEt(rs.getInt(19));
        cmd.setQteEnCoursEt(rs.getInt(20));
        cmd.setQteEt(rs.getInt(21));
        return cmd;
    }
}
