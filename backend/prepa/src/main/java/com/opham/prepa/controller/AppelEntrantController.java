package com.opham.prepa.controller;

import com.opham.prepa.model.AppelEntrant.*;
import com.opham.prepa.repository.AppelEntrant.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")

public class AppelEntrantController {

    @Autowired
    CallRepository appelentrantRepository;

    @GetMapping("/appelentrant")

    public ResponseEntity<List<AppelEntrant>> onAppelEntrant(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_deb, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_fin) {
        try {
            List<AppelEntrant> cmd = appelentrantRepository.findAppelEntrant(sessionId,date_deb ,date_fin);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/appelsortant")

    public ResponseEntity<List<AppelSortant>> onAppelSortant(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date_sortant, @RequestParam(required = false)  String datePart, @RequestParam(required = false) Time timePart, @RequestParam(required = false)  String numero) {
        try {
            List<AppelSortant> cmd = appelentrantRepository.findAppelSortant(sessionId,date_sortant , datePart, timePart, numero);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/listAction")

    public ResponseEntity<List<ActionFirstAppel>> onActionFirstAppel(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String seqEntrant) {
        try {
            BigDecimal seqEntrantBig = new BigDecimal(seqEntrant);
            List<ActionFirstAppel> cmd = appelentrantRepository.findActionFirstAppel(sessionId,seqEntrantBig);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateEtat")
    public ResponseEntity<String> updateFrais(@RequestHeader("Authorization") String sessionId, @RequestParam String AppelId) {
        BigDecimal AppelIdBig = new BigDecimal(AppelId);
        appelentrantRepository.updateEtat(sessionId,AppelIdBig);
        return ResponseEntity.status(HttpStatus.OK).body("update Etat successfully");
    }

 /*   @PutMapping("/updateEtat1")
    public ResponseEntity<String> updateEtat(@RequestParam Long AppelId) {
        appelentrantRepository.updateEtat(AppelId);
        return ResponseEntity.status(HttpStatus.OK).body("update Etat to 2 successfully");
    } */

    @PostMapping("/updateEtatSortie")
    public ResponseEntity<String> updateEtatSortie(@RequestHeader("Authorization") String sessionId, @RequestParam String seq,@RequestParam int etat,@RequestParam(required = false) String seqSortie,@RequestParam(required = false) int rappel,@RequestParam(required = false) String src_sortant) {

        try {
            BigDecimal seqEtatBig = new BigDecimal(seq);
            BigDecimal seqSortieBig = null;
            if(seqSortie!=null) {
                seqSortieBig = new BigDecimal(seqSortie);
            }
            int rowsAffected = appelentrantRepository.updateEtatSortie(sessionId,seqEtatBig,etat,seqSortieBig,rappel,src_sortant);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("resultat update vel avec succès");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'update vl ");
            }
        } catch (DataAccessException e) {
            // Log the exception details

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour vl");
        }

    }



  /*  @GetMapping("/xTest_maj")

    public ResponseEntity<List<XtestMajActions>> onTestMaj(@RequestParam(required = true) int seq) {
        try {
            List<XtestMajActions> cmd = appelxTestMajRepository.findxTestMaj(seq);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @GetMapping("/xTest_maj")
    public ResponseEntity<Integer> findxTestMaj(@RequestHeader("Authorization") String sessionId, @RequestParam String seq) {
        try {
            // Call the service to get the result
            BigDecimal seqBig = new BigDecimal(seq);
            int count = appelentrantRepository.findxTestMaj(sessionId,seqBig);
            return new ResponseEntity<>(count, HttpStatus.OK);  // Return 200 OK with the count
        } catch (Exception e) {
            // Return 500 Internal Server Error if something goes wrong
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/insert_frappel")
    public ResponseEntity<String> insertFrappel(@RequestHeader("Authorization") String sessionId, @RequestParam (required = true) String seqEntrant,@RequestParam (required = true) String seqSortant,@RequestParam (required = true) String user,@RequestParam (required = true) String ip_local) {
        try {
            BigDecimal seqEntrantbig = new BigDecimal(seqEntrant);
            BigDecimal seqSortantbig = new BigDecimal(seqSortant);
            int rowsAffected = appelentrantRepository.insertFrappel(sessionId,seqEntrantbig,seqSortantbig,user,ip_local);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion dans FRAPPEL");
            }
        } catch (DataAccessException e) {
            // Log the exception details

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion de FRAPPEL");
        }
    }




    @GetMapping("/appel_moyenne")

    public ResponseEntity<List<MoyenneAppel>> onAppelMoyenne(@RequestHeader("Authorization") String sessionId,  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_deb, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_fin) {
        try {
            List<MoyenneAppel> cmd = appelentrantRepository.findAppelMoyenne(sessionId ,date_deb ,date_fin);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
