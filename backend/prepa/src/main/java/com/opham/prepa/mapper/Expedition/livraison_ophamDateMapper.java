package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.detail_livraison;
import com.opham.prepa.model.Expedition.livraison_ophamDate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class livraison_ophamDateMapper implements RowMapper<livraison_ophamDate>{
    public livraison_ophamDate mapRow(ResultSet rs, int rowNum) throws SQLException {
        livraison_ophamDate cmd = new livraison_ophamDate();
        cmd.setDate_today(rs.getDate(1));

        return cmd;
    }
}
