package com.opham.prepa.mapper.EtatCommande;

import com.opham.prepa.model.EtatCommande.EtatCommande;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class EtatCommandeMapper implements RowMapper<EtatCommande> {
    public EtatCommande mapRow(ResultSet rs, int rowNum) throws SQLException {
        EtatCommande cmd = new EtatCommande();
        cmd.setCode(rs.getString(1));
        cmd.setCode_client(rs.getString(2));
        cmd.setNom_client(rs.getString(3));
        cmd.setAxe(rs.getString(4));
        cmd.setCommande(rs.getDate(5));
        cmd.setValid_fact(rs.getTimestamp(6));
        cmd.setMode(rs.getString(7));
        cmd.setValid_adv(rs.getTimestamp(8));
        cmd.setDeb_prep(rs.getTimestamp(9));
        cmd.setFin_prep(rs.getTimestamp(10));
        cmd.setDeb_ctrl(rs.getTimestamp(11));
        cmd.setFin_ctrl(rs.getTimestamp(12));
        cmd.setFin_emb(rs.getTimestamp(13));
        cmd.setSortie_fact(rs.getTimestamp(14));
        cmd.setCommentaire(rs.getString(15));
        cmd.setCommentaire_mag(rs.getString(16));
        cmd.setSortie_opham(rs.getTimestamp(17));
        cmd.setLivree_par(rs.getString(18));
        cmd.setVoiture_liv(rs.getString(19));
        cmd.setRetour_liv(rs.getString(20));
        cmd.setBon_a_livree(rs.getString(21));
        cmd.setAtte_bal_exp(rs.getTimestamp(22));
        cmd.setDate_bal_adv(rs.getTimestamp(23));
        cmd.setDate_annul_Bal_adv(rs.getTimestamp(24));
        return cmd;
    }
}
