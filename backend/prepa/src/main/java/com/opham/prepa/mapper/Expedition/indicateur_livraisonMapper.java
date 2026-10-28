package com.opham.prepa.mapper.Expedition;
import com.opham.prepa.model.Expedition.indicateur_livraison;
import com.opham.prepa.model.Expedition.livraison_opham;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class indicateur_livraisonMapper implements RowMapper<indicateur_livraison>{
    public indicateur_livraison mapRow(ResultSet rs, int rowNum) throws SQLException {
        indicateur_livraison cmd = new indicateur_livraison();
        cmd.setVague_ind(rs.getString(1));
        cmd.setHeure_ind(rs.getTime(2));
        cmd.setTotal_cde_ind(rs.getInt(3));
        cmd.setPrepa(rs.getInt(4));
        cmd.setCtrl(rs.getInt(5));
        cmd.setFacture(rs.getInt(6));
        cmd.setLivraison(rs.getInt(7));
        cmd.setLivre(rs.getInt(8));
        cmd.setPercent_livre(rs.getInt(9));
        cmd.setLivraison_ok(rs.getInt(10));
        cmd.setPercent_ok(rs.getInt(11));
        cmd.setCoul_livre(rs.getInt(12));
        cmd.setCoul_livraison_ok(rs.getInt(13));
        cmd.setLivraison_hier(rs.getInt(14));
        cmd.setPercent_liv_hier(rs.getInt(15));

        return cmd;
    }
}
