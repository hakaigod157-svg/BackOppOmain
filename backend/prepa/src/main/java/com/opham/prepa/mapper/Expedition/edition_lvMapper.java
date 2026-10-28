package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.edition_lv;
import com.opham.prepa.model.Expedition.livraison;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class edition_lvMapper implements RowMapper<edition_lv>{
    public edition_lv mapRow(ResultSet rs, int rowNum) throws SQLException {
        edition_lv cmd = new edition_lv();
        cmd.setLiv_code(rs.getString(1));
        cmd.setLiv_sortie(rs.getTimestamp(2));
        cmd.setLiv_com(rs.getString(3));
        cmd.setLiv_chauf(rs.getString(4));
        cmd.setLiv_voit(rs.getString(5));
        cmd.setSaisie(rs.getString(6));
        cmd.setLiv_livreur(rs.getString(7));
        cmd.setLiv_axe(rs.getString(8));
        cmd.setLivrempli(rs.getInt(9));
        cmd.setLiv_saisie(rs.getInt(10));
        cmd.setLiv_seq2(rs.getInt(11));
        cmd.setLiv_seq(rs.getBigDecimal(12));
        cmd.setInc1(rs.getInt(13));
        cmd.setInc2(rs.getInt(14));
        cmd.setInc3(rs.getBigDecimal(15));
        cmd.setCpt_depart(rs.getInt(16));
        cmd.setCpt_arrive(rs.getInt(17));
        cmd.setRet_vehicule(rs.getTimestamp(18));
        cmd.setXCode(rs.getString(19));
        cmd.setIs_sortiesec(rs.getInt(20));
        cmd.setDuree(rs.getInt(21));
        cmd.setSortie_sec(rs.getTimestamp(22));
        cmd.setType(rs.getString(23));
        return cmd;
    }
}
