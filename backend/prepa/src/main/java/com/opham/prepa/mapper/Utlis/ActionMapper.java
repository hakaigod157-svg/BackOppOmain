package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Action;
import org.apache.fop.datatypes.Numeric;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionMapper implements RowMapper<Action>{
    @Override
    public Action mapRow(ResultSet rs, int rowNum) throws SQLException {
        Action causeRac = new Action();

        //code de action
        causeRac.setActionNcpscode(rs.getString("ACTION_NCPSCODE"));
        int ActionValide = rs.getInt("ACTION_VALIDE");
        causeRac.setActionValide(rs.wasNull() ? 0 : ActionValide);
        causeRac.setActionResponsable(rs.getString("ACTION_RESPONSABLE"));
        causeRac.setActionDateprev(rs.getDate("ACTION_DATEPREV"));
        causeRac.setLibelleType(rs.getString("LIBELLE_TYPE"));
        causeRac.setActionDateeffect(rs.getDate("ACTION_DATEEFFECT"));
        int ActionMiseAppli = rs.getInt("ACTION_MISEAPPLI");
        causeRac.setActionMiseappli(rs.wasNull() ? 0 : ActionMiseAppli);
        causeRac.setActionCommentaire(rs.getString("ACTION_COMMENTAIRE"));
        int ActionCausesseq = rs.getInt("ACTION_CAUSESEQ");
        causeRac.setActionCausesseq(rs.wasNull() ? 0 : ActionCausesseq);
        causeRac.setActionDescription(rs.getString("ACTION_DESCRIPTION"));
        int ActionType = rs.getInt("ACTION_TYPE");
        causeRac.setActionType(rs.wasNull() ? 0 : ActionType);
        causeRac.setActionDateAnalEffic(rs.getDate("ACTION_DATE_ANAL_EFFIC"));
        causeRac.setActionDateRelleEffic(rs.getDate("ACTION_DATE_RELLE_EFFIC"));
        int ActionAnalyse = rs.getInt("ACTION_ANALYSE");
        causeRac.setActionAnalyse(rs.wasNull() ? 0 : ActionAnalyse);

        return causeRac;
    }
}
