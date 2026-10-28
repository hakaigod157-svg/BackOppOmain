package com.opham.prepa.mapper.ValidationCommande;


import com.opham.prepa.model.ValidationCommande.ClientSolde;
import com.opham.prepa.model.ValidationCommande.CommandeAValider;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientSoldeMapper implements RowMapper<ClientSolde>{

    public ClientSolde mapRow(ResultSet rs, int rowNum) throws SQLException {
        ClientSolde cmd = new ClientSolde();
        cmd.setPiece(rs.getString(1));
        cmd.setDate(rs.getDate(2));
        cmd.setEcheance(rs.getDate(3));
        cmd.setMontantinit(rs.getBigDecimal(4));
        cmd.setReste(rs.getBigDecimal(5));
        cmd.setComment1(rs.getString(6));
        cmd.setComment2(rs.getString(7));
        cmd.setNbjour(rs.getBigDecimal(8));
        cmd.setEcllib(rs.getString(9));
        cmd.setPartiel(rs.getInt(10));
        cmd.setFarsrv(rs.getInt(11));

        return cmd;
    }
}
