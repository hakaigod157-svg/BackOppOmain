package com.opham.prepa.repository.Utils;

import com.opham.prepa.model.Utils.*;

import java.util.Date;
import java.util.List;

public interface UtilsRepository {

    List<String> findAllAxe(String sessionId);

    List<String> findParCode(String sessionId, String code);

    Alerte getAlerte(String sessionId, String code);

    Etape getEtape(String sessionId);

    String getId(String sessionId);
    Lock getLock(String sessionId, String xTable, String xCode, int isClose);

    Droit getDoit(String sessionId, String xUser, String access);

    List<Droit> listDroit(String sessionId, String xUser);

    List<Depot> listDepot(String sessionId, String critere);

    List<Alle> listAlle(String sessionId, String depot, String critere);

    List<Article> listArticle(String sessionId, String critere);

    List<Labo> listLabo(String sessionId, String critere);

    List<Famille> listFamille(String sessionId);

    List<Client> listClient(String sessionId);

    int InsertAnnuaire(String sessionId, String nom, String code, String type, String numero, String user_cree);

    int updateName(String sessionId, String numero);

    //code de capa
    void insertFNCPS_USER(String sessionId,String ncpsuserusercode,String ncpsuserncpscode);
    List<Userinsert> listUserinsert(String sessionId,String ncpsuserncpscode);
    List<CauseRacine> findcauseraccode(String sessionId,String code_causerac);
    void insertFNCPS_CAUSE(String sessionId,String causeRacCode, String causeRacDescription, Date causeRacDate, int causeRacValid, String causeRacProcImput);
    void updateFCAUSE_RAC(String sessionId,int causeRacSeq, String causeRacCode, Date causeRacDate, String causeRacDescription, String causeRacProcImput, int causeRacValide);
    void deleteFCAUSE_RAC(String sessionId,int causeracseq);
    List<Proc> listProc(String sessionId,String enumType);
    List<String> findallactiontype(String sessionId);
    void insertAction(String sessionId,String actionNcpscode, int actionValide, int actionMiseappli, String actionDescription,int actionCausesseq, int actionType);
    List<String> findallemetteur(String sessionId);
    List<Proc> listEnum(String sessionId);
    List<String> findalldpt(String sessionId);
    List<String> findalldpt2(String sessionId);
    List<String> findalluser(String sessionId);

     List<Reglement> listReglement(String sessionId, String critere);

     List<AccordADV> listAccordADV(String sessionId);

    List<String> findAllTarif(String sessionId);

    List<Transporteur> listTransporteur(String sessionId, String critere);

    String getCode(String sessionId,String table,int cpt);

    List<LoginPlus> onLoginPlus(String sessionId, String critere);

    String getCCDepBP(String sessionId, String BP);

    String hextoCode( String hex ,String x);


}
