package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleMapper implements RowMapper<Article> {
@Override
public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
    Article d = new Article();
        d.setArticle(rs.getString(1));
        d.setLibelle(rs.getString(2));
        d.setLabo(rs.getString(3));
        d.setNomLabo(rs.getString(4));
        d.setFamille(rs.getString(5));
        d.setNomFamille(rs.getString(6));
        d.setCategorie(rs.getString(7));
        d.setNomCategorie(rs.getString(8));
        d.setGamme(rs.getString(9));
        d.setNomGamme(rs.getString(10));
        d.setDistributeur(rs.getString(11));
        d.setNomDistributeur(rs.getString(12));
        d.setFournisPrincipal(rs.getString(13));
        d.setNomFournisPr(rs.getString(14));
        d.setFabricant(rs.getString(15));
        d.setNomFabricant(rs.getString(16));
        d.setPlateforme(rs.getString(17));
        d.setNomPlateforme(rs.getString(18));
        d.setSousCategorie(rs.getString(19));
        d.setNomSousCat(rs.getString(20));
        d.setDci(rs.getString(21));
        d.setNomCom(rs.getString(22));
        d.setColisage(rs.getInt(23));
        d.setType(rs.getString(24));
        d.setConservation(rs.getString(25));
        d.setStatut(rs.getString(26));
        return d;
        }

}
