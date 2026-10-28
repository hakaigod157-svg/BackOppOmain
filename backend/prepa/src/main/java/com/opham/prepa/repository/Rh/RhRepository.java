package com.opham.prepa.repository.Rh;

import com.opham.prepa.model.Rh.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface RhRepository {


    List<DemandePers> findAllListDemandePers(String sessionId, Integer etat);
    List<List<Object>> findAllDetailRecru(String sessionId, String coderecu);
    List<List<Object>> findAllCvRecru(String sessionId, String recrut);


    List<CV> findAllCvGlobal(String sessionId);

    List<DepartementPoste> findAllDepartementPoste(String sessionId);
    List<Offre> findAllOffre(String sessionId, String coderecr);
    List<Cabinet> findAllCabinet(String sessionId);
    List<List<Object>> findcodenouveauDemande(String sessionId, String prefixe, String table);
    List<Recrutement> findAllListRecrutement(String sessionId);
    int insererDemande(DemandePers d, byte[] fiche, String sessionId) throws IOException;
    int updateDemande(DemandePers d, String sessionId) throws IOException;
    int insererCV(CV c, byte[] cv, byte[] lm, String sessionId) throws IOException;
    int insererLigneCV(CV c, String sessionId) throws IOException;
    int insererOffre(Offre o, byte[] piece, String sessionId) throws IOException;

    int updateOffre(Offre o, byte[] piece, String sessionId);

    int updateCV(CV c, byte[] cv, byte[] motivation, String sessionId);

    int updateDemandeToApprouve(String sessionId, String codeDemande);
    int updateDemandeToRefuser(String sessionId,String codeDemande);
    int updateCvSelection(String sessionId, String recrut, String cv);
    int updateCvEntretien(String sessionId, String recrut, String cv);
    int updateDateCvEntretien(String sessionId, java.sql.Timestamp date, String recrut, String cv);

    int deleteOffre(String sessionId, String codeOffre, String entite, String codeRecru);

    int deleteDemande(String sessionId, String codeDemande);

    int onUpdatevalidation(String sessionId, String recrut, String cv, Date dateValidation ,String categorie);

    int onUpdatedateValidation(String sessionId, Timestamp sqlDate, String recrut, String cv);

    int updateCvDateProposition(String sessionId, String recrut, String cv);

    int onUpdatedateProposition(String sessionId, Timestamp sqlDate, String recrut, String cv);

    int insertCVtest(String sessionId, String cv_code, String recru_code, Timestamp cv_ligne_date_test, Timestamp cv_ligne_date_entretien);

    List<List<Object>> findAllListParams(String sessionId, String departement_code, String poste_code);

    void deleteParams(String sessionId, String dep, String code_poste, String categorie, String materiel);

    int insert_params(String sessionId, String dep, String code_poste, String categorie, String materiel);

    List<List<Object>> findAllListEmployes(String sessionId, Integer etat);

    int updateMatricule(String sessionId, String matricule, String code_recrutement);

    List<List<Object>> findAllListIntegration(String sessionId);

    int insertEmploye(String sessionId, String empl_matricule, String empl_nom, String empl_prenom, String empl_date_naiss, String empl_departement, String empl_poste, String empl_contrat, String empl_prise_fonction, String empl_date_fin_essai1, String empl_date_fin_essai2);

    int updateEmploye(String sessionId, String empl_matricule, String empl_nom, String empl_prenom, String empl_date_naiss, String empl_departement, String empl_poste, String empl_contrat, String empl_prise_fonction, String empl_date_fin_essai1, String empl_date_fin_essai2);

    int deleteEmploye(String sessionId, String empl_matricule);

    List<List<Object>> findAllDetailIntegr(String sessionId, String codeInteg ,String matricule);

    int updateDateEtape(String sessionId, String ligne_etape_code, String ligne_etape_etape, Date ligne_etape_date);

    int updateDateMatDispo(String sessionId, String mise_dispo_matr, String mise_dispo_cat, String mise_dispo_materiel, Date dateEffect);

    List<Categorie> findAllListCategorie(String sessionId);

    int updateDateFinCdd(String sessionId, String demandeCode, int moisFinCDD);

    Map<String, Object> findAllListParamsFormation(String sessionId, String departement_code, String poste_code);

    int insert_paramsFormation(String sessionId, String form_code, String code_poste, String dep);

    void deleteParamsFormation(String sessionId, String form_code, String code_poste, String dep);

    int updateDateForm(String sessionId, String matricule, String form_code, String libelle_form, Date date);
}
