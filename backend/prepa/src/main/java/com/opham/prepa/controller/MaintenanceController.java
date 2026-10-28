package com.opham.prepa.controller;
import com.opham.prepa.model.Maintenance.*;
import com.opham.prepa.service.MaintenanceService;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.opham.prepa.repository.Maintenance.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")

public class MaintenanceController {
    @Autowired
    private MaintenanceRepository mtnceRepo;

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping("/listDmdMntc")
    public ResponseEntity<List<DemandeMaint>> onListDemandeMtnce(@RequestHeader("Authorization") String sessionId)
    {
        try {
            List<DemandeMaint> demande = mtnceRepo.getDemandeMaintenance(sessionId);
            if (demande.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(demande, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/listMtnce")
    public ResponseEntity<List<Maintenance>> onListMtnce(
            @RequestHeader("Authorization") String sessionId
            ) {
        try {
            List<Maintenance> mtnce = mtnceRepo.getMaintenance(sessionId);
            if (mtnce.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(mtnce, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/demandes/search")
    public ResponseEntity<List<DemandeMaint>> searchDemandes(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "") String marque
    ) {
        return ResponseEntity.ok(
                maintenanceService.searchDemandes(
                        sessionId,
                        search,
                        marque
                )
        );
    }

    @PostMapping("/valider")
    public ResponseEntity<Void> validerDemande(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String demandeCode,
            @RequestParam Integer kmVoiture,
            @RequestParam String classification
    ) {
        maintenanceService.validerDemande(sessionId, demandeCode,kmVoiture,classification);
        return ResponseEntity.ok().build();
    }

@PostMapping("/refuser")
    public ResponseEntity<Void> refuserDemande(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String demandeCode
    ) {
        maintenanceService.refuserDemande(sessionId, demandeCode);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/suppr")
    public ResponseEntity<Void> supprimerDemande(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String demandeCode
    ) {
        maintenanceService.supprDmd(sessionId, demandeCode);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/update")
    public ResponseEntity<Void> updateDemande(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String demandeCode,
            @RequestBody DemandeMaint demande
    ) {
        maintenanceService.updateDemande(
                sessionId,
                demandeCode,
                demande.getDEMANDE_DATE(),
                demande.getDEMANDE_VOITURE(),
                demande.getDEMANDE_MARQUE(),
                demande.getDEMANDE_TYPE(),
                demande.getDEMANDE_AUTEUR(),
                demande.getDEMANDE_DESCR()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dmdCode")
    public ResponseEntity<String> getNextCode( @RequestHeader("Authorization") String sessionId) {
        String codeGenere = maintenanceService.creerDemandeCode(sessionId);
        return ResponseEntity.ok(codeGenere);
    }

    @GetMapping("/getDemandeByCode")
    public ResponseEntity<DemandeMaint> getDemandbyCode( @RequestHeader("Authorization") String sessionId , @RequestParam String demandeCode) {
        try {
            DemandeMaint demande = maintenanceService.getDmdbyCode(sessionId,demandeCode);
            if (demande == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(demande, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/enregistrer")
    public ResponseEntity<Void> saveDmd(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String demandeCode,
            @RequestParam String demandeVoiture,
            @RequestParam String demandeMarque,
            @RequestParam String demandeType,

            @RequestParam String demandeDescr
    ) {
        maintenanceService.enregistrDmd(sessionId,demandeCode,demandeVoiture,demandeMarque,demandeType,demandeDescr);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/allVoiture")
    public ResponseEntity<List<Voiture>> getVoiture(@RequestHeader("Authorization") String sessionId){

    List<Voiture> voitrKm = mtnceRepo.getVoiture(sessionId);
        if (voitrKm.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(voitrKm, HttpStatus.OK);
    }
    @GetMapping("/getVoitureTokana")
    public ResponseEntity<Voiture> getVoitureTokana(@RequestHeader("Authorization") String sessionId,@RequestParam("VOIT_NUM") String numVoiture){

    Voiture voitrKm = mtnceRepo.getVoitureByNum(sessionId,numVoiture);
        if (voitrKm.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(voitrKm, HttpStatus.OK);
    }

    @GetMapping("/getKmVoit")
    public ResponseEntity<Integer> getVoitureKm(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam("VOIT_NUM") String numVoiture) {

        Integer km = mtnceRepo.getVoitureLastKm(sessionId, numVoiture);
        if (km == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(km);
    }

    @GetMapping("/getEnumerat")
        public ResponseEntity<List<Enumerat>> getEnumerat(@RequestHeader ("Authorization") String sessionId , @RequestParam ("ENUM_VALEUR") String enumValeur){
            List<Enumerat> enumerate = mtnceRepo.getEnum(sessionId , enumValeur);
            if (enumerate.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(enumerate, HttpStatus.OK);
        }

     @GetMapping("/getTypeMaint")
            public ResponseEntity<List<Enumerat>> getTypeMaint(@RequestHeader ("Authorization") String sessionId ){
                List<Enumerat> enumerate = mtnceRepo.getTypeMaintenance(sessionId );
                if (enumerate.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(enumerate, HttpStatus.OK);
            }


    @GetMapping("/getClassification")
    public ResponseEntity<List<Enumerat>> getClassification(@RequestHeader ("Authorization") String sessionId ){
        List<Enumerat> enumerate = mtnceRepo.getTypeClassific(sessionId);
        if (enumerate.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(enumerate, HttpStatus.OK);
    }


    @GetMapping("/getEtapeListe")
    public ResponseEntity<List<Etape>>getListEtape(
            @RequestHeader ("Authorization") String sessionId ,
            @RequestParam ("NATURE") String natureEtape,
            @RequestParam ("CLASSIFICATION") String classification)
    {
        List<Etape> etapeList = mtnceRepo.getEtapesParClass(sessionId,natureEtape,classification);
        if (etapeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(etapeList, HttpStatus.OK);
    }

    @GetMapping("/etapeEnCours")
    public ResponseEntity<List<DemandeStatut>>getEtapeEnCours( @RequestHeader ("Authorization") String sessionId ,@RequestParam("DEMANDE_CODE") String demandeCode){
       List<DemandeStatut> farany = mtnceRepo.getDemandeStatus(sessionId,demandeCode);
        return new ResponseEntity<>(farany, HttpStatus.OK);
    }

    @PostMapping("/validEtape")
    public ResponseEntity<Void>validEtape(@RequestHeader ("Authorization") String sessionId, @RequestParam("DEMANDE_CODE") String demandeCode){
        mtnceRepo.validerEtape(sessionId,demandeCode);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/terminerDmd")
    public ResponseEntity<Void> fermerDemande(@RequestHeader ("Authorization") String sessionId, @RequestParam("DEMANDE_CODE") String demandeCode) {
        mtnceRepo.fermerDemande(sessionId, demandeCode);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getHistorique")
    public ResponseEntity<List<Historique>>getHistorique( @RequestHeader ("Authorization") String sessionId ,@RequestParam("DEMANDE_CODE") String demandeCode){
        List<Historique> farany = mtnceRepo.getHistoriqueDmd(sessionId,demandeCode);
        if (farany.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(farany, HttpStatus.OK);
    }

    @GetMapping("/getPrepaMaint")
    public ResponseEntity<List<EtapeLigne>> getPrepaMaint(@RequestHeader("Authorization")String sessionId, @RequestParam("MAINT_CODE_LIGNE") String maintCode){
        List<EtapeLigne> prepa = mtnceRepo.getEtapePrepa(sessionId,maintCode);
                if(prepa.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(prepa,HttpStatus.OK);
     }


    @GetMapping("/getRealMaint")
    public ResponseEntity<List<Realisation>> getRealMaint(@RequestHeader("Authorization")String sessionId, @RequestParam("MAINT_CODE_REAL") String maintCode){
        List<Realisation> realMadrid = mtnceRepo.getEtapeReal(sessionId,maintCode);
         if(realMadrid.isEmpty()){
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
         return new ResponseEntity<>(realMadrid,HttpStatus.OK);
    }


    /////////////////////
    @PostMapping("/validerEtapePrepa")
    public ResponseEntity<?> validerEtapePrepa(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam("MAINT_CODE_LIGNE") String maintCode,
            @RequestParam("MAINT_ETAPE_LIGNE") String etapeLigne)
            {
        mtnceRepo.validerEtapePrepa(sessionId, maintCode, etapeLigne);
        return ResponseEntity.ok(Collections.singletonMap("success", true));
    }

    @PostMapping("/validerEtapeReal")
    public ResponseEntity<?> validerEtapeReal(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam("MAINT_CODE_REAL") String maintCode,
            @RequestParam("MAINT_INTERV_REAL") String intervReal,
            @RequestParam(value = "MAINT_ACTION_REAL", required = false) String action,
            @RequestParam(value = "MAINT_FNR_REAL", required = false) String fnr,
            @RequestParam(value = "MAINT_PRIX_REAL", required = false) Double prix,
            @RequestParam(value = "MAINT_MRAQUE_MARQUE", required = false) String marque,
            @RequestParam(value = "MAINT_DATE_REAL", required = false) String dateReal) {
        mtnceRepo.validerEtapeReal(sessionId, maintCode, intervReal, action, fnr, prix, marque, dateReal);
        return ResponseEntity.ok(Collections.singletonMap("success", true));
    }

    @PostMapping("/addEtapeReal")
    public ResponseEntity<?> addEtapeReal(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam("MAINT_CODE_REAL") String maintCode,
            @RequestParam("MAINT_INTERV_REAL") String intervReal,
            @RequestParam(value = "MAINT_DATE_PREVU", required = false) String datePrevu,
            @RequestParam(value = "MAINT_ACTION_REAL", required = false) String action,
            @RequestParam(value = "MAINT_FNR_REAL", required = false) String fnr,
            @RequestParam(value = "MAINT_PRIX_REAL", required = false) Double prix,
            @RequestParam(value = "MAINT_MRAQUE_MARQUE", required = false) String marque) {
        mtnceRepo.addEtapeReal(sessionId, maintCode, intervReal, datePrevu, action, fnr, prix, marque);
        return ResponseEntity.ok(Collections.singletonMap("success", true));
    }
}




