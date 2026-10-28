package com.opham.prepa.controller;


import com.opham.prepa.model.Catalogue.catalogue;
import com.opham.prepa.model.Catalogue.listCodeBarre;
import com.opham.prepa.model.Catalogue.listLabo;
import com.opham.prepa.repository.Catalogue.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class CatalogueController {

    @Autowired
    private CatalogueRepository cataloguerepository;

    @GetMapping("/catalogue")
    public ResponseEntity<List<catalogue>>oncatalogue(@RequestHeader("Authorization") String sessionId,@RequestParam(required = false) String clientcode ,@RequestParam(required = true) int moiscode,@RequestParam(required = true) int anneecode,@RequestParam(required = false) String tarifcode,@RequestParam(required = true) int distribue) {
        try {
            List<catalogue> cmd = cataloguerepository.findCatalogue(sessionId,clientcode,moiscode,anneecode,tarifcode,distribue);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/testCatalogue")
    public ResponseEntity<Integer>ontestcatalogue(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) int moiscode, @RequestParam(required = true) int anneecode) {
        try {
            int cmd = cataloguerepository.findTestCatalogue(sessionId,moiscode,anneecode);
           /* if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } */
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/genereCatalogue")
    public ResponseEntity<String> genereCatalogue(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) int moiscode, @RequestParam(required = true) int anneecode ) {
        try {
            int rowsAffected = cataloguerepository.genereCatalogue(sessionId, moiscode,anneecode);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour FCC2 effectuée avec succès");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
            }
        } catch (DataAccessException e) {
            e.printStackTrace(); // Log l'erreur pour le débogage
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour");
        }
    }

    @GetMapping("/listLaboCode")
    public ResponseEntity<List<listLabo>>onlistLabo(@RequestHeader("Authorization") String sessionId) {
        try {
            List<listLabo> cmd = cataloguerepository.findListLabo(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listCodeBarre")
    public ResponseEntity<List<listCodeBarre>>onlistCodeBarre(@RequestHeader("Authorization") String sessionId) {
        try {
            List<listCodeBarre> cmd = cataloguerepository.findListCodeBarre(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/insertCodeBarre")
    public ResponseEntity<String> insertCodeBarre(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String labo, @RequestParam(required = true) String article ) {
        try {
            int rowsAffected = cataloguerepository.insertCodeBarre(sessionId, labo,article);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("insertion effectuée avec succès");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l insertion");
            }
        } catch (DataAccessException e) {
            e.printStackTrace(); // Log l'erreur pour le débogage
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l insertion");
        }
    }
}
