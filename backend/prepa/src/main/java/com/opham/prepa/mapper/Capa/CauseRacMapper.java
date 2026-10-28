package com.opham.prepa.mapper.Capa;

import com.opham.prepa.model.Capa.CauseRac;
import com.opham.prepa.model.Utils.Action;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CauseRacMapper implements RowMapper<CauseRac> {
    @Override
    public CauseRac mapRow(ResultSet rs, int rowNum) throws SQLException {
        CauseRac causeRac = new CauseRac();

        //code de action

        int ActionSeq = rs.getInt("ACTION_SEQ");
        causeRac.setActionSeq(rs.wasNull() ? 0 : ActionSeq);
        causeRac.setActionNcpscode(rs.getString("ACTION_NCPSCODE"));
        int ActionValide = rs.getInt("ACTION_VALIDE");
        causeRac.setActionValide(rs.wasNull() ? 0 : ActionValide);
        causeRac.setActionResponsable(rs.getString("ACTION_RESPONSABLE"));
        causeRac.setActionDateprev(rs.getDate("ACTION_DATEPREV"));
        causeRac.setLibelleType(rs.getString("LIBELLE_TYPE"));
        causeRac.setActionDateeffect(rs.getDate("ACTION_DATEEFFECT"));
        int ActionMiseAppli = rs.getInt("ACTION_MISEAPPLI");
        causeRac.setActionMiseappli(rs.wasNull() ? 0 : ActionMiseAppli);
        int ActionCausesseq = rs.getInt("ACTION_CAUSESEQ");
        causeRac.setActionCausesseq(rs.wasNull() ? 0 : ActionCausesseq);
        causeRac.setActionDescription(rs.getString("ACTION_DESCRIPTION"));
        causeRac.setActionValidationLibelle(rs.getString("VALIDATION_LIBELLE"));
        causeRac.setActionDateanaleffic(rs.getDate("ACTION_DATE_ANAL_EFFIC"));
        causeRac.setActionDaterelleeffic(rs.getDate("ACTION_DATE_RELLE_EFFIC"));
        causeRac.setActionCommentaire(rs.getString("ACTION_COMMENTAIRE"));
        //int ActionAnalyse = rs.getInt("ACTION_ANALYSE");
        //causeRac.setActionAnalyse(rs.wasNull() ? 0 : ActionAnalyse);

        return causeRac;
    }
}