package com.opham.prepa.mapper.Rh;

import com.opham.prepa.model.Rh.DetailRecru;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailRecruMapper implements RowMapper<DetailRecru> {

    @Override
    public DetailRecru mapRow(ResultSet rs, int rowNum) throws SQLException {
        DetailRecru dtr = new DetailRecru();
        dtr.setLigne_etape_code(rs.getString("LIGNE_ETAPE_CODE"));
        dtr.setLigne_etape_etape(rs.getString("LIGNE_ETAPE_ETAPE"));
        dtr.setEtape_libelle(rs.getString("ETAPE_LIBELLE"));
        dtr.setLigne_etape_date_prevision(rs.getDate("LIGNE_ETAPE_DATE_PREVISION"));
        dtr.setLigne_etape_date(rs.getDate("LIGNE_ETAPE_DATE"));
        return dtr;
    }
}
