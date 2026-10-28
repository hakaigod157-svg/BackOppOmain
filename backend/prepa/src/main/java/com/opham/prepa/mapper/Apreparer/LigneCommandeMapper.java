package com.opham.prepa.mapper.Apreparer;

import com.opham.prepa.model.Apreparer.LigneCommande;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LigneCommandeMapper implements RowMapper<LigneCommande> {
    public LigneCommande mapRow(ResultSet rs, int rowNum) throws SQLException {
        LigneCommande cmd = new LigneCommande();
        cmd.setCode_article(rs.getString("CODE_ARTICLE"  ));
        cmd.setLibelle(rs.getString("LIBELLE"));
        cmd.setQte_cmd(rs.getInt("QTE_CMD"));
        cmd.setQte_reste(rs.getInt("QTE_RESTE"));
        cmd.setQte_expedie(rs.getInt("QTE_EXPEDIE"));
        cmd.setComs(rs.getString("COMS"));
        return cmd;
    }
}
