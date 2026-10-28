package com.opham.prepa.mapper.ValidationCommande;

import com.opham.prepa.model.ValidationCommande.CommandeAValider;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandeAvaliderMapper implements RowMapper<CommandeAValider>{

    public CommandeAValider mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommandeAValider cmd = new CommandeAValider();
        cmd.setCcseq(rs.getLong(1));
        cmd.setCode(rs.getString(2));
        cmd.setDate(rs.getDate(3));
        cmd.setCode_client(rs.getString(4));
        cmd.setNom_client(rs.getString(5));
        cmd.setMode(rs.getString(6));
        cmd.setCondition(rs.getString(7));
        cmd.setComptant(rs.getString(8));
        cmd.setPlafond(rs.getDouble(9));
        cmd.setEtape_adv(rs.getString(10));
        cmd.setSolde(rs.getDouble(11));
        cmd.setFacture_echue(rs.getDouble(12));
        cmd.setCmd_encours(rs.getDouble(13));
        cmd.setCmd(rs.getDouble(14));
        cmd.setRg_encours(rs.getDouble(15));
        cmd.setValidation_auto(rs.getString(16));
        cmd.setCommentaire(rs.getString(17));
        cmd.setCouleur(rs.getInt(18));
        cmd.setBloque(rs.getInt(19));
        cmd.setUser_bloque(rs.getString(20));
        cmd.setAdr_liv(rs.getString(21));
        cmd.setGarde(rs.getString(22));
        cmd.setValeur_client(rs.getString(23));
        cmd.setMode_liv(rs.getString(24));
        cmd.setDepassement(rs.getBigDecimal(25));
        cmd.setCommentaire_client(rs.getString(26));
        cmd.setCom_a_valider(rs.getString(27));

        return cmd;
    }
}
