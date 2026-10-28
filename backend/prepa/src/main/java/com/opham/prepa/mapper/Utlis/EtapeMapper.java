package com.opham.prepa.mapper.Utlis;


import com.opham.prepa.model.Utils.Alerte;
import com.opham.prepa.model.Utils.Etape;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EtapeMapper implements RowMapper<Etape> {
    @Override
    public Etape mapRow(ResultSet rs, int rowNum) throws SQLException {
        Etape etape = new Etape();
        etape.setAttePrep(rs.getInt("AttePrep"));
        etape.setEncoursPrep(rs.getInt("EncoursPrep"));
        etape.setAtteCtrl(rs.getInt("AtteCtrl"));
        etape.setEncoursCtrl(rs.getInt("EncoursCtrl"));
        etape.setEmballage(rs.getInt("Emballage"));
        etape.setEncoursPrepBP(rs.getInt("ENCOURS_PREPA_BP"));
        etape.setEncoursCtrlBP(rs.getInt("ENCOURS_CTRL_BP"));
        etape.setAtteCtrlBP(rs.getInt("ATTE_CTRL_BP"));
        etape.setFinCtrlBP(rs.getInt("FIN_CTRL_BP"));
        etape.setEncoursPP(rs.getInt("encoursPP"));
        return etape;
    }
}