package com.opham.prepa.mapper.Expedition;
import com.opham.prepa.model.Expedition.livraison_ophamDate;
import com.opham.prepa.model.Expedition.livraison_ophamDemainDate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class livraison_ophamDemainDateMapper implements RowMapper<livraison_ophamDemainDate>{
    public livraison_ophamDemainDate mapRow(ResultSet rs, int rowNum) throws SQLException {
        livraison_ophamDemainDate cmd = new livraison_ophamDemainDate();
        cmd.setDate_demain(rs.getDate(1));

        return cmd;
    }
}
