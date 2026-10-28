package com.opham.prepa.repository.Maintenance;

import com.opham.prepa.model.Maintenance.*;

import java.util.Date;
import java.util.List;

public interface MaintenanceRepository {

    List<Voiture> getVoiture(String sessionId);

    List<DemandeMaint> searchDemandes(
            String sessionId,
            String search,
            String marque
    );

    List<DemandeMaint> getDemandeMaintenance(
            String sessionId
//            String codeDemande
    );

    List<Maintenance> getMaintenance(
            String sessionId
    );

    String creerDemandeCode(String sessionId);

    String creeMtnceCode(String sessionId);


  void validerDemande(String sessionId, String demandeCode , Integer voitureKm , String classification);

    void enregistrDmd(String sessionId, String demandeCode, String demandeVoiture, String demandeMarque, String demandeType, String demandeDescr);

    void updateDemande(
            String sessionId,
            String demandeCode,
            Date demandeDate,
            String demandeVoiture,
            String demandeMarque,
            String demandeType,
            String demandeAuteur,
            String demandeDescr
    );

    void refuserDemande(String sessionId, String demandeCode);

    void supprDmd(String sessionId, String demandeCode);

    DemandeMaint getDmdbyCode(String sessionId, String demandeCode);

    Voiture getVoitureByNum(String sessionId, String numVoiture);

    Integer getVoitureLastKm(String sessionId, String numVoiture);



    List<Enumerat>getEnum(String sessionId, String enumValeur);

    List<Enumerat>getTypeMaintenance(String sessionId);

    List<Enumerat>getTypeClassific(String sessionId);

    List<Etape> getEtapesParClass(String sessionId, String natureEtape , String classification);

    void validerEtape(String sessionId, String demandeCode);

    void fermerDemande(String sessionId, String demandeCode);

    //String getLastEtape(String sessionId, String demandeCode);

    List<DemandeStatut> getDemandeStatus(String sessionId, String demandeCode);

    List<Historique> getHistoriqueDmd(String sessionId, String demandeCode);

    List<EtapeLigne> getEtapePrepa(String sessionId, String maintCode);

    List<Realisation> getEtapeReal(String sessionId, String maintCode);

   // void validerEtapePrepa(String sessionId, String maintCode, String etapeLigne, String intervenant);
    void validerEtapePrepa(String sessionId, String maintCode, String etapeLigne);

    void validerEtapeReal(String sessionId, String maintCode, String intervReal,
                          String action, String fnr, Double prix, String marque, String dateReal);

    void addEtapeReal(String sessionId, String maintCode, String intervReal,
                      String datePrevu, String action, String fnr, Double prix, String marque);

    /** DemandeMaint getDmdbyCode(String sessionId,String demandeCode); */
}