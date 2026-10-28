package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.Trans_Attribution_Lv;
import com.opham.prepa.model.Expedition.Trans_facture_L;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Trans_Facture_L_Mapper implements RowMapper<Trans_facture_L> {
    @Override
    public Trans_facture_L mapRow(ResultSet rs, int rowNum) throws SQLException {
        Trans_facture_L L = new Trans_facture_L();
        L.setCode_liv(rs.getString(1));
        return L;
    }
}
