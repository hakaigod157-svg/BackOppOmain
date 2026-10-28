package com.opham.prepa.mapper.EnPreparation;

import com.opham.prepa.model.Apreparer.Commande;
import com.opham.prepa.model.EnPreparation.EnCoursCMD;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnCoursCmdMapper implements RowMapper<EnCoursCMD> {
    @Override
    public EnCoursCMD mapRow(ResultSet rs, int rowNum) throws SQLException {
        EnCoursCMD cmd = new EnCoursCMD();
        cmd.setCode(rs.getString("CODE"  ));
        cmd.setDate_com(rs.getDate("DATE_COMMANDE"));
        cmd.setNom_client(rs.getString("NOM_CLIENT"));
        cmd.setType_tana(rs.getString("TYPE_TANA"));
        cmd.setCcseq(rs.getInt("CCSEQ"));
        cmd.setEtape(rs.getString("ETAPE"));
        cmd.setDuree_atte_prep(rs.getInt("CMIN"));
        cmd.setPrepspecif(rs.getInt("PREPSPECIF"));
        cmd.setUrgent(rs.getInt("URGENT"));
        cmd.setAprendre(rs.getInt("A_PRENDRE"));
        cmd.setPrioritaire(rs.getInt("PRIO"));
        cmd.setProvince(rs.getInt("TANA"));
        cmd.setGarde(rs.getInt("GARDE"));
        cmd.setNbLigne(rs.getInt("NBLIGNE"));
        cmd.setAo(rs.getInt("AO"));
        cmd.setDepuis_adv(rs.getInt("DEPUIS_ADV"));
        cmd.setDepuis_prepa(rs.getInt("DEPUIS_PREPA"));
        cmd.setDate_prevue_liv(rs.getTimestamp("DATELIV_PREVU"));
        cmd.setCc_bloque_fact(rs.getInt("CC_BLOQUE_FACT"));
        cmd.setSortie_magasin_prevue(rs.getTimestamp("SORTIE_MAGASIN_PREVUE"));
        cmd.setMag_restant(rs.getInt("MAG_RESTANT"));
        cmd.setReste_22(rs.getString(22));
        cmd.setSortie_expedition(rs.getTimestamp("SORTIE_EXPEDITION"));
        cmd.setExp_restant(rs.getInt("EXP_RESTANT"));
        cmd.setSituation_exp(rs.getString("SITUATION_EXP"));
        cmd.setAxe(rs.getString("CC2AXE"));
        cmd.setValeur(rs.getString("CLVALEUR"));
        cmd.setCode_traite(rs.getString("CODE_TRAITE"));
        return cmd;
    }
}
