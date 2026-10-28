package com.opham.prepa.repository.Transfert;

import com.opham.prepa.model.Transfert.*;

import java.util.Date;
import java.util.List;

public interface TransfertRepository {
    List<List<Object>> listRotation(String sessionId, String depot, String alle);

    List<List<Object>> listColisIncomplet(String sessionId, String depotOrg, String depotDest, String alle);

    List<List<Object>> listPetitColis(String sessionId, String depotOrg, String depotDest, String alle);

    List<List<Object>> listRangement(String sessionId, String depot);

    int insertL6_Transfert(String sessionId, Transfert t);

    int insertL6_TransfertConseil(String sessionId, TransfertConseilReappro t);

    String insert_FSIL(String sessionId, String ids, String commentaire, String depOrg, String depDest,String user_web);


    ProblemeStock testStock(String sessionId, String article, int qte, String depot, String lettre, String empl);

    int stockPasVide(String sessionId, String depot_Dest, String empl_Dest);

    byte[] generateReportTransfert(String sessionId, String td, String org, String dest, Integer isDouble, String users);

    List<List<Object>> conseilReappro(String sessionId, String article, String depotOrg, String depotDest);

    List<SuiviTDDetail> listSuiviTDDetail(String sessionId, String code, String depOrg, String depDest, Date datedeb, Date datefin, String article, int detail, int etat);

    List<SuiviTD> listSuiviTD(String sessionId, String code, String depOrg, String depDest, Date datedeb, Date datefin, String article, int detail, int etat);


}
