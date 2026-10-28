package com.opham.prepa.mapper.Transfert;

import com.opham.prepa.model.Transfert.Rangement;
import com.opham.prepa.model.Transfert.Rotation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RangementMapper implements RowMapper<Rangement> {

    @Override
    public Rangement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rangement cmd = new Rangement();
        cmd.setArticle(rs.getString("STEMPAR"));
        cmd.setLibelle(rs.getString("ARLIB"));
        cmd.setLot(rs.getString("STLOT"));
        cmd.setDatePer(rs.getDate("NLOTDATEPER"));
        cmd.setDepot_org(rs.getString("DEPOT"));
        cmd.setEmpl_org(rs.getString("EMP_DEPART"));
        cmd.setColisage(rs.getInt("ARFRANCOFO"));
        cmd.setDepot_dest(rs.getString("DEPOT"));
        cmd.setEmpl_dest(rs.getString("EMP_ARRIVE"));
        cmd.setQte(rs.getInt("QUANTITE"));
        return cmd;
    }
}
