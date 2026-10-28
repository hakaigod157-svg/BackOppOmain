package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.detail_editionLv;
import com.opham.prepa.model.Expedition.detail_livraison;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class detail_editionLvMapper implements RowMapper<detail_editionLv>{
    public detail_editionLv mapRow(ResultSet rs, int rowNum) throws SQLException {
        detail_editionLv cmd = new detail_editionLv();
        cmd.setCode_cc(rs.getString(1));
        cmd.setCode_client(rs.getString(2));
        cmd.setNom_client(rs.getString(3));
        cmd.setDate_validation_adv(rs.getTimestamp(4));
        cmd.setEtape(rs.getString(5));
        cmd.setGarde(rs.getInt(6));
        cmd.setDate_commande(rs.getTimestamp(7));
        cmd.setNombre_colis(rs.getString(8));
        cmd.setCc2_pass_bal(rs.getInt(9));
        cmd.setDateliv_prevu(rs.getTimestamp(10));
        cmd.setModeliv(rs.getString(11));
        cmd.setService_cde(rs.getString(12));
        cmd.setCc2axe(rs.getString(13));
        cmd.setPr_type(rs.getString(14));

        return cmd;
    }
}
