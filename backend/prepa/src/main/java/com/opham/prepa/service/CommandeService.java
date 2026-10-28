package com.opham.prepa.service;

import com.opham.prepa.Utils.Convert;
import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.mapper.Apreparer.*;
import com.opham.prepa.mapper.EnPreparation.*;
import com.opham.prepa.mapper.genererBP.ArticleCmdMapper;
import com.opham.prepa.mapper.genererBP.DepotCmdMapper;
import com.opham.prepa.mapper.genererBP.ListeCmdMapper;
import com.opham.prepa.mapper.genererBP.RayonCmdMapper;
import com.opham.prepa.model.Apreparer.*;
import com.opham.prepa.model.EnPreparation.DetailBP;
import com.opham.prepa.model.EnPreparation.EnCoursCMD;
import com.opham.prepa.model.EnPreparation.EnCoursPrepBP;
import com.opham.prepa.model.EnPreparation.ListLigneBP;
import com.opham.prepa.model.genererBP.ListeCmd;
import com.opham.prepa.repository.Apreparer.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import org.springframework.jdbc.core.*;

import java.io.*;
import java.sql.*;
import java.sql.Types;
import java.util.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.InputStream;

import org.springframework.dao.DataAccessException;

@Repository
public class CommandeService implements CommandeRepository {

    private final UserSessionManager userSessionManager;

    @Autowired
    public CommandeService(UserSessionManager userSessionManager) {
        this.userSessionManager = userSessionManager;
    }





