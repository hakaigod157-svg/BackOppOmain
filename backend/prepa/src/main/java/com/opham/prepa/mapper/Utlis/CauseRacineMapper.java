package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.CauseRacine;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CauseRacineMapper implements RowMapper<CauseRacine> {

    @Override
    public CauseRacine mapRow(ResultSet rs, int rowNum) throws SQLException {
        CauseRacine causeRac = new CauseRacine();

        int causeracseq = rs.getInt("CAUSE_RAC_SEQ");
        causeRac.setCauseracseq(rs.wasNull() ? 0 : causeracseq);
        //causeRac.setCauseracseq(rs.getString("CAUSE_RAC_SEQ"));
        causeRac.setCauseracncpscode(rs.getString("CAUSE_RAC_NCPSCODE"));
        causeRac.setCauseracdate(rs.getDate("CAUSE_RAC_DATE"));
        causeRac.setCauseracdescription(rs.getString("CAUSE_RAC_DESCRIPTION"));
        int nbaction = rs.getInt("NBACTION");
        causeRac.setNbaction(rs.wasNull() ? 0 : nbaction);
        causeRac.setCauseracprocimput(rs.getString("CAUSE_RAC_PROC_IMPUT"));
        causeRac.setEtat(rs.getString("ETAT"));
        int causeracvalid = rs.getInt("CAUSE_RAC_VALID");
        causeRac.setCauseracvalide(rs.wasNull() ? 0 : causeracvalid);

        return causeRac;
    }
}
