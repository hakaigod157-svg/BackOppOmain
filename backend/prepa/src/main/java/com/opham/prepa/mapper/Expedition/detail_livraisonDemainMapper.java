package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.detail_livraison;
import com.opham.prepa.model.Expedition.detail_livraisonDemain;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class detail_livraisonDemainMapper implements RowMapper<detail_livraisonDemain>{
    public detail_livraisonDemain mapRow(ResultSet rs, int rowNum) throws SQLException {
        detail_livraisonDemain cmd = new detail_livraisonDemain();
        cmd.setCode_cc(rs.getString(1));
        cmd.setCode_client(rs.getString(2));
        cmd.setNom_client(rs.getString(3));
        cmd.setDate_validation_adv(rs.getTimestamp(4));
        cmd.setEtape(rs.getString(5));
        cmd.setTana(rs.getInt(6));
        cmd.setCc2axe(rs.getString(7));
        cmd.setA_prendre(rs.getInt(8));
        cmd.setGarde(rs.getInt(9));
        cmd.setDate_commande(rs.getTimestamp(10));
        cmd.setSituation(rs.getString(11));
        cmd.setVague_heure(rs.getTime(12));
        cmd.setVague_min(rs.getInt(13));
        cmd.setVague_axe(rs.getString(14));
        cmd.setNombre_colis(rs.getString(15));
        cmd.setCc2_pass_bal(rs.getInt(16));
        cmd.setDateliv_prevu(rs.getTimestamp(17));
        cmd.setCc2_vague_liv(rs.getString(18));
        cmd.setModeliv(rs.getString(19));
        cmd.setVague_a_livrer(rs.getString(20));
        cmd.setDate_a_livrer(rs.getTimestamp(21));

        return cmd;
    }
}