    @Override
    public List<Commande> findByDate(String sessionId,String groupe, java.util.Date dateliv) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec v_x_Atte_Prepa_Partiel ?,?",
                new CommandeMapper(), groupe, dateliv);
    }

    @Override
    public List<EnCoursCMD> listEnCoursPrepa(String sessionId,int prepspecif, int isVisible, java.util.Date dateliv) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec v_x_CC_Preparation_Partiel ?,?,?",
                new EnCoursCmdMapper(), prepspecif, isVisible, dateliv);
    }

    public List<LigneCommande> findLigneCmdebyCode(String sessionId,String code) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("select CCLARTICLE as CODE_ARTICLE,ARLIB as LIBELLE,CCLQTE as QTE_CMD,CCLRESTE as QTE_RESTE,CCLQTEEXP as QTE_EXPEDIE,(case when isnull(xSeq,0)<>0 then '*T*' else '' end) as COMS\n" +
                        "from FCCL  left join VIEW_FAR on ARCODE=CCLARTICLE left join xFCCL on xSeq=CCLSEQ\n" +
                        "where CCLCODE=? order by CCLARTICLE",
                new LigneCommandeMapper(), code);
    }

    @Override
    public List<DetailPrep> listDetailBP(String sessionId,String code_CC) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_DetailPrepa2 ?",
                new DetailPrepaMapper(), code_CC);
    }

    @Override
    public InfoCommande plusInfoCmd(String sessionId,String code_CC) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.queryForObject("select CCCODE as CODE_CC,CCNOM as NOM_CLIENT,CCADR1||CCADR2 as adresse,CC2AXE as axe,CC2DATELIV_PREVU as DATE_LIV,CCCOMMENTAIRES as coms_client,CCCOMMENTMAG as coms_mag,CLTEL||' | '||CLTEL2 as contact,CC2RAISONDATELIVPREV as raisonDateLiv,convert(date,CC2DATELIV_PREVU) as date_l,convert(time,CC2DATELIV_PREVU) as heure_l   from FCC \n" +
                        "inner join FCC2 on CC2CODE=CCCODE inner join FCL on CLCODE=CCCLIENT where CCCODE=?",
                new Object[]{code_CC}, new InfoCmdMapper());
    }

    @Override
    public int updateInfoCmd(String sessionId,InfoCommande inf) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "exec v_bp_updateInfoCmd ?,?,?,?,?";
        return jdbcTemplate.update(sql, inf.getComs_mag(), inf.getAxe(), inf.getDate_liv(), inf.getRaisonDateLiv(), inf.getCode_cc());
    }


    @Override
    public String insert_BP(String sessionId,String ids, String code_CC, String depot,String user_web) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "exec v_x_CreateBP ?, ?,?,?";
        String result = jdbcTemplate.queryForObject(sql, new Object[]{ids, code_CC, depot,user_web}, String.class);
        return result;
    }

    @Override
    public int atteTransfert(String sessionId,String code_CC, String article) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "exec v_bp_updateAtteTransfert ?, ?";
        return jdbcTemplate.update(sql, code_CC, article);
    }

    @Override
    public void updateCCResume(String sessionId, String codeCC, String ip) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "update FCC set CC_IP_MODIF = ?,CCRESUME=(isnull(CCRESUME,''))||'*T*' where CCCODE=? and upper(CCRESUME) not like '%*T*%'";
        jdbcTemplate.update(sql,ip,codeCC);
    }

    /* NON UTILISER POUR L'INSTANT */
    @Override
    public void updateFrais(String sessionId,String codeCC) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "update FCC set CCDATEECH2=getdate(),CCDATEECH3=getdate(),CCDATEECH4=getdate(),CCDATEECH1=getdate(),CCPREPSPECIF=3 where CCCODE=?";
        jdbcTemplate.update(sql, codeCC);
    }
    /*|******************************|*/

    @Override
    public int insertL6(String sessionId,ListeCmd lc) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "INSERT INTO DBSUIVI..L6_PREPA_INSERT\n" +
                "\t(Article, Lettre, Designation, LienCode, LienNum, Qte, UnitFact, PrixHT, ModeLiv, LigneLibre, TypeVente, Reglement, Echeancesp, abs_Qte, Factman, Offert, Artype, Devise, Coursdev, PrixHTdev, TotHTdev, Rem1, Rem2, Rem3, TotPrixHT, Emplacement, Attachement, Lot, Arreffour, cclmarche, ccldate, cclcolis, arqtecolis, cclpaht, seqLib, comment_mag, cclcolisage, cclnbcolis, cclpack, rayon, depot, cclpromo,myID,commande,ip_web)\n" +
                "VALUES \n" +
                "\t(?,?,?,?,?,?,?,? ,?,?,?,?,? ,?,? ,?,? ,?,?,? ,?,? ,?,? ,?,?,?,?,?,?,?,? ,?,? ,?,?,?,?,? ,?,?,?,?,?,?)";

        return jdbcTemplate.update(sql, lc.getArticle(), lc.getLettre(), lc.getDesignation(), lc.getLienCode(), lc.getLienNum(),
                lc.getQte(), lc.getUnitfact(), lc.getPrix_ht(), lc.getModeliv(), lc.getLignelibre(), lc.getTypeVente(),
                lc.getReglement(), lc.getEcheancesp(), lc.getAbs_qte(), lc.getFactman(), lc.getOffert(), lc.getArtype(), lc.getDevise(),
                lc.getCoursdev(), lc.getPrixht_dev(), lc.getTotht_dev(), lc.getRem1(), lc.getRem2(), lc.getRem3(), lc.getTotPrix_HT(), lc.getEmplacement(),
                lc.getAttachement(), lc.getLot(), lc.getArreffour(), lc.getCclmarche(), lc.getCcldate(), lc.getCclcolis(), lc.getArqtecolis(), lc.getCclpaht(),
                lc.getSeqLib(), lc.getComment_mag(), lc.getCclcolisage(), lc.getCclnbcolis(),
                lc.getCclpack(), lc.getRayon(), lc.getDepot(), lc.getCclpromo(), lc.getMyID(), lc.getLienCode(),lc.getIp_web());
    }

 /*   @Override
    public String getId() {
        String sql = "select newID()";
        String result = jdbcTemplate.queryForObject(sql, new Object[]{}, String.class);
        return result;
    }*/

    @Override
    public List<List<Object>> genererBP(String sessionId,String code) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("BECCPREP_Liste_Depot")
                .declareParameters(
                        new SqlParameter("code", Types.VARCHAR)

                )
                .returningResultSet("result1", new DepotCmdMapper())
                .returningResultSet("result2", new ArticleCmdMapper())
                .returningResultSet("result3", new ListeCmdMapper())
                .returningResultSet("result4", new RayonCmdMapper());

        Map<String, Object> results = jdbcCall.execute(code);

        Map<String, List<Object>> mappedResults = new HashMap<>();

        List<List<Object>> resultList = new ArrayList<>();
        resultList.add((List<Object>) results.get("result1"));
        resultList.add((List<Object>) results.get("result2"));
        resultList.add((List<Object>) results.get("result3"));
        resultList.add((List<Object>) results.get("result4"));
      /*  mappedResults.put("result1", Collections.singletonList((List<DepotCmd>) results.get("result1")));
        mappedResults.put("result2", Collections.singletonList((List<ArticleCmd>) results.get("result2")));
        mappedResults.put("result3", Collections.singletonList((List<ListeCmd>) results.get("result3")));
        mappedResults.put("result4", Collections.singletonList((List<RayonCmd>) results.get("result4")));*/
        return resultList;
    }

    @Override
    public List<List<Object>> preparerCmd(String sessionId,String code) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        List<List<Object>> results = jdbcTemplate.query("{ exec BECCPREP_Liste_Depot ? }", new Object[]{code}, new ResultSetExtractor<List<List<Object>>>() {
            @Override
            public List<List<Object>> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<List<Object>> results = new ArrayList<>();
                RowMapper<?> rowMapper = null;
                while (rs.next()) {
                    List<Object> result = new ArrayList<>();
                    int columnCount = rs.getMetaData().getColumnCount();
                    System.out.println(columnCount);
                    if (columnCount == 2) {
                        rowMapper = new DepotCmdMapper();
                    } else if (columnCount == 3) {
                        rowMapper = new ArticleCmdMapper();
                    } else if (columnCount == 4) {
                        rowMapper = new ListeCmdMapper();
                    } else {
                        rowMapper = new RayonCmdMapper();
                    }
                    do {
                        result.add(rowMapper.mapRow(rs, rs.getRow()));
                    } while (rs.next());
                    results.add(result);
                }
                return results;
            }
        });
        return results;
    }



    public byte[] generateReport(String sessionId,String codeBP, Integer isDouble) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        try {
            String hexes = String.format("%08X", Integer.parseInt(codeBP.substring(2, 10).concat("3")));
            //String hexes = Convert.decimalToHexadecimal(Integer.parseInt(codeBP.substring(2, 10).concat("3")));
            InputStream jrxmlInputStream = getClass().getResourceAsStream("/report/prepaA5.jrxml");
            if (jrxmlInputStream == null) {
                throw new FileNotFoundException("Le fichier JRXML n'a pas été trouvé.");
            }
            JasperReport jasperReport = net.sf.jasperreports.engine.JasperCompileManager.compileReport(jrxmlInputStream);

            // Paramètres du rapport
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("code_BP", codeBP);
            parameters.put("isDouble", isDouble);
            parameters.put("hexa", hexes);

            // Générer le rapport
            byte[] reportBytes = JasperRunManager.runReportToPdf(jasperReport, parameters, jdbcTemplate.getDataSource().getConnection());

            return reportBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<EnCoursPrepBP> listBPEncours(String sessionId,int etat) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_BP_EnCours_Magasin ?",
                new EnCoursPrepBPMapper(), etat);
    }

    @Override
    public List<ListLigneBP> listLigneBP(String sessionId,String BP) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("select BPLARTICLE as article,rtrim(ARLIB) as libelle,rtrim(STEMPLOT) as lot,NLOTDATEPER as datePer,BPLEMP as empl,Sum(BPLQTE) as QTE from FBPL left join FSTEMP on STEMPAR=BPLARTICLE and STEMPDEPOT=BPLDEPOT and STEMPLETTRE=BPLLETTRE\n" +
                        "and BPLEMP=STEMPEMP\n" +
                        "left join VIEW_FAR_TOUS on ARCODE=BPLARTICLE left join FNLOT on NLOTAR=BPLARTICLE and NLOTCODE=STEMPLOT where BPLCODE=?\n" +
                        "group by BPLARTICLE,ARLIB,STEMPLOT,NLOTDATEPER,BPLEMP",
                new ListBPMapper(), BP);
    }

    @Override
    public List<DetailBP> detailBP(String sessionId,String BP) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("select  BPCODE,ETAT_PREPA_RAYON,RAYON_PREPARATION,DEBUT_PREPA_RAYON,FIN_PREPA_RAYON,DUREE_PREPA_RAYON,AGENT_PREPA_RAYON,QTE_TOTAL from VIEW_BP_DETAIL where BPCODE=? order by RAYON_PREPARATION",
                new DetailBPMapper(), BP);
    }

    @Override
    public List<DetailBP> detailBPCTRL(String sessionId,String BP) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("select BPCODE,ETAT_CONTROL,CONTROLEUR,DEBUT_CTRL,FIN_CTRL,DUREE_CTRL,sum(QTE_TOTAL) as QTE_TOTAL,NB_COLIS from VIEW_BP_DETAIL where BPCODE=? and isnull(CONTROLEUR,'')<>'' group by NB_COLIS,BPCODE,ETAT_CONTROL,CONTROLEUR,DEBUT_CTRL,FIN_CTRL,DUREE_CTRL",
                new DetailBPCTRLMapper(), BP);
    }

    @Override
    public EnCoursPrepBP infoEncoursBP(String sessionId,String BP) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.queryForObject(" select BPCODE,ETAT_BP,xEmp from VIEW_BP_ENCOURS left join xEMP_CTRL  on xCC_Encours=BPCODE where BPCODE=?",
                new InfoEncoursBPMapper(), BP);
    }

    @Override
    public int check_PP(String sessionId) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql="select count(*) from x_PP where isnull(PPIMPRIME,0)=0";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<pp_list> listPP(String sessionId, Date date_deb, Date date_fin) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_PP_List ?,?",
                new pp_listMapper(),date_deb,date_fin);
    }

    @Override
    public List<pp_list_det> listPPDet(String sessionId,String TPPCODE) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_PP_List_Det ?",
                new pp_list_detMapper(),TPPCODE);
    }


    public byte[] generateReport(String sessionId,String pp) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        try {
            String hexes = String.format("%08X", Integer.parseInt(pp.substring(2, 10).concat("7")));

            InputStream jrxmlInputStream = getClass().getResourceAsStream("/report/ppA5.jrxml");
            if (jrxmlInputStream == null) {
                throw new FileNotFoundException("Le fichier JRXML n'a pas été trouvé.");
            }
            JasperReport jasperReport = net.sf.jasperreports.engine.JasperCompileManager.compileReport(jrxmlInputStream);

            // Paramètres du rapport
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("code_BP", pp);
            parameters.put("hexa", hexes);
            // Générer le rapport
            byte[] reportBytes = JasperRunManager.runReportToPdf(jasperReport, parameters, jdbcTemplate.getDataSource().getConnection());
            return reportBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
