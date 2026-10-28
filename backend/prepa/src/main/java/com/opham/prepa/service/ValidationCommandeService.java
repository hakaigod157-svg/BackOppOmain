package com.opham.prepa.service;

import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.mapper.Transfert.ConseilReapproMapper;
import com.opham.prepa.mapper.Transfert.TransfertConseilReapproMapper;
import com.opham.prepa.mapper.ValidationCommande.*;
import com.opham.prepa.model.ValidationCommande.*;
import com.opham.prepa.repository.ValidationCommande.CommandeAlivrerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.*;
@Repository

public class ValidationCommandeService implements CommandeAlivrerRepository {
    private final UserSessionManager userSessionManager;
    @Autowired
    public ValidationCommandeService(UserSessionManager userSessionManager) {

        this.userSessionManager = userSessionManager;
    }
    @Override
    public List<CommandeAlivrer> findCommandeAlivrer(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec bp_bon_liv_adv",
                new CommandeAlivrerMapper());
    }

    @Override
    public int updateFCC2(String sessionId, String cc,int etat) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "update FCC2 set CC2_ACCORD_ADV_LIV=? where CC2CODE= ?";
        return jdbcTemplate.update(sql,etat, cc);
    }

    @Override
    public int updateCOM_Client(String sessionId, String cc, String commentaires) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "v_bp_update_CommADV ?,?";
        return jdbcTemplate.update(sql,cc, commentaires);
    }


    @Override
    public List<CommandeAValider> findCommandeAValider(String sessionId , String client) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        /*return jdbcTemplate.query("exec x_CC_A_VALIDER_ADV_DEC_2 ?",*/
        return jdbcTemplate.query("exec x_CC_A_VALIDER_ADV_ERPO ?",
                new CommandeAvaliderMapper(),client);
    }
