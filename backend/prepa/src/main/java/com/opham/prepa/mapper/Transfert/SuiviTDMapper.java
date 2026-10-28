package com.opham.prepa.mapper.Transfert;

import com.opham.prepa.model.Transfert.SuiviTD;
import com.opham.prepa.model.Transfert.SuiviTDDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuiviTDMapper implements RowMapper<SuiviTD> {
    @Override
    public SuiviTD mapRow(ResultSet rs, int rowNum) throws SQLException {
        SuiviTD td = new SuiviTD();
        td.setCode(rs.getString(1));
        td.setDate(rs.getDate(2));
        td.setQte(rs.getInt(3));
        td.setUsers(rs.getString(4));
        td.setDeb_picking(rs.getTimestamp(5));
        td.setFin_picking(rs.getTimestamp(6));
        td.setEtat_picking(rs.getString(7));
        td.setUser_picking(rs.getString(8));
        td.setNb_ligne(rs.getInt(9));
        td.setDuree(rs.getBigDecimal(10));
        td.setDep_org(rs.getString(11));
        td.setDep_dest(rs.getString(12));
        td.setDuree_atte(rs.getBigDecimal(13));
        td.setDate_entree(rs.getTimestamp(14));
        return td;
    }
}
