package com.opham.prepa.mapper.genererBP;

import com.opham.prepa.model.genererBP.ArticleCmd;
import com.opham.prepa.model.genererBP.DepotCmd;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleCmdMapper implements RowMapper<ArticleCmd> {
    @Override
    public ArticleCmd mapRow(ResultSet rs, int rowNum) throws SQLException {
        ArticleCmd cmd = new ArticleCmd();
        cmd.setCode_CCL(rs.getString("CCLCODE"));
        cmd.setArticle(rs.getString("CCLARTICLE"));
        cmd.setLibelle(rs.getString("ARLIB"));
        cmd.setColisage(rs.getInt("COLISAGE"));
        cmd.setQte_a_preparer(rs.getInt("QTE_A_PREPARER"));
        cmd.setDispo(rs.getInt("DISPO"));
        cmd.setTransfert(rs.getString("TRANSFERT"));
        cmd.setEtat(rs.getBoolean(8));
        cmd.setDepot(rs.getString("DEPOT"));
        return cmd;
    }
}
