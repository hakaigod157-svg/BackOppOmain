package com.opham.prepa.controller;

import com.opham.prepa.model.Capa.Capa;
import com.opham.prepa.model.Capa.AtteVal;
import com.opham.prepa.model.Capa.CauseRac;
import com.opham.prepa.model.Capa.ktable;
import com.opham.prepa.model.Utils.Action;
import com.opham.prepa.repository.Capa.CapaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
class CapaController {

    @Autowired
    private CapaRepository capaRepository;

    @GetMapping("/capa")
    public ResponseEntity<List<Capa>> listCapa(@RequestHeader("Authorization") String sessionId,@RequestParam(required = false) String code,
                                               @RequestParam(required = false) String description,
                                               @RequestParam(required = false) String emetteur,
                                               @RequestParam(required = false) String libelleDep,
                                               @RequestParam(required = false) String type,
                                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                               @RequestParam(required = false) Integer causevalid,
                                               @RequestParam(required = false) Integer causeatte,
                                               @RequestParam(required = false) Integer attevalid,
                                               @RequestParam(required = false) Integer attemiseappli,
                                               @RequestParam(required = false) Integer appli) {
        try {
            List<Capa> cpaList = capaRepository.listCapa(sessionId,code, description, emetteur, libelleDep, type, date, causevalid, causeatte, attevalid, attemiseappli, appli);

            if (cpaList == null || cpaList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(cpaList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //code de mis a jour :
    @PutMapping("/updatecapa")
    public void updateFNCPS(@RequestHeader("Authorization") String sessionId, @RequestParam(value = "ncpsSeq", required = true) int ncpsSeq,
                            @RequestParam(value = "ncpsCode", required = false) String ncpsCode,
                            @RequestParam(value = "ncpsDate", required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd") Date ncpsDate,
                            @RequestParam(value = "ncpsDescription", required = false) String ncpsDescription,
                            @RequestParam(value = "ncpsDepartement", required = false) String ncpsDepartement,
                            @RequestParam(value = "ncpsType", required = false) String ncpsType,
                            @RequestParam(value = "ncpsModeDetect", required = false) String ncpsModeDetect,
                            @RequestParam(value = "ncpsProcessusDetect", required = false) String ncpsProcessusDetect) {
        capaRepository.updateFNCPS(sessionId,ncpsSeq,ncpsCode, ncpsDate, ncpsDescription, ncpsDepartement, ncpsType, ncpsModeDetect, ncpsProcessusDetect);
    }

    @PostMapping("/insertcapa")
    public void insertFNCPS(@RequestHeader("Authorization") String sessionId,
            @RequestParam(value = "ncpsEmetteur", required = true) String ncpsEmetteur,
            @RequestParam(value = "ncpsDescription", required = false) String ncpsDescription,
            @RequestParam(value = "ncpsDepartement", required = false) String ncpsDepartement,
            @RequestParam(value = "ncpsDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date ncpsDate,
            @RequestParam(value = "ncpsEtat", required = false, defaultValue = "0") int ncpsEtat,
            @RequestParam(value = "ncpsValide", required = false, defaultValue = "0") int ncpsValide) {

        capaRepository.insertFNCPS(sessionId,ncpsEmetteur, ncpsDescription, ncpsDepartement, ncpsDate ,ncpsEtat, ncpsValide);
    }

    @GetMapping("/attentevalid")
    public ResponseEntity<List<AtteVal>> listatteval(@RequestHeader("Authorization") String sessionId) {
        try {
            List<AtteVal> cpaList2 = capaRepository.listatteval(sessionId);

            if (cpaList2 == null || cpaList2.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(cpaList2, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/action")
    public ResponseEntity<List<CauseRac>> listAction(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) int actionCauseseq) {
        try {
            List<CauseRac> actionList = capaRepository.findByCodeAction(sessionId,actionCauseseq);

            if (actionList == null || actionList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(actionList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteactionc")
    public void deleteFACTION(@RequestHeader("Authorization") String sessionId,
            @RequestParam(value = "actionSeq", required = true) int actionSeq) {

        capaRepository.deleteFACTION(sessionId,actionSeq);
    }

    @PutMapping("/updateaction")
    public void updateFACTION(@RequestHeader("Authorization") String sessionId,@RequestParam(value = "actionSeq", required = true) int actionSeq,
                              @RequestParam(value = "actionResponsable", required = false) String actionResponsable,
                                @RequestParam(value = "actionDateprev", required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd") Date actionDateprev) {
        capaRepository.updateFACTION(sessionId,actionSeq, actionResponsable, actionDateprev);
    }

    @PutMapping("/updateaction2")
    public void updateFACTION2(@RequestHeader("Authorization") String sessionId,@RequestParam(value = "actionSeq", required = true) int actionSeq,
                               @RequestParam(value = "actionDateanaleffic", required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd") Date actionDateanaleffic,
                              @RequestParam(value = "actionDaterelleeffic", required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd") Date actionDaterelleeffic) {
        capaRepository.updateFACTION2(sessionId,actionSeq, actionDateanaleffic, actionDaterelleeffic);
    }

    @PutMapping("/updateaction3")
    public void updateFACTION3(@RequestHeader("Authorization") String sessionId,@RequestParam(value = "actionSeq", required = true) int actionSeq,
                               @RequestParam(value = "actionDaterelleeffic", required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd") Date actionDaterelleeffic,
                               @RequestParam(value = "actionCommentaire", required = false) String actionCommentaire) {
        capaRepository.updateFACTION3(sessionId,actionSeq, actionDaterelleeffic, actionCommentaire);
    }
    @GetMapping("/ktable")
    public ResponseEntity<List<ktable>> listktable(@RequestHeader("Authorization") String sessionId) {
        try {
            List<ktable> tableList = capaRepository.findktable(sessionId);

            if (tableList == null || tableList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(tableList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updatektable")
    public void updateKTABLE(@RequestHeader("Authorization") String sessionId, @RequestParam(value = "seqk", required = true) int seqk) {
        capaRepository.updateKTABLE(sessionId,seqk);
    }

    @PostMapping("/insertktable")
    public void insertKTABLE(@RequestHeader("Authorization") String sessionId) {

        capaRepository.insertKTABLE(sessionId);
    }
}
