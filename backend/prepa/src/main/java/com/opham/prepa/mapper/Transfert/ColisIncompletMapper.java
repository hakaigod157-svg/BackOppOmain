package com.opham.prepa.mapper.Transfert;

import com.opham.prepa.model.Transfert.ColisIncomplet;
import com.opham.prepa.model.Transfert.Rotation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColisIncompletMapper implements RowMapper<ColisIncomplet> {
    @Override
    public ColisIncomplet mapRow(ResultSet rs, int rowNum) throws SQLException {
        ColisIncomplet cmd = new ColisIncomplet();
        cmd.setArticle(rs.getString("m_article"));
        cmd.setLibelle(rs.getString("m_libelle"));
        cmd.setLot(rs.getString("m_lot"));
        cmd.setDatePer(rs.getDate("m_dateper"));
        cmd.setDepot_org(rs.getString("m_depotOrg"));
        cmd.setEmpl_org(rs.getString("m_empOrg"));
        cmd.setColisage(rs.getInt("m_colisage"));
        cmd.setLot_vte(rs.getInt("m_lot_vente"));
        cmd.setNb_j_vte(rs.getDouble("m_nb_j_vte"));
        cmd.setDepot_dest(rs.getString("m_depotDest"));
        cmd.setEmpl_dest(rs.getString("m_emplDest"));
        cmd.setQte(rs.getInt("m_reste"));
        cmd.setEtat(rs.getBoolean(13));
        return cmd;
    }
}