/*
    @Override
    public List<CommandeValider> findCommandeValider(String sessionId, Date datedeb, Date datefin, String codeCC, String client) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_CommandeValide_ADV ?,?,?,?",
                new CommandeValiderMapper(),datedeb,datefin,codeCC,client);
    }  */




    @Override
    public List<List<Object>> findCommandeValider(String sessionId, Date datedeb, Date datefin, String codeCC, String client) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("x_CommandeValide_ADV")
                .declareParameters(
                        new SqlParameter("dateDeb", Types.DATE),
                        new SqlParameter("dateFin", Types.DATE),
                        new SqlParameter("cde", Types.VARCHAR),
                        new SqlParameter("client", Types.VARCHAR)

                )
                .returningResultSet("result1", new CommandeValiderMapper())
                .returningResultSet("result2", new CommandeValiderStatsMapper());

        Map<String, Object> results = jdbcCall.execute(datedeb, datefin, codeCC, client);

        Map<String, List<Object>> mappedResults = new HashMap<>();

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));
        resultList.add((List<Object>) results.get("result2"));
        return resultList;
    }





    @Override
   public List<ClientSolde> findClientSolde(String sessionId, String codeclient, int type) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_CC_SOLDE_CLIENT ?,?",
                new ClientSoldeMapper(),codeclient,type);
    }

    @Override
    public List<CommandeEncours> findCommandeEncours(String sessionId, String codeclient, int type) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_CC_SOLDE_CLIENT ?,?",
                new CommandeEncoursMapper(),codeclient,type);
    }

    @Override
    public List<Avance> findAvance(String sessionId, String codeclient, int type) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_CC_SOLDE_CLIENT ?,?",
                new AvanceMapper(),codeclient,type);
    }


    @Override
    public List<Traite> findTraite(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_commande_traite",
                new TraiteMapper());
    }

    @Override
    public List<Reglement> findReglement(String sessionId, String codeclient, int type) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_CC_SOLDE_CLIENT ?,?",
                new ReglementMapper(),codeclient,type);
    }

    @Override
    public List<ReglementBon> findReglementBon(String sessionId, String codeclient, int type) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_CC_SOLDE_CLIENT ?,?",
                new ReglementBonMapper(),codeclient,type);
    }

    @Override
    public List<Commande> findCommande(String sessionId, String codeclient, int type) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_CC_SOLDE_CLIENT ?,?",
                new CommandeMapper(),codeclient,type);
    }



    @Override
    public List<TestApresValide> onValideAdv(String sessionId, String cmd, String accord, String comAccord, String commClt,
                                             String comMag, String modeNorm, String modeEncours,
                                             BigDecimal impaye, BigDecimal retard, int bal, String ip) {

        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);



        // Construire l'appel de procédure avec les paramètres
        String callSql = "{call dbo.v_bp_validCmd_adv(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        return jdbcTemplate.query(callSql, new Object[]{
                cmd, accord, comAccord, commClt, comMag,
                modeNorm, modeEncours, impaye, retard, bal, ip
        }, (rs, rowNum) -> {
            TestApresValide result = new TestApresValide();
            result.setMode(rs.getInt(1));
            result.setTraiteEncours(rs.getString(2));
            result.setStade(rs.getInt(3));
            result.setNumero(rs.getString(4));
            result.setNotification(rs.getString(5));
            result.setNom_com(rs.getString(6));
            return result;
        });
    }


    @Override
    public String onCreeTraiteAdv(String sessionId, String cmd,String traite,int isNew) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
               // .withCatalogName("Equagestion")
                .withProcedureName("v_bp_creeTraite_Adv")
                .declareParameters(
                        new SqlParameter("cmd", Types.VARCHAR),
                        new SqlParameter("traite", Types.VARCHAR),
                        new SqlParameter("isNew", Types.INTEGER),
                        //new SqlParameter("dateEch", Types.DATE),
                        new SqlOutParameter("m_code",Types.VARCHAR)
                );

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("cmd", cmd);
        inParams.put("traite", traite);
        inParams.put("isNew", isNew);
       // inParams.put("dateEch", dateEch);

        Map<String, Object> outParams = jdbcCall.execute(inParams);

        return (String) outParams.get("m_code");
    }


    @Override
    public List<InfoApresBlocage> onBlockAdv(String sessionId, String cmd, String ip,int couleur) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String cause = null;
        switch (couleur) {
            case 1:
                cause =  "Attente ADV";
                break;
            case 2:
                cause =  "PLAFOND DEPASSE";
                break;
            case 3:
                cause =  "RETARD DE PAIEMENT";
                break;
            case 4:
                cause =  "PLAFOND DEPASSE + RETARD DE PAIEMENT";
                break;
            default:
                cause ="";
                break;
        }

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("v_bp_blocage_Adv")
                .returningResultSet("result",
                        (rs, rowNum) -> {
                            InfoApresBlocage info = new InfoApresBlocage();
                            info.setStat(rs.getInt(1));
                            info.setNumero(rs.getString(2));
                            info.setNotification(rs.getString(3));
                            info.setNom_com(rs.getString(4));
                            return info;
                        });

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("cmd", cmd);
        inParams.put("ip", ip);
        inParams.put("cause",cause);
        Map<String, Object> result = jdbcCall.execute(inParams);

        return (List<InfoApresBlocage>) result.getOrDefault("result", Collections.emptyList());
    }

    @Override
    public int getStade_cmd(String sessionId,String code) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "SELECT isnull(CCSTADE_DET,0) FROM FCC where CCCODE=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code);
        return count ;
    }

    @Override
    public int getSatde_bal(String sessionId,String code) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "select isnull(CC2_ACCORD_ADV_LIV,0) from FCC2 where CC2CODE=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code);
        return count ;
    }

    @Override
    public Etape getEtapeAdv(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.queryForObject(" exec v_bp_count_adv",
                new Object[]{}, new EtapeMapper());
    }

}
