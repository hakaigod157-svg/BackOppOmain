package com.opham.prepa.repository.Apreparer;

import com.opham.prepa.model.Apreparer.*;
import com.opham.prepa.model.EnPreparation.DetailBP;
import com.opham.prepa.model.EnPreparation.EnCoursCMD;
import com.opham.prepa.model.EnPreparation.EnCoursPrepBP;
import com.opham.prepa.model.EnPreparation.ListLigneBP;
import com.opham.prepa.model.genererBP.ListeCmd;

import java.util.Date;
import java.util.List;

public interface CommandeRepository {


    List<Commande> findByDate(String sessionId,String groupe, Date dateliv);

    List<LigneCommande> findLigneCmdebyCode(String sessionId,String code);

    List<DetailPrep> listDetailBP(String sessionId,String code_CC);

    InfoCommande plusInfoCmd(String sessionId,String code_cc);

    int updateInfoCmd(String sessionId,InfoCommande in);



    List<List<Object>>   genererBP(String sessionId,String code);

    List<List<Object>> preparerCmd(String sessionId,String code);

    String insert_BP(String sessionId,String ids,String code_CC,String depot,String user_web);

   // String getId();

    int insertL6(String sessionId,ListeCmd l);

    byte[] generateReport(String sessionId,String codeBP, Integer isDouble);

    int atteTransfert(String sessionId,String code_CC, String article);

    List<EnCoursCMD> listEnCoursPrepa(String sessionId,int prepspecif,int isVisible,Date dateliv);

    void updateCCResume(String sessionId, String codeCC, String ip);
    void updateFrais(String sessionId,String codeCC);

    List<EnCoursPrepBP> listBPEncours(String sessionId,int etat);

    List<ListLigneBP> listLigneBP(String sessionId,String BP);
    List<DetailBP> detailBP(String sessionId,String BP);

    List<DetailBP> detailBPCTRL(String sessionId,String BP);

    EnCoursPrepBP infoEncoursBP(String sessionId,String BP);

    int check_PP(String sessionId);

    List<pp_list> listPP(String sessionId, Date date_deb, Date date_fin);


    byte[] generateReport(String sessionId, String pp);

    List<pp_list_det> listPPDet(String sessionId, String TPPCODE);
}
