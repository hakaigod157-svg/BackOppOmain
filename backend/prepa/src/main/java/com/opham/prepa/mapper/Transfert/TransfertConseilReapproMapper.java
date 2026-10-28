package com.opham.prepa.mapper.Transfert;

import com.opham.prepa.model.Transfert.Transfert;
import com.opham.prepa.model.Transfert.TransfertConseilReappro;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransfertConseilReapproMapper implements RowMapper<TransfertConseilReappro> {

    @Override
    public TransfertConseilReappro mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransfertConseilReappro cmd = new TransfertConseilReappro();
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
        cmd.setCommentaire(rs.getString(18));
        cmd.setUg(rs.getInt(19));
        cmd.setUrg(rs.getString(20));
        cmd.setQteNonEt(rs.getInt(21));
        cmd.setQteEnCoursEt(rs.getInt(22));
        cmd.setQteEt(rs.getInt(23));
        return cmd;
    }
}
