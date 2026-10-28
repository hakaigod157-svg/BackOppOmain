package com.opham.prepa.controller;

import com.opham.prepa.Utils.FileParser;
import com.opham.prepa.model.Expedition.Chauf;
import com.opham.prepa.model.Utils.*;
import com.opham.prepa.repository.Utils.UtilsRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//
@RestController

@RequestMapping("/api")
public class UtilsController {

    @Autowired
    UtilsRepository utilsRepository;

    @GetMapping("/axe")
    public ResponseEntity<List<String>> listAxe(@RequestHeader("Authorization") String sessionId) {
        try {
            List<String> cmd = utilsRepository.findAllAxe(sessionId);

            if (cmd == null || cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tarif")
    public ResponseEntity<List<String>> listTarif(@RequestHeader("Authorization") String sessionId) {
        try {
            List<String> cmd = utilsRepository.findAllTarif(sessionId);

            if (cmd == null || cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/enumeration")
    public ResponseEntity<List<String>> listEnumeration(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String code) {
        try {
            List<String> cmd = utilsRepository.findParCode(sessionId, code);

            if (cmd.isEmpty() || cmd == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/timing")
    public ResponseEntity<Alerte> getAlerte(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String code) {
        try {
            Alerte cmd = utilsRepository.getAlerte(sessionId, code);

            if (cmd != null) {
                return new ResponseEntity<>(cmd, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/new-id")
    public ResponseEntity<String> getNewId(@RequestHeader("Authorization") String sessionId) {
        try {
            String id = utilsRepository.getId(sessionId);
            if (id.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/etape")
    public ResponseEntity<Etape> getEtape(@RequestHeader("Authorization") String sessionId) {
        try {
            Etape cmd = utilsRepository.getEtape(sessionId);

            if (cmd != null) {
                return new ResponseEntity<>(cmd, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/lock")
    public ResponseEntity<Lock> getLock(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String xTable, @RequestParam(required = true) String xCode, @RequestParam(required = true) int isClose) {
        Lock lock = utilsRepository.getLock(sessionId, xTable, xCode, isClose);
        if (lock != null) {
            return new ResponseEntity<>(lock, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/droit")
    public ResponseEntity<Droit> getDroid(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String xUser, @RequestParam(required = true) String access) {
        try {
            Droit cmd = utilsRepository.getDoit(sessionId, xUser, access);

            if (cmd != null) {
                return new ResponseEntity<>(cmd, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listDroit")
    public ResponseEntity<List<Droit>> listDroit(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String xUser) {

        try {
            List<Droit> droits = utilsRepository.listDroit(sessionId, xUser);
            if (droits.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(droits, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des droits pour l'utilisateur: " + xUser);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listDepot")
    public ResponseEntity<List<Depot>> listDepot(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String critere) {
        try {
            List<Depot> cmd = utilsRepository.listDepot(sessionId, critere);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listReglement")
    public ResponseEntity<List<Reglement>> listReglement(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String critere) {
        try {
            List<Reglement> cmd = utilsRepository.listReglement(sessionId, critere);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listAlle")
    public ResponseEntity<List<Alle>> listDepot(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String depot, @RequestParam(required = false) String critere) {
        try {
            List<Alle> cmd = utilsRepository.listAlle(sessionId, depot, critere);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listServer")
    public ResponseEntity<List<String>> listNames() {
        try {
            FileParser fileParser = new FileParser();

            List<String> names = fileParser.extractNames("interfaces");

            if (names.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(names, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listArticle")
    public ResponseEntity<List<Article>> listArticle(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String critere) {
        try {
            List<Article> cmd = utilsRepository.listArticle(sessionId, critere);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listLabo")
    public ResponseEntity<List<Labo>> listLabo(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String critere) {
        try {
            List<Labo> cmd = utilsRepository.listLabo(sessionId, critere);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listAccordADV")
    public ResponseEntity<List<AccordADV>> listAccordADV(@RequestHeader("Authorization") String sessionId) {
        try {
            List<AccordADV> cmd = utilsRepository.listAccordADV(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listFamille")
    public ResponseEntity<List<Famille>> listFamille(@RequestHeader("Authorization") String sessionId) {
        try {
            List<Famille> cmd = utilsRepository.listFamille(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/local-ip")
    public String getLocalIPAddress(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        return ipAddress;
    }

    @GetMapping("/listClient")
    public ResponseEntity<List<Client>> listClient(@RequestHeader("Authorization") String sessionId) {
        try {
            List<Client> cmd = utilsRepository.listClient(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/InsertAnnuaire")
    public ResponseEntity<String> InsertAnnuaire(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String nom, @RequestParam(required = true) String code, @RequestParam(required = true) String type, @RequestParam(required = true) String numero, @RequestParam(required = true) String user_cree) {

        try {
            int rowsAffected = utilsRepository.InsertAnnuaire(sessionId, nom, code, type, numero, user_cree);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("update annuaire avec succès");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'update Annuaire ");
            }
        } catch (DataAccessException e) {
            // Log the exception details

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour");
        }

    }

    @PostMapping("/Update_name")
    public ResponseEntity<String> updateName(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String numero) {

        try {
            int rowsAffected = utilsRepository.updateName(sessionId, numero);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("resultat update name ok  avec succès");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'update");
            }
        } catch (DataAccessException e) {
            // Log the exception details

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour");
        }

    }

    //debut code de capa :
    //code de cause racine :
    @GetMapping("/causeRac")
    public ResponseEntity<List<CauseRacine>> listCauseRac(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String code_causerac) {
        try {
            List<CauseRacine> causeRacList = utilsRepository.findcauseraccode(sessionId,code_causerac);

            if (causeRacList == null || causeRacList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(causeRacList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insertcauserac")
    public void insertFNCPS_CAUSE(@RequestHeader("Authorization") String sessionId,
            @RequestParam(value = "causeRacCode", required = true) String causeRacCode,
            @RequestParam(value = "causeRacDescription", required = true) String causeRacDescription,
            @RequestParam(value = "causeRacDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date causeRacDate,
            @RequestParam(value = "causeRacValid", required = false, defaultValue = "0") int causeRacValid,
            @RequestParam(value = "causeRacProcImput", required = false) String causeRacProcImput) {

        utilsRepository.insertFNCPS_CAUSE(sessionId,causeRacCode, causeRacDescription, causeRacDate, causeRacValid ,causeRacProcImput);
    }

    //code de mis a jour :
    @PutMapping("/updatecuserac")
    public void updateFCAUSE_RAC(@RequestHeader("Authorization") String sessionId,@RequestParam(value = "causeRacSeq", required = true) int causeRacSeq,
                                 @RequestParam(value = "causeRacCode", required = false) String causeRacCode,
                                 @RequestParam(value = "causeRacDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date causeRacDate,
                                 @RequestParam(value = "causeRacDescription", required = false) String causeRacDescription,
                                 @RequestParam(value = "causeRacProcImput", required = false) String causeRacProcImput,
                                 @RequestParam(value = "causeRacValide", required = false) int causeRacValide) {
        utilsRepository.updateFCAUSE_RAC(sessionId,causeRacSeq,causeRacCode,causeRacDate,causeRacDescription,causeRacProcImput,causeRacValide);
    }

    @DeleteMapping("/deletecauserac")
    public void deleteFCAUSE_RAC(@RequestHeader("Authorization") String sessionId,
            @RequestParam(value = "causeracseq", required = true) int causeracseq) {

        utilsRepository.deleteFCAUSE_RAC(sessionId,causeracseq);
    }

    //fin code de cause racine


    @PostMapping("/insertuser")
    public void insertFNCPS_USER(@RequestHeader("Authorization") String sessionId,
            @RequestParam(value = "ncpsUserUserCode", required = true) String ncpsUserUserCode,
            @RequestParam(value = "ncpsUserNcpsCode", required = false) String ncpsUserNcpsCode) {

        utilsRepository.insertFNCPS_USER(sessionId,ncpsUserUserCode, ncpsUserNcpsCode);
    }

    @GetMapping("/listuserconsult")
    public ResponseEntity<List<Userinsert>> listUserinsert(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String ncpsUserNcpsCode) {
        try {
            List<Userinsert> userList = utilsRepository.listUserinsert(sessionId,ncpsUserNcpsCode);

            if (userList == null || userList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("General Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/processus")
    public ResponseEntity<List<Proc>> listProc(@RequestHeader("Authorization") String sessionId,@RequestParam(required = false) String enumType) {
        try {
            if (enumType == null || enumType.isEmpty()) {
                enumType = "Processus";
            }
            List<Proc> procList = utilsRepository.listProc(sessionId,enumType);

            if (procList == null || procList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(procList, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("General Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/actiontype")
    public ResponseEntity<List<String>> listActionType(@RequestHeader("Authorization") String sessionId ) {
        try {
            List<String> cmd = utilsRepository.findallactiontype(sessionId);

            if (cmd==null||cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insertaction")
    public void insertAction(@RequestHeader("Authorization") String sessionId,
            @RequestParam(value = "actionNcpscode", required = true) String actionNcpscode,
            @RequestParam(value = "actionValide", required = false, defaultValue = "0") int actionValide,
            @RequestParam(value = "actionMiseappli", required = false, defaultValue = "0") int actionMiseappli,
            @RequestParam(value = "actionDescription", required = true) String actionDescription,
            @RequestParam(value = "actionCausesseq", required = true) int actionCausesseq,
            @RequestParam(value = "actionType", required = true) int actionType) {

        utilsRepository.insertAction(sessionId,actionNcpscode, actionValide, actionMiseappli, actionDescription,actionCausesseq, actionType);
    }

    @GetMapping("/emetteur_capa")
    public ResponseEntity<List<String>> listEmetteur( @RequestHeader("Authorization") String sessionId) {
        try {
            List<String> cmd = utilsRepository.findallemetteur(sessionId);

            if (cmd==null||cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/user_capa")
    public ResponseEntity<List<String>> listUser( @RequestHeader("Authorization") String sessionId) {
        try {
            List<String> cmd = utilsRepository.findalluser(sessionId);

            if (cmd==null||cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //code de select
    @GetMapping("/listenumerationcapa")
    public ResponseEntity<List<Proc>> listEnum(@RequestHeader("Authorization") String sessionId) {
        try {
            List<Proc> procList = utilsRepository.listEnum(sessionId);

            if (procList == null || procList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(procList, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("General Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dpt_capa")
    public ResponseEntity<List<String>> listDpt( @RequestHeader("Authorization") String sessionId) {
        try {
            List<String> cmd = utilsRepository.findalldpt(sessionId);

            if (cmd==null||cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/dpt_capa2")
    public ResponseEntity<List<String>> listDpt2( @RequestHeader("Authorization") String sessionId) {
        try {
            List<String> cmd = utilsRepository.findalldpt2(sessionId);

            if (cmd==null||cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listVoiture")
    public ResponseEntity<List<Transporteur>> listTransporteur(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String critere) {
        try {
            List<Transporteur> cmd = utilsRepository.listTransporteur(sessionId, critere);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getCode")
    public ResponseEntity<String> getCode(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String table,
            @RequestParam int cpt
    ) {
        String generatedCode = utilsRepository.getCode(sessionId,table, cpt);
        return new ResponseEntity<>(generatedCode, HttpStatus.OK);
    }

    @GetMapping("/listLoginPlus")
    public ResponseEntity<?> onListVoit(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = false) String critere) {

        try {

            List<LoginPlus> cmd = utilsRepository.onLoginPlus(sessionId,critere);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cmd, HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();

            String message = e.getMessage();

            if (e.getCause() != null) {
                message = e.getCause().getMessage();
            }

            // renvoyer le message d'erreur au frontend
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(message);
        }
    }

    @GetMapping("/getCCDepBP")
    public ResponseEntity<?> getCCDepBP(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = true) String BP) {

        try {
            String result = utilsRepository.getCCDepBP(sessionId, BP);

            return (result == null || result.isEmpty())
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.ok(result);

        } catch (Exception e) {

            String message = (e.getCause() != null)
                    ? e.getCause().getMessage()
                    : e.getMessage();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(message);
        }
    }

    @GetMapping("/hex-to-code")
    public ResponseEntity<?> hexToCode(
            @RequestParam String hex,
            @RequestParam String x) {

        try {

            String result = utilsRepository.hextoCode(hex, x);

            return (result == null || result.isEmpty())
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.ok(result);

        } catch (Exception e) {

            String message = (e.getCause() != null)
                    ? e.getCause().getMessage()
                    : e.getMessage();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(message);
        }
    }

}
