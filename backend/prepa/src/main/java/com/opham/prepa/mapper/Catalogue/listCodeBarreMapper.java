package com.opham.prepa.mapper.Catalogue;

import com.opham.prepa.model.Catalogue.listCodeBarre;
import com.opham.prepa.model.Catalogue.listLabo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class listCodeBarreMapper implements RowMapper<listCodeBarre>{
    public listCodeBarre mapRow(ResultSet rs, int rowNum) throws SQLException {
        listCodeBarre cmd = new listCodeBarre();
        cmd.setLabo(rs.getString(1));
        cmd.setArticle(rs.getString(2));
        cmd.setPays(rs.getString(3));
        cmd.setCode_labo(rs.getString(4));
        cmd.setCode_pays(rs.getString(5));
        cmd.setCode_article(rs.getString(6));
        cmd.setCle_ctrl(rs.getInt(7));
        cmd.setCode_ean13(rs.getString(8));
        cmd.setDate_cre(rs.getDate(9));
        cmd.setUser_cre(rs.getInt(10));

        return cmd;
    }
}
