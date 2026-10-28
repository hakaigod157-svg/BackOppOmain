package com.opham.prepa.mapper.Transfert;

import com.opham.prepa.model.Transfert.Rotation;
import com.opham.prepa.model.genererBP.ArticleCmd;
import com.opham.prepa.model.genererBP.RayonCmd;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RotationMapper implements RowMapper<Rotation> {

    @Override
    public Rotation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rotation cmd = new Rotation();
        cmd.setArticle(rs.getString("STEMPAR"));
        cmd.setLibelle(rs.getString("ARLIB"));
        cmd.setLot(rs.getString("STLOT"));
        cmd.setDatePer(rs.getDate("NLOTDATEPER"));
        cmd.setDepot_org(rs.getString("xDEPOT"));
        cmd.setEmpl_org(rs.getString("xEMP_ORG"));
        cmd.setColisage(rs.getInt("ARFRANCOFO"));
        cmd.setDepot_dest(rs.getString("xDEPOT"));
        cmd.setEmpl_dest(rs.getString("xEMP_DEST"));
        cmd.setQte(rs.getInt("QUANTITE"));
        cmd.setEtat(rs.getBoolean(11));
        return cmd;
    }
}
