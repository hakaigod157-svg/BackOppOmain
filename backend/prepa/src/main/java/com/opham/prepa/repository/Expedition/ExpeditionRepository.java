package com.opham.prepa.repository.Expedition;

import com.opham.prepa.model.Expedition.*;
import com.opham.prepa.model.Utils.LoginPlus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ExpeditionRepository {
    int insertTransFacture(String sessionId,Trans_facture T);

    int updateTransFacture(String sessionId,Trans_facture T);

    List<Trans_Attribution_Lv> findAttribLv(String sessionId, String transporteur, Date datedeb, Date datefin);

    int insertTransFacture_L(String sessionId,Trans_facture_L L);
    List<Trans_facture_L> listTrans_Fact_L(String sessionId,String code);

    void deleteTrans_Fact(String sessionId,String liv,String code);

    int test_Attrib_Liv(String sessionId,String liv,String transport);

    int updateTransValide(String sessionId, String trans_code);

    List<Trans_Detail_Lv> listDetailLv(String sessionId, String num_code, int type);

    List<Trans_Detail_Nb_cmd> listDetailNbCmd(String sessionId, String code_ft, int type);

    List<Trans_Detail_Lv> ajoutLv(String sessionId,String lv);

    List<List<Object>> listLivraison(String sessionId ,String modeLiv);


    List<edition_lv> listEditionLv(String sessionId, String livraison, String commande, Date date, String chauf, String voit, Integer saisie);
    byte[] genererLV(String sessionId, String code);

    List<params_liv> listparamsLv(String sessionId , String code_client);

    byte[] genererCourse(String sessionId, String code);

    void deleteParams(String sessionId, String code_client, int jour, int heure);

    int insert_params(String sessionId, String code_client, int jour, int heure);

    List<DureeLV> getDureeLv(String sessionId,String code);

    int onInsertDuree(String sessionId, String code, String code_client, Timestamp h_arrive, Timestamp h_depart);

    int onInfoSaisiLV(String sessionId, String code, int remplissage, int cpt_arrive,int cpt_depart,int saisie);

    int onSupprLV(String sessionId, String code,String liv,String raison,String user_annul);

    List<List<Object>> listattribAxe(String sessionId, String code_client);

    List<Situation_axe> situationparAxe(String sessionId);

    int onUpdateAxe(String sessionId, int seq, String axe);

    TestAvAddCmdLiv ontestAvAddCmdLiv(String sessionId, String cmd, int isCc);

    int onAddCmdLiv(String sessionId, String cmd ,String liv,String raison);

    int onUpdateModeLiv(String sessionId, int seq, String mode_livraison);

    List<params_liv_adr> listparamsLv_adr(String sessionId, int seq);

    int insert_paramsAdr(String sessionId, int seq, String code_client, int jour, int heure);

    void deleteParamsAdr(String sessionId, int seq, String code_client, int jour, int heure);

    List<Chauf> onListChauf(String sessionId, String  type);

    List<VoitureCh> onVoitureCh(String sessionId);

    List<ListAtteLiv> onListAtteLiv(String sessionId, String mode);

    boolean isLiv(String sessionId, String CC);
    boolean isAtteLiv(String sessionId, String CC);

    List<List<Object>> listIndLivraison(String sessionId, Date date_deb, Date date_fin, String modeLiv);
    createlv onCreateLv(String sessionId, String chauf, String voit,int etat,String course,String livreur,String axe);

    int updateLivCmd(String sessionId,String cmd,String codeLiv);

    List<List<Object>> listIndChauffeur(String sessionId);

    Map<String, Object> onIndicatChauf(String sessionId);

    List<ListCmdLivAsuppr> onSupprLVCmd(String sessionId, String liv,String client);

    List<LoginPlus> onLivreurOccupe(String sessionId);

    List<proximite> onlistProximite(String sessionId,String type);

    List<detail_editionLv> detailEditionLv(String sessionId, String mode, String sit_com, String axe);

    int onInsertVoitCh(String sessionId, String voit, String chauf);

    int onDelVoitCh(String sessionId,String seq);

    boolean onTestDispo(String sessionId, String voit,String chauf);
}
