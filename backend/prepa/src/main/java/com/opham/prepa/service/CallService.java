package com.opham.prepa.service;

import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.mapper.AppelEntrant.ActionFirstAppelMapper;
import com.opham.prepa.mapper.AppelEntrant.AppelEntrantMapper;
import com.opham.prepa.mapper.AppelEntrant.AppelSortantMapper;
import com.opham.prepa.mapper.AppelEntrant.MoyenneAppelMapper;
import com.opham.prepa.model.AppelEntrant.ActionFirstAppel;
import com.opham.prepa.model.AppelEntrant.AppelEntrant;
import com.opham.prepa.model.AppelEntrant.AppelSortant;
import com.opham.prepa.model.AppelEntrant.MoyenneAppel;
import com.opham.prepa.repository.AppelEntrant.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public class CallService implements CallRepository {

    private final UserSessionManager userSessionManager;

    @Autowired
    public CallService(UserSessionManager userSessionManager) {

        this.userSessionManager = userSessionManager;
    }
    @Override
    public List<AppelEntrant> findAppelEntrant(String sessionId, Date date_deb , Date date_fin) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        //Requête qui affiche les appels entrant
        //return jdbcTemplate.query("select SEQ,CALLDATE,DATE,HEURE,SRC,NUMERO,NUMERO_INDICATIF,CLIENT,DST,POSTE_INTERNE,DURATION,BILLSEC,DISPOSITION,ETAT,STATUT,DEPUIS,DATERAPPEL_1,DATERAPPEL_2,POSTE_RAP1,POSTE_RAP2,VISIBLE_BTN,RESOLU from CALLING_OPHAM..VIEW_APPEL_ENTRANT where  convert(date,CALLDATE) between ? and ? order by CALLDATE desc",
        String sql = "execute CALLING_OPHAM..x_AppelEntrant ?,?";
        return jdbcTemplate.query(sql,new AppelEntrantMapper(),date_deb,date_fin);

        //new AppelEntrantMapper(),date_deb,date_fin);
    }

    @Override
    public List<AppelSortant> findAppelSortant(String sessionId, Date date_sortant , String datePart, Time timePart, String numero) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "execute CALLING_OPHAM..x_appelSortant ?,?,?";
        return jdbcTemplate.query(sql,new AppelSortantMapper(),numero,datePart,timePart);

     /*   return jdbcTemplate.query("select SEQ,CALLDATE,DATE,HEURE,SRC,NUMERO,NUMERO_INDICATIF,CLIENT,DCONTEXT,DST,DSTCHANNEL,POSTE_INTERNE,DURATION,BILLSEC,DISPOSITION,UNIQUEID,ETAT from CALLING_OPHAM..VIEW_APPEL_SORTANT_NEW where  NUMERO =? and DATE =? and HEURE>? ",
                new AppelSortantMapper(),numero,datePart,timePart); */
    }

    //@Override
    //public List<ActionFirstAppel> findActionFirstAppel(int seqEntrant) {
    //  return jdbcTemplate.query("select SEQ,ACTION from CALLING_OPHAM..ACTIONS order by SEQ",
    //new ActionFirstAppelMapper(),seqEntrant);
//
    //}

    @Override
    public List<ActionFirstAppel> findActionFirstAppel(String sessionId, BigDecimal seqEntrantBig) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "execute CALLING_OPHAM..nj_GetActions ?";
        return jdbcTemplate.query(sql, new ActionFirstAppelMapper(), seqEntrantBig);
    }


    @Override
    public void updateEtat(String sessionId, BigDecimal AppelId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "update CALLING_OPHAM..FAPPEL set ETAT=1 where SEQ=?";
        jdbcTemplate.update(sql, AppelId);
    }

   /* @Override
    public void updateEtat1(Long AppelId) {
        String sql = "update CALLING_OPHAM..FAPPEL set ETAT=2 where SEQ=?";
        jdbcTemplate.update(sql, AppelId);
    } */

    @Override
    public int updateEtatSortie(String sessionId, BigDecimal seqEtatBig, int etat, BigDecimal seqSortant, int rappel, String src_sortant) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "execute CALLING_OPHAM..bp_majEtatAppel ?,?,?,?,?";
        return jdbcTemplate.update(sql, seqEtatBig,etat,seqSortant,rappel,src_sortant);
    }

    @Override
    public int findxTestMaj(String sessionId, BigDecimal seq) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "execute CALLING_OPHAM..x_testMAJ ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{seq}, Integer.class);
    }



    @Override
    public int insertFrappel(String sessionId, BigDecimal seqEntrant, BigDecimal seqSortant, String user, String ip_local) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "insert into CALLING_OPHAM..FRAPPEL (RAPPEL_SEQ_ENTRANT,RAPPEL_SEQ_SORTANT,RAPPEL_USERCRE,RAPPEL_IPCRE) values (?,?,?,?)";
        return jdbcTemplate.update(sql, seqEntrant,seqSortant,user,ip_local);
    }


    @Override
    public List<MoyenneAppel> findAppelMoyenne(String sessionId, Date date_deb, Date date_fin) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "execute CALLING_OPHAM..x_MoyenneRappel ?,?";
        return jdbcTemplate.query(sql,new MoyenneAppelMapper(),date_deb,date_fin);

    }
}
