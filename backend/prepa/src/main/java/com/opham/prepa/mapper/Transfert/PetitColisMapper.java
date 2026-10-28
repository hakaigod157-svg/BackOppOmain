package com.opham.prepa.mapper.Transfert;

import com.opham.prepa.model.Transfert.ColisIncomplet;
import com.opham.prepa.model.Transfert.PetitColis;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetitColisMapper implements RowMapper<PetitColis> {
    @Override
    public PetitColis mapRow(ResultSet rs, int rowNum) throws SQLException {
        PetitColis cmd = new PetitColis();
        cmd.setArticle(rs.getString("m_article"));
        cmd.setLibelle(rs.getString("m_libelle"));
        cmd.setLot(rs.getString("m_lot"));
        cmd.setDatePer(rs.getDate("m_dateper"));
        cmd.setDepot_org(rs.getString("m_depotOrg"));
        cmd.setEmpl_org(rs.getString("m_empOrg"));
        cmd.setColisage(rs.getInt("m_colisage"));
        cmd.setPalette(rs.getInt("m_palette"));
        cmd.setNbColis(rs.getInt("m_colis"));
        cmd.setPerc(rs.getInt("m_perc"));
        cmd.setLot_vte(rs.getInt("m_lot_vte"));
        cmd.setStock_det(rs.getInt("m_stock_det"));
        cmd.setNb_j_vte(rs.getDouble("m_nb_j_vte"));
        cmd.setDepot_dest(rs.getString("m_depotDest"));
        cmd.setEmpl_dest(rs.getString("m_emplDest"));
        cmd.setQte(rs.getInt("m_qteTotal"));
        cmd.setEtat(rs.getBoolean(17));
        return cmd;
    }
}
