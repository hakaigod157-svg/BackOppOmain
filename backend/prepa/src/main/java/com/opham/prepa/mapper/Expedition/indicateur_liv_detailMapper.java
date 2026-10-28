package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.indicateur_liv_detail;
import com.opham.prepa.model.Expedition.indicateur_livraison;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class indicateur_liv_detailMapper implements RowMapper<indicateur_liv_detail>{
    public indicateur_liv_detail mapRow(ResultSet rs, int rowNum) throws SQLException {
        indicateur_liv_detail cmd = new indicateur_liv_detail();
        cmd.setCode(rs.getString(1));
        cmd.setClient(rs.getString(2));
        cmd.setNom_client(rs.getString(3));
        cmd.setDate_commande(rs.getDate(4));
        cmd.setDate_validation_adv(rs.getTimestamp(5));
        cmd.setCc2_vague_liv(rs.getString(6));
        cmd.setDateliv_prevu(rs.getTimestamp(7));
        cmd.setCc2axe(rs.getString(8));
        cmd.setStade(rs.getString(9));
        cmd.setLivraison_ind(rs.getTimestamp(10));
        cmd.setSortie_securite(rs.getTimestamp(11));
        cmd.setLivraison_prevu_marge(rs.getTimestamp(12));
        cmd.setCc2_pass_bal(rs.getInt(13));
        cmd.setQualite_livraison(rs.getString(14));

        return cmd;
    }
}
