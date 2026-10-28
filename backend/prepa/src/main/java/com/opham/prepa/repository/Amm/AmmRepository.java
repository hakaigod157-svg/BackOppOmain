package com.opham.prepa.repository.Amm;
import com.opham.prepa.model.Amm.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AmmRepository {
    List<fabricant> findFabricant(String sessionId, String libelle);

    int updateLibelleFabricant(String sessionId, String libelle_update, String code_update);

    int insertFabricant(String sessionId, String codeFab, String libelleFab);

    List<dossier> findDossier(String sessionId,String labo);

/*Si listDossier prend 2 valeurs*/
    /*List<List<Object>> findDossier(String sessionId); */

    int insertLigneDossier(String sessionId, String codeAmm, String codeArticle, String libelle,String fab_code, String focode);

    int insertAmmDossier(String sessionId, String codeAmm, String responsable, String type,String classification,String ref_variation);

    List<List<Object>> findnumdossier(String sessionId ,String prefixe, String nom_table);

    List<ListTitulaire> findTitulaire(String sessionId);

    int updateDossier(String sessionId, String dossierCode, String type_update, String respo_update, String class_update);

    List<List<Object>> findArticleDetail(String sessionId, String dossier);

    List<etapeList> findEtape(String sessionId, String ammCode);

    int insertAmmEtape(String sessionId, String codeAmm, String etape, int etat , Date dateNew);

    List<fournirList> findFournir(String sessionId, String ammCode);

    int insertFournir(String sessionId, String codeAmm, String codeDossier ,Date date_fournir);

    int updateEtape(String sessionId, Date dateUpdate,String ds_code,  String etape);

    int insertHisto(String sessionId, String codeAmm, Date date, String commentaire);

    int updateFournir(String sessionId, Date dateUpdate, String ds_code, String dossierLcode);

    List<AllDossierList> findalllistdossier(String sessionId);

    int insertdocfournir(String sessionId, String dossier_code, String dossier_lib);

    int updateAmmDossier(String sessionId, String ds_code, int decision , Date date, String text);

    int insertDossierDefaut(String sessionId, String type_dossier,String code_dossier, String classification);

    List<allListGen> findalllistgen(String sessionId);

    List<allListSpe> findalllistspe(String sessionId);

    void deleteFournir(String sessionId, String dossier_code);

    void deleteDossierDefaut(String sessionId, String classification, String ds_code , String type_dossier);

    List<motifDetail> findMotif(String sessionId, String ammCode);

    List<listnumDS> findnumDS(String sessionId, String dossier, int etat);

    List<listnumFB> findnumFB(String sessionId, String dossier, int etat);

    List<typeReclam> findTypeReclam(String sessionId);

    int insertReclam(String sessionId, String code_ds, String reclamation_code, Date date_reclam,String insertReclam);

    int updateammreclam(String sessionId, String ds_code ,Date date_reclam);

    List<reclamationDetail> findDetailReclam(String sessionId,String ammCode);

    List<list_amm_dossier> findlistAmmDossier(String sessionId, String ammCode);

    int updateReclam(String sessionId, Date dateUpdate, String ds_code ,String code_lib,Integer annule_reclam);

    List<ListRespo> findListRespo(String sessionId);

    int updateAmmLigneArticle(String sessionId, String ds_code, String num_amm, Date date_amm, int seq);

    int updateCodeArticle(String sessionId, String ds_code, String codeArticle, int seq);

    List<ListArticleLier> findListArticleLier(String sessionId, String titulaire , int type);

    int insertLigneVariation(String sessionId, String codeAmm, String libelle_variation);

    int insertVariation(String sessionId, String codeAmm, String libelle_variation);

    int import_midac(String sessionId, Midac t);

    boolean importMoisExist(String sessionId,int date,int mois);

    List<List<Object>> findlistMidac(String sessionId);

    //Repository update FAR
    int updateFarMidac(String sessionId,String code_pro,String codeArticle);

    int updateImage(String sessionId, int seq, byte[] file);

    List<list_titulaire_amm> findListTitulaire(String sessionId);

    int insertAmmReclam(String sessionId, String ds_code, String code_reclam, Date date_reclam, String nb_reclam);

    List<amm_renouveler> findListArenouveler(String sessionId, int type);

    Object findFicheArtWork(String sessionId, String amm_ds_code, String ligne_code_article, String ligne_libelle_article);

    List<Abrogation> findAbrogation(String sessionId);

    int updateDateAbrogation(String sessionId, LocalDate dateValidation, String codeArticle);
}
