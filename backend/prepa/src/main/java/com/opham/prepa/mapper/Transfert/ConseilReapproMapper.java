package com.opham.prepa.mapper.Transfert;

import com.opham.prepa.model.Transfert.ColisIncomplet;
import com.opham.prepa.model.Transfert.ConseilReappro;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConseilReapproMapper implements RowMapper<ConseilReappro> {
    @Override
    public ConseilReappro mapRow(ResultSet rs, int rowNum) throws SQLException {
        ConseilReappro c = new ConseilReappro();
        c.setArticle(rs.getString("ARCODE"));
        c.setLibelle(rs.getString("ARLIB"));
        c.setColisage(rs.getInt("COLISAGE"));
        c.setMoyenne_vte_j(rs.getInt("MOYENNE_VTE_J"));
        c.setCouverture_det(rs.getDouble("COUVERTURE_DET"));
        c.setCouverture_besion(rs.getDouble("COUVERTURE_BESOIN"));
        c.setStick_gros(rs.getInt("STOCK_GROS"));
        c.setQte_appro_exacte(rs.getInt("QTE_APPRO_EXACTE"));
        c.setQte_appro(rs.getInt("QTE_APPRO"));
        c.setUrg(rs.getString("URG"));
        c.setStock_det(rs.getInt("STOCK_DET"));
        return c;
    }
}
