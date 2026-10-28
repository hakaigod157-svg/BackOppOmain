package com.opham.prepa.mapper.Transfert;

import com.opham.prepa.model.Transfert.SuiviTDDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuiviTDDetailMapper implements RowMapper<SuiviTDDetail> {
    @Override
    public SuiviTDDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        SuiviTDDetail td = new SuiviTDDetail();
        td.setCode(rs.getString(1));
        td.setDate(rs.getDate(2));
        td.setEmpl_org(rs.getString(3));
        td.setLibelle(rs.getString(4));
        td.setQte(rs.getInt(5));
        td.setEmpl_dest(rs.getString(6));
        td.setCommentaire(rs.getString(7));
        td.setMotif(rs.getString(8));
        td.setDate_entree(rs.getTimestamp(9));
        td.setUsers(rs.getString(10));
        td.setDeb_picking(rs.getTimestamp(11));
        td.setFin_picking(rs.getTimestamp(12));
        td.setEtat_picking(rs.getString(13));
        td.setUser_picking(rs.getString(14));
        td.setDuree(rs.getBigDecimal(15));
        td.setDep_org(rs.getString(16));
        td.setDep_dest(rs.getString(17));
        td.setDuree_atte(rs.getBigDecimal(18));
        return td;
    }
}
