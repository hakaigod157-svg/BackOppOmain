package com.opham.prepa.controller;

import com.opham.prepa.model.BusinessIntelligence.*;
import com.opham.prepa.model.Expedition.ListCmdLivAsuppr;
import com.opham.prepa.model.Utils.Credentials;
import com.opham.prepa.model.Utils.LoginPlus;
import com.opham.prepa.repository.BusinessIntelligence.BusinessIntelligenceRepository;
import com.opham.prepa.repository.Expedition.ExpeditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")

public class BusinessIntelligenceController {

    @Autowired
    BusinessIntelligenceRepository businessIntelligenceRepository;

    @GetMapping("/list_view")
    public ResponseEntity<?> list_view(@RequestHeader("Authorization") String sessionId) {
        try {
            List<list_view> cmd = businessIntelligenceRepository.listView(sessionId);

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

    @GetMapping("/list_ca_annuel")
    public ResponseEntity<?> listCaAnnuel(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = false) String codeArticle) {

        try {
            List<list_ca_annuel> cmd = businessIntelligenceRepository.listCaAnnuel(sessionId,codeArticle);

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


    @GetMapping("/list_palier")
    public ResponseEntity<List<List<Object>>> listPalier(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = false) String taux,
            @RequestParam(required = false) Double primeMax) {

        try {

            Double tauxDouble = null;

            // conversion String -> Double
            if (taux != null && !taux.trim().isEmpty()) {
                tauxDouble = Double.parseDouble(
                        taux.replace(",", ".")
                );
            }

            List<List<Object>> cmd =
                    businessIntelligenceRepository.listPalier(sessionId, tauxDouble ,primeMax);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cmd, HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/list_taux")
    public ResponseEntity<?> list_taux(@RequestHeader("Authorization") String sessionId) {
        try {
            List<taux_palier> cmd = businessIntelligenceRepository.list_taux(sessionId);

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

}
