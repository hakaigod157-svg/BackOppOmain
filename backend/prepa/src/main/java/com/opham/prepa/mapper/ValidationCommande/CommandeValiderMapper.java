package com.opham.prepa.mapper.ValidationCommande;

import com.opham.prepa.model.ValidationCommande.CommandeAValider;
import com.opham.prepa.model.ValidationCommande.CommandeValider;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CommandeValiderMapper implements RowMapper<CommandeValider>{
    public CommandeValider mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommandeValider cmd = new CommandeValider();
        cmd.setCode_valider(rs.getString(1));
        cmd.setNom_client_valider(rs.getString(2));
        cmd.setDate_commande_valider(rs.getDate(3));
        cmd.setDate_validation_adv_valider(rs.getTimestamp(4));
        cmd.setCondition_client_valider(rs.getString(5));
        cmd.setComptant_valider(rs.getString(6));
        cmd.setPlafond_valider(rs.getBigDecimal(7));
        cmd.setSolde_client_valider(rs.getBigDecimal(8));
        cmd.setFacture_echue_valider(rs.getBigDecimal(9));
        cmd.setCmd_encours_valider(rs.getBigDecimal(10));
        cmd.setMontant_cmd_valider(rs.getBigDecimal(11));
        cmd.setReglement_encours_valider(rs.getBigDecimal(12));
        cmd.setCommentaire_valider(rs.getString(13));
        cmd.setCheque_valider(rs.getInt(14));
        cmd.setDepassement_valider(rs.getBigDecimal(15));
        cmd.setEtape_valider(rs.getString(16));
        cmd.setUser_valid_valider(rs.getString(17));
        cmd.setGarde_valider(rs.getInt(18));
        cmd.setBon_a_livrer_valider(rs.getInt(19));
        cmd.setClient_valider(rs.getString(20));


        return cmd;
    }
}
