package com.opham.prepa.mapper.Transfert;

import com.opham.prepa.model.Transfert.PetitColis;
import com.opham.prepa.model.Transfert.ProblemeStock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProblemeStockMapper implements RowMapper<ProblemeStock> {
    @Override
    public ProblemeStock mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProblemeStock cmd = new ProblemeStock();
        cmd.setArticle(rs.getString("STEMPAR"));
        cmd.setLibelle(rs.getString("ARLIB"));
        cmd.setDepot(rs.getString("STEMPDEPOT"));
        cmd.setLot(rs.getString("STLOT"));
        cmd.setLettre(rs.getString("STLETTRE"));
        cmd.setEmpl(rs.getString("STEMPEMP"));
        cmd.setQteStock(rs.getInt("QTE_ACTU"));
        cmd.setQteTransfert(rs.getInt("QTE_TRANSFERT"));
        cmd.setRest(rs.getInt("QTE_REST"));
        return cmd;
    }
}

