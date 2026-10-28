package com.opham.prepa.mapper.Expedition;

import com.opham.prepa.model.Expedition.VoitureCh;
import com.opham.prepa.model.Expedition.proximite;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class VoitureChMapper implements RowMapper<VoitureCh> {
    @Override
    public VoitureCh mapRow(ResultSet rs, int rowNum) throws SQLException {
        VoitureCh D = new VoitureCh();
        D.setVoit(rs.getString(1));
        D.setChauf(rs.getString(2));
        D.setDispo(rs.getInt(3));
        D.setDisponibilite(rs.getString(4));
        D.setCode(rs.getString(5));
        D.setType_liv(rs.getString(6));
        D.setType_service(rs.getString(7));
        D.setSeq(rs.getString(8));
        return D;
    }
}