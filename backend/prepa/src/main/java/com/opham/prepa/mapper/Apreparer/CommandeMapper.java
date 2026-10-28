package com.opham.prepa.mapper.Apreparer;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.opham.prepa.model.Apreparer.Commande;
import org.springframework.jdbc.core.RowMapper;

public class CommandeMapper implements RowMapper<Commande> {
    public Commande mapRow(ResultSet rs, int rowNum) throws SQLException {
        Commande cmd = new Commande();
        cmd.setCode(rs.getString("CODE"  ));
        cmd.setDate_com(rs.getDate("DATE_COMMANDE"));
        cmd.setCode_client(rs.getString("CLIENT"));
        cmd.setNom_client(rs.getString("NOM_CLIENT"));
        cmd.setCommentaire(rs.getString("commentaire"));
        cmd.setSeq_cc(rs.getLong("CCSEQ"));
        cmd.setDate_valide_adv(rs.getDate("DATE_VALIDATION_ADV"));
        cmd.setResume(rs.getString("tranfert"));
        cmd.setUrgent(rs.getInt("URGENT"));
        cmd.setAprendre(rs.getInt("A_PRENDRE"));
        cmd.setPrioritaire(rs.getInt("PRIO"));
        cmd.setDuree_atte_prep(rs.getInt("DELAI_ATTE_PREP"));
        cmd.setProvince(rs.getInt("TANA"));
        cmd.setGarde(rs.getInt("GARDE"));
        cmd.setNbLigne(rs.getInt("NB_LIGNE"));
        cmd.setAo(rs.getInt("AO"));
        cmd.setDate_prevue_liv(rs.getTimestamp("DATELIV_PREVU"));
        cmd.setSpec_cc(rs.getInt("CC2SPEC"));
        cmd.setDate_sortie_magasin(rs.getDate("SORTIE_MAGASIN_PREVUE"));
        cmd.setDuree_mag_restant(rs.getInt("MAG_RESTANT"));
        cmd.setSituation_mag(rs.getString("SITUATION_MAG"));
        cmd.setDate_sortie_exp(rs.getDate("SORTIE_EXPEDITION"));
        cmd.setDuree_exp_restant(rs.getInt("EXP_RESTANT"));
        cmd.setSituation_exp(rs.getString("SITUATION_EXP"));
        cmd.setAxe(rs.getString("CC2AXE"));
        cmd.setNb_BP(rs.getInt("NB_BP"));
        cmd.setValeur(rs.getString("CLVALEUR"));
        cmd.setCode_traite(rs.getString("CODE_TRAITE"));
        return cmd;
    }
}

