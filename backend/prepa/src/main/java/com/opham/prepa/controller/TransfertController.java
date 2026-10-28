package com.opham.prepa.controller;

import com.opham.prepa.model.Transfert.*;
import com.opham.prepa.repository.Transfert.TransfertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransfertController {
    @Autowired
    TransfertRepository transfertRepository;


    @GetMapping("/rotation")
    public ResponseEntity<List<List<Object>>> listRotation(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String depot, @RequestParam(required = false) String alle) {
        try {
            List<List<Object>> cmd = transfertRepository.listRotation(sessionId, depot, alle);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/colisIncomplet")
    public ResponseEntity<List<List<Object>>> listColisIncomplet(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false, defaultValue = "GROS") String depotOrg, @RequestParam(required = false, defaultValue = "DET") String depotDest, @RequestParam(required = false) String alle) {
        try {
            List<List<Object>> cmd = transfertRepository.listColisIncomplet(sessionId, depotOrg, depotDest, alle);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/petitColis")
    public ResponseEntity<List<List<Object>>> listPetitColis(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false, defaultValue = "GROS") String depotOrg, @RequestParam(required = false, defaultValue = "DET") String depotDest, @RequestParam(required = false) String alle) {
        try {
            List<List<Object>> cmd = transfertRepository.listPetitColis(sessionId, depotOrg, depotDest, alle);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rangement")
    public ResponseEntity<List<List<Object>>> listRangement(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String depot) {
        try {
            List<List<Object>> cmd = transfertRepository.listRangement(sessionId, depot);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insertL6_transfert")
    public ResponseEntity<Transfert> insertL6_Transfert(@RequestHeader("Authorization") String sessionId, @RequestBody Transfert t) {
        try {
            int updatedRows = transfertRepository.insertL6_Transfert(sessionId, t);
            if (updatedRows > 0) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insertL6_transfertConseil")
    public ResponseEntity<TransfertConseilReappro> insertL6_TransfertConseil(@RequestHeader("Authorization") String sessionId, @RequestBody TransfertConseilReappro t) {
        try {
            int updatedRows = transfertRepository.insertL6_TransfertConseil(sessionId, t);
            if (updatedRows > 0) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insertFSIL")
    public ResponseEntity<String> insert_FSIL(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String ids, @RequestParam(required = true) String commentaire, @RequestParam(required = true) String depOrg, @RequestParam(required = true) String depDest,@RequestParam(required = true) String user_web) {
        try {
            String cmd = transfertRepository.insert_FSIL(sessionId, ids, commentaire, depOrg, depDest,user_web);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/testStock")
    public ResponseEntity<ProblemeStock> testStock(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String article, @RequestParam(required = true) int qte, @RequestParam(required = true) String depot, @RequestParam(required = true) String lettre, @RequestParam(required = true) String empl) {
        try {
            ProblemeStock cmd = transfertRepository.testStock(sessionId, article, qte, depot, lettre, empl);
            if (cmd != null) {
                return new ResponseEntity<>(cmd, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stockPasVide")
    public ResponseEntity<Integer> stockPasVide(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String depot, @RequestParam(required = true) String empl) {
        try {
            int cmd = transfertRepository.stockPasVide(sessionId, depot, empl);

/*            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }*/
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/printTransfert")
    public ResponseEntity<byte[]> generateReport(@RequestHeader("Authorization") String sessionId, @RequestParam String td, @RequestParam String org, @RequestParam String dest, @RequestParam Integer isDouble, @RequestParam String users) {
        byte[] reportBytes = transfertRepository.generateReportTransfert(sessionId, td, org, dest, isDouble, users);

        if (reportBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "transfertA5.pdf");

            return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/conseilReappro")
    public ResponseEntity<List<List<Object>>> conseilReappro(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String article, @RequestParam(required = false, defaultValue = "GROS") String depotOrg, @RequestParam(required = false, defaultValue = "DET") String depotDest) {
        try {
            List<List<Object>> cmd = transfertRepository.conseilReappro(sessionId, article, depotOrg, depotDest);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/suiviTDDetail")
    public ResponseEntity<List<SuiviTDDetail>> listSuiviTDDetail(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String code, @RequestParam(required = false) String depOrg, @RequestParam(required = false) String depDest,
                                                           @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date datedeb, @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date datefin, @RequestParam(required = false) String article,@RequestParam(required = true) int detail,@RequestParam(required = true) int etat) {
        try {
            List<SuiviTDDetail> cmd = transfertRepository.listSuiviTDDetail(sessionId, code, depOrg, depDest, datedeb, datefin, article,detail,etat);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/suiviTD")
    public ResponseEntity<List<SuiviTD>> listSuiviTD(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String code, @RequestParam(required = false) String depOrg, @RequestParam(required = false) String depDest,
                                                     @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date datedeb, @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date datefin, @RequestParam(required = false) String article, @RequestParam(required = true) int detail, @RequestParam(required = true) int etat) {
        try {
            List<SuiviTD> cmd = transfertRepository.listSuiviTD(sessionId, code, depOrg, depDest, datedeb, datefin, article,detail,etat);

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
