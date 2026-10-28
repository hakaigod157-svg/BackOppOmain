package com.opham.prepa.controller;

import com.opham.prepa.model.Rh.*;
import com.opham.prepa.repository.Rh.RhRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RhController {
    @Autowired
    private RhRepository rhRepository;

    @GetMapping("/listDemandePers")
    public ResponseEntity<List<DemandePers>> onListDemandePers(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = false) Integer etat) {
        try {
            List<DemandePers> cmd = rhRepository.findAllListDemandePers(sessionId, etat);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listDetailRecrut")
    public ResponseEntity<List<List<Object>>> onlistdetailrecrut(@RequestHeader("Authorization") String sessionId, @RequestParam String coderecu) {
        try {
            List<List<Object>> cmd = rhRepository.findAllDetailRecru(sessionId, coderecu);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listRecrut")
    public ResponseEntity<List<Recrutement>> onListRecrutement(
            @RequestHeader("Authorization") String sessionId
    ){
        try {
            List<Recrutement> rct = rhRepository.findAllListRecrutement(sessionId);
            if (rct.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } return new ResponseEntity<>(rct,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listOffre")
    public ResponseEntity<List<Offre>> onListOffre(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String coderecr
    ){
        try {
            List<Offre> of = rhRepository.findAllOffre(sessionId, coderecr);
            if (of.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } return new ResponseEntity<>(of,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listCabinet")
    public ResponseEntity<List<Cabinet>> onListCabinet(
            @RequestHeader("Authorization") String sessionId
    ){
        try {
            List<Cabinet> c = rhRepository.findAllCabinet(sessionId);
            if (c.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } return new ResponseEntity<>(c,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listCV")
    public ResponseEntity<List<List<Object>>> onListCV(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String recrut
    ){
        try {
            List<List<Object>> cv = rhRepository.findAllCvRecru(sessionId, recrut);
            if (cv.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } return new ResponseEntity<>(cv,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listCVglobal")
    public ResponseEntity<List<CV>> onListCVglobal(
            @RequestHeader("Authorization") String sessionId
    ){
        try {
            List<CV> c = rhRepository.findAllCvGlobal(sessionId);
            if (c.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } return new ResponseEntity<>(c,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/updatecvselection")
    public ResponseEntity<Integer> onUpdateCv(@RequestHeader("Authorization") String sessionId, @RequestParam String recrut, @RequestParam String cv) {
        try {
            int rows = rhRepository.updateCvSelection(sessionId, recrut, cv);

            if (rows == 0) {
                // aucune ligne mise à jour
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            // au moins une ligne mise à jour → retour OK avec le nombre de lignes
            return new ResponseEntity<>(rows, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/updatecventretien")
    public ResponseEntity<Integer> onUpdateCv1(@RequestHeader("Authorization") String sessionId, @RequestParam String recrut, @RequestParam String cv) {
        try {
            int rows = rhRepository.updateCvEntretien(sessionId, recrut, cv);

            if (rows == 0) {
                // aucune ligne mise à jour
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // au moins une ligne mise à jour → retour OK avec le nombre de lignes
            return new ResponseEntity<>(rows, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/updatecvProposition")
    public ResponseEntity<Integer> onUpdateDateProposition(@RequestHeader("Authorization") String sessionId, @RequestParam String recrut, @RequestParam String cv) {
        try {
            int rows = rhRepository.updateCvDateProposition(sessionId, recrut, cv);

            if (rows == 0) {
                // aucune ligne mise à jour
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // au moins une ligne mise à jour → retour OK avec le nombre de lignes
            return new ResponseEntity<>(rows, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


  /*  @GetMapping("/updatevalidation")
    public ResponseEntity<Integer> onUpdatevalidation(@RequestHeader("Authorization") String sessionId, @RequestParam String recrut, @RequestParam String cv,@RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") java.sql.Date dateValidation) {
        try {
            int rows = rhRepository.onUpdatevalidation(sessionId, recrut, cv ,dateValidation);

            if (rows == 0) {
                // aucune ligne mise à jour
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // au moins une ligne mise à jour → retour OK avec le nombre de lignes
            return new ResponseEntity<>(rows, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } */

    @GetMapping("/updatevalidation")
    public ResponseEntity<Integer> onUpdatevalidation(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String recrut,
            @RequestParam String cv,
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") java.time.LocalDate dateValidation,
            @RequestParam String categorie) {
        try {
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateValidation);

            int rows = rhRepository.onUpdatevalidation(sessionId, recrut, cv, sqlDate, categorie);

            if (rows == 0) {
                // aucune ligne mise à jour
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // au moins une ligne mise à jour → retour OK avec le nombre de lignes
            return new ResponseEntity<>(rows, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/updatedatecventretien")
    public ResponseEntity<Integer> onUpdateCv1(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String date,
            @RequestParam String recrut,
            @RequestParam String cv
    ) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(date);
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(parsedDate.getTime());

            int rows = rhRepository.updateDateCvEntretien(sessionId, sqlDate, recrut, cv);

            if (rows == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(rows, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/updatedatevalidation")
    public ResponseEntity<Integer> onUpdatedateValidation(@RequestHeader("Authorization") String sessionId, @RequestParam String date, @RequestParam String recrut, @RequestParam String cv) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(date);
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(parsedDate.getTime());

            int rows = rhRepository.onUpdatedateValidation(sessionId, sqlDate, recrut, cv);

            if (rows == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(rows, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/updatedatecvProposition")
    public ResponseEntity<Integer> onUpdatedateProposition(@RequestHeader("Authorization") String sessionId, @RequestParam String date, @RequestParam String recrut, @RequestParam String cv) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(date);
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(parsedDate.getTime());

            int rows = rhRepository.onUpdatedateProposition(sessionId, sqlDate, recrut, cv);

            if (rows == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(rows, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/departement-poste")
    public ResponseEntity<List<DepartementPoste>> departementPoste(
            @RequestHeader("Authorization") String sessionId) {
        try {
            List<DepartementPoste> list = rhRepository.findAllDepartementPoste(sessionId);
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/codenouveauDemande")
    public ResponseEntity<List<List<Object>>> oncodenouveauDemande(@RequestHeader("Authorization") String sessionId,
                                                                   @RequestParam String prefixe,
                                                                   @RequestParam String table) {
        try {
            List<List<Object>> cmd = rhRepository.findcodenouveauDemande(sessionId, prefixe, table);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @SneakyThrows
    @PostMapping("/insertDemande")
    public ResponseEntity<String> onInsertDemande(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String demande_code,
            @RequestParam String demande_date,
            @RequestParam String demande_prise_fonction,
            @RequestParam String demande_departement,
            @RequestParam String demande_motif,
            @RequestParam String demande_contrat,
            @RequestParam String demande_poste,
            @RequestParam Integer demande_effectif,
            @RequestParam Integer demande_etat,
            @RequestParam(value = "demande_fiche", required = false) MultipartFile demandeFiche
    ) throws IOException {

        DemandePers d = new DemandePers();
        d.setDemande_code(demande_code);
        d.setDemande_date(Date.valueOf(demande_date)); // convert string -> java.sql.Date
        d.setDemande_prise_fonction(Date.valueOf(demande_prise_fonction));
        d.setDemande_departement(demande_departement);
        d.setDemande_motif(demande_motif);
        d.setDemande_contrat(demande_contrat);
        d.setDemande_poste(demande_poste);
        d.setDemande_effectif(demande_effectif);
        d.setDemande_etat(demande_etat);

        byte[] fiche = null;
        if (demandeFiche != null && !demandeFiche.isEmpty()) {
            fiche = demandeFiche.getBytes();
        }

        int rowsAffected = rhRepository.insererDemande(d, fiche, sessionId);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Demande insérée avec succès");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec insertion");
    }

    @SneakyThrows
    @PostMapping("/updateDemande")
    public ResponseEntity<String> onUpdateDemande(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String demande_code,
            @RequestParam String demande_date,
            @RequestParam String demande_prise_fonction,
            @RequestParam String demande_departement,
            @RequestParam String demande_motif,
            @RequestParam String demande_contrat,
            @RequestParam String demande_poste,
            @RequestParam Integer demande_effectif,
            @RequestParam(value = "demande_fiche", required = false) MultipartFile demandeFiche
    ) throws IOException {

        DemandePers d = new DemandePers();
        d.setDemande_code(demande_code);
        d.setDemande_date(Date.valueOf(demande_date)); // convert string -> java.sql.Date
        d.setDemande_prise_fonction(Date.valueOf(demande_prise_fonction));
        d.setDemande_departement(demande_departement);
        d.setDemande_motif(demande_motif);
        d.setDemande_contrat(demande_contrat);
        d.setDemande_poste(demande_poste);
        d.setDemande_effectif(demande_effectif);
        d.setDemande_fiche(demandeFiche.getBytes());

        int rowsAffected = rhRepository.updateDemande(d, sessionId);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Demande modifiee avec succès");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec modification");
    }


    @SneakyThrows
    @PostMapping("/insertOffre")
    public ResponseEntity<String> onInsertOffre(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String offre_code,
            @RequestParam String offre_date,
            @RequestParam String offre_entite,
            @RequestParam String offre_code_recr,
            @RequestParam(value = "offre_piece", required = false) MultipartFile offrePiece
    ) throws IOException {

        Offre o = new Offre();
        o.setOffre_code(offre_code);
        o.setOffre_date(Date.valueOf(offre_date));
        o.setOffre_entite(offre_entite);
        o.setOffre_code_recr(offre_code_recr);


        byte[] piece = null;
        if (offrePiece != null && !offrePiece.isEmpty()) {
            piece = offrePiece.getBytes();
        }

        int rowsAffected = rhRepository.insererOffre(o, piece, sessionId);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Offre insérée avec succès");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec insertion");
    }

    @SneakyThrows
    @PostMapping("/insertCV")
    public ResponseEntity<String> onInsertCV(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String cv_code,
            @RequestParam String cv_nom,
            @RequestParam String cv_prenom,
            @RequestParam String cv_contact1,
            @RequestParam String cv_contact_2,
            @RequestParam String cv_email,
            @RequestParam String cv_date,
            @RequestParam String cv_recru_code,
            @RequestParam (value = "cv_cv", required = false) MultipartFile cvCv,
            @RequestParam (value = "cv_motivation", required = false) MultipartFile cvLm

    ) throws IOException {

        CV c = new CV();
        c.setCv_code(cv_code);
        //c.setDemande_date(Date.valueOf(cv_nom)); // convert string -> java.sql.Date

        c.setCv_nom(cv_nom);
        c.setCv_prenom(cv_prenom);
        c.setCv_contact1(cv_contact1);
        c.setCv_contact_2(cv_contact_2);
        c.setCv_email(cv_email);
        c.setCv_date(Date.valueOf(cv_date));
        c.setCv_recru_code(cv_recru_code);

        byte[] cv = null;
        byte[] lm = null;
        if (cvCv != null && !cvCv.isEmpty()) {
            cv = cvCv.getBytes();
        }
        if (cvLm != null && !cvLm.isEmpty()) {
            lm = cvLm.getBytes();
        }

        int rowsAffected = rhRepository.insererCV(c, cv, lm, sessionId);
        int rowsAffected1 = rhRepository.insererLigneCV(c, sessionId);
        if (rowsAffected > 0 && rowsAffected1>0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("CV insérée avec succès");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec insertion CV");
    }

    @SneakyThrows
    @PostMapping("/insertCVglobal")
    public ResponseEntity<String> onInsertCVglobal(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String cv_code,
            @RequestParam String cv_nom,
            @RequestParam String cv_prenom,
            @RequestParam String cv_contact1,
            @RequestParam String cv_contact_2,
            @RequestParam String cv_email,
            @RequestParam String cv_date,
            @RequestParam (value = "cv_cv", required = false) MultipartFile cvCv,
            @RequestParam (value = "cv_motivation", required = false) MultipartFile cvLm

    ) throws IOException {

        CV c = new CV();
        c.setCv_code(cv_code);
        //c.setDemande_date(Date.valueOf(cv_nom)); // convert string -> java.sql.Date

        c.setCv_nom(cv_nom);
        c.setCv_prenom(cv_prenom);
        c.setCv_contact1(cv_contact1);
        c.setCv_contact_2(cv_contact_2);
        c.setCv_email(cv_email);
        c.setCv_date(Date.valueOf(cv_date));

        byte[] cv = null;
        byte[] lm = null;
        if (cvCv != null && !cvCv.isEmpty()) {
            cv = cvCv.getBytes();
        }
        if (cvLm != null && !cvLm.isEmpty()) {
            lm = cvLm.getBytes();
        }

        int rowsAffected = rhRepository.insererCV(c, cv, lm, sessionId);

        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("CV insérée avec succès");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec insertion CV");
    }
    @SneakyThrows
    @PostMapping("/insertLigneCV")
    public ResponseEntity<String> onInsertLigneCV(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String cv_code,
            @RequestParam String cv_recru_code

    ) throws IOException {
        CV c = new CV();
        c.setCv_code(cv_code);
        c.setCv_recru_code(cv_recru_code);
        int rowsAffected = rhRepository.insererLigneCV(c, sessionId);

        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("CV insérée avec succès");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec insertion CV");
    }



    @PostMapping("/updateEtatDemande")
    public ResponseEntity<String> update_etat_demande(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String codeDemande ) {
        int rowsAffected = rhRepository.updateDemandeToApprouve(sessionId, codeDemande);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
        }
    }
    @PostMapping("/updateEtatDemande2")
    public ResponseEntity<String> update_etat_demande2(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String codeDemande ) {
        int rowsAffected = rhRepository.updateDemandeToRefuser(sessionId, codeDemande);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
        }
    }

    @SneakyThrows
    @PostMapping("/updateOffre")
    public ResponseEntity<String> updateOffre(
            @RequestHeader("Authorization") String sessionId ,
            @RequestParam String offre_code,
            @RequestParam String offre_date,
            @RequestParam String offre_entite,
            @RequestParam String offre_code_recr,
            @RequestParam(value = "offre_piece", required = false) MultipartFile offrePiece
    ) {


        Offre o = new Offre();
        o.setOffre_code(offre_code);
        o.setOffre_date(Date.valueOf(offre_date));
        o.setOffre_entite(offre_entite);
        o.setOffre_code_recr(offre_code_recr);


        byte[] piece = null;
        if (offrePiece != null && !offrePiece.isEmpty()) {
            piece = offrePiece.getBytes();
        }
        int rowsAffected = rhRepository.updateOffre(o, piece, sessionId);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
        }
    }
    @SneakyThrows
    @PostMapping("/updateCv")
    public ResponseEntity<String> updateCv(
            @RequestHeader("Authorization") String sessionId ,
            @RequestParam String cv_code,
            @RequestParam String cv_nom,
            @RequestParam String cv_prenom,
            @RequestParam String cv_contact1,
            @RequestParam String cv_contact_2,
            @RequestParam String cv_email,
            @RequestParam String cv_date,
            @RequestParam (value = "cv_cv", required = false) MultipartFile cvCv,
            @RequestParam (value = "cv_motivation", required = false) MultipartFile cvLm
    ) {

        CV c = new CV();
        c.setCv_code(cv_code);
        //c.setDemande_date(Date.valueOf(cv_nom)); // convert string -> java.sql.Date

        c.setCv_nom(cv_nom);
        c.setCv_prenom(cv_prenom);
        c.setCv_contact1(cv_contact1);
        c.setCv_contact_2(cv_contact_2);
        c.setCv_email(cv_email);
        c.setCv_date(Date.valueOf(cv_date));

        byte[] cv = null;
        byte[] lm = null;
        if (cvCv != null && !cvCv.isEmpty()) {
            cv = cvCv.getBytes();
        }
        if (cvLm != null && !cvLm.isEmpty()) {
            lm = cvLm.getBytes();
        }

        int rowsAffected = rhRepository.updateCV(c, cv, lm, sessionId);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
        }
    }

    @PostMapping("/deleteOffre")
    public ResponseEntity<String> delete_offre(@RequestHeader("Authorization") String sessionId ,
                                               @RequestParam(required = true) String offre_code,
                                               @RequestParam(required = true) String offre_entite,
                                               @RequestParam(required = true) String offre_code_recr) {
        int rowsAffected = rhRepository.deleteOffre(sessionId, offre_code, offre_entite, offre_code_recr);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Suppression fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de suppression");
        }
    }
    @PostMapping("/deleteDemande")
    public ResponseEntity<String> delete_offre(@RequestHeader("Authorization") String sessionId ,
                                               @RequestParam(required = true) String codeDemande) {
        int rowsAffected = rhRepository.deleteDemande(sessionId, codeDemande);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Suppression fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de suppression");
        }
    }


   /* @PostMapping("/insertCVTest")
    public ResponseEntity<String> onInsertCVTest(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String cv_code,
            @RequestParam String recru_code,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp cv_ligne_date_test,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp cv_ligne_date_entretien


    ) throws IOException {
        int rowsAffected = rhRepository.insertCVtest(sessionId,cv_code,recru_code,cv_ligne_date_test,cv_ligne_date_entretien);

        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("CV test insérée avec succès");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec insertion CV test");
    } */



    @PostMapping("/insertCVTest")
    public ResponseEntity<String> onInsertCVTest(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String cv_code,
            @RequestParam String recru_code,
            @RequestParam(required = false) String cv_ligne_date_test,
            @RequestParam(required = false) String cv_ligne_date_entretien

    ) throws IOException {

        Timestamp dateTest = null;
        Timestamp dateEntretien = null;

        if (cv_ligne_date_test != null && !cv_ligne_date_test.isBlank()) {
            dateTest = Timestamp.valueOf(cv_ligne_date_test);
        }

        if (cv_ligne_date_entretien != null && !cv_ligne_date_entretien.isBlank()) {
            dateEntretien = Timestamp.valueOf(cv_ligne_date_entretien);
        }

        int rowsAffected = rhRepository.insertCVtest(sessionId, cv_code, recru_code, dateTest, dateEntretien);

        if (rowsAffected > 0) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("CV test insérée avec succès");
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Échec insertion CV test");
    }


    @GetMapping("/listParams")
    public ResponseEntity<List<List<Object>>> onListParams(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String departement_code , @RequestParam(required = false) String poste_code){
        try {
            List<List<Object>> cv = rhRepository.findAllListParams(sessionId, departement_code ,poste_code);
            if (cv.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } return new ResponseEntity<>(cv,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteParams")
    public ResponseEntity<Void> deleteParams(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String dep, @RequestParam(required = true) String code_poste,@RequestParam(required = true) String categorie,@RequestParam(required = true) String materiel) {
        rhRepository.deleteParams(sessionId,dep,code_poste,categorie,materiel);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/insert_Params")
    public ResponseEntity<String> insert_params(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String dep, @RequestParam(required = true) String code_poste,@RequestParam(required = true) String categorie,@RequestParam(required = true) String materiel) {
        try {
            int rowsAffected = rhRepository.insert_params(sessionId,dep,code_poste,categorie,materiel);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion");
            }
        } catch (DataAccessException e) {
            // Log the exception details

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion");
        }
    }

  /*  @GetMapping("/listEmploye")
    public ResponseEntity<List<List<Object>>> onListEmploye(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) Integer etat){
        try {
            List<List<Object>> employe = rhRepository.findAllListEmployes(sessionId, etat);
            if (employe.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } return new ResponseEntity<>(employe,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } */

    @GetMapping("/listEmploye")
    public ResponseEntity<List<List<Object>>> onListEmploye(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String etat) {
        Integer valeurEtat = null;

        if (etat != null && !etat.equalsIgnoreCase("null") && !etat.isBlank()) {
            valeurEtat = Integer.valueOf(etat);
        }

        List<List<Object>> employe = rhRepository.findAllListEmployes(sessionId, valeurEtat);

        return ResponseEntity.ok(employe);
    }

    @PostMapping("/updateMatricule")
    public ResponseEntity<String> updateMatricule(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String matricule ,@RequestParam(required = true) String code_recrutement) {

        int rowsAffected = rhRepository.updateMatricule(sessionId,matricule,code_recrutement);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
        }
    }


   @GetMapping("/listIntegration")
    public ResponseEntity<List<List<Object>>> onListIntegration(@RequestHeader("Authorization") String sessionId){
        try {
            List<List<Object>> integration = rhRepository.findAllListIntegration(sessionId);
            if (integration.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } return new ResponseEntity<>(integration,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/insertEmploye")
    public ResponseEntity<String> insertEmploye(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String empl_matricule, @RequestParam(required = true) String empl_nom, @RequestParam(required = true) String empl_prenom, @RequestParam(required = true) String empl_date_naiss, @RequestParam(required = true) String empl_departement, @RequestParam(required = true) String empl_poste, @RequestParam(required = true) String empl_contrat, @RequestParam(required = true) String empl_prise_fonction, @RequestParam(required = false) String empl_date_fin_essai1, @RequestParam(required = false) String empl_date_fin_essai2
    ) {

        int rowsAffected = rhRepository.insertEmploye(sessionId, empl_matricule, empl_nom, empl_prenom, empl_date_naiss, empl_departement, empl_poste, empl_contrat, empl_prise_fonction, empl_date_fin_essai1, empl_date_fin_essai2);

        if (rowsAffected > 0) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Employé ajouté avec succès");
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Échec de l'ajout de l'employé");
        }
    }

    @PostMapping("/updateEmploye")
    public ResponseEntity<String> updateEmploye(
            @RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String empl_matricule, @RequestParam(required = true) String empl_nom, @RequestParam(required = true) String empl_prenom, @RequestParam(required = true) String empl_date_naiss, @RequestParam(required = true) String empl_departement, @RequestParam(required = true) String empl_poste, @RequestParam(required = true) String empl_contrat, @RequestParam(required = true) String empl_prise_fonction, @RequestParam(required = false) String empl_date_fin_essai1, @RequestParam(required = false) String empl_date_fin_essai2
    ) {

        int rowsAffected = rhRepository.updateEmploye(sessionId, empl_matricule, empl_nom, empl_prenom, empl_date_naiss, empl_departement, empl_poste, empl_contrat, empl_prise_fonction, empl_date_fin_essai1, empl_date_fin_essai2);

        if (rowsAffected > 0) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Employé modifié avec succès");
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Échec de la modification de l'employé");
        }
    }

    @PostMapping("/deleteEmploye")
    public ResponseEntity<String> deleteEmploye(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String empl_matricule) {

        int rowsAffected = rhRepository.deleteEmploye(sessionId, empl_matricule);

        if (rowsAffected > 0) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Employé supprimé avec succès");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Aucun employé trouvé avec le matricule : " + empl_matricule);
        }
    }

    @GetMapping("/listDetailIntegr")
    public ResponseEntity<List<List<Object>>> onlistdetailintegr(@RequestHeader("Authorization") String sessionId, @RequestParam String codeInteg ,@RequestParam String matricule) {
        try {
            List<List<Object>> cmd = rhRepository.findAllDetailIntegr(sessionId, codeInteg ,matricule);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateDateEtape")
    public ResponseEntity<String> updateDateEtape(@RequestHeader("Authorization") String sessionId, @RequestParam String ligne_etape_code, @RequestParam String ligne_etape_etape, @RequestParam(required = false) String ligne_etape_date) {

        try {
            Date date = null;

            if (ligne_etape_date != null && !ligne_etape_date.isEmpty()) {
                date = Date.valueOf(ligne_etape_date);
            }
            rhRepository.updateDateEtape(sessionId, ligne_etape_code, ligne_etape_etape, date);

            return new ResponseEntity<>("Date de l'étape mise à jour avec succès", HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseEntity<>("Erreur lors de la mise à jour de la date : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/updateDateEffect")
    public ResponseEntity<String> updateDateEffect(@RequestHeader("Authorization") String sessionId, @RequestParam String mise_dispo_matr, @RequestParam String mise_dispo_cat, @RequestParam String mise_dispo_materiel, @RequestParam(required = false) String mise_dispo_date_effect) {
        try {
            Date dateEffect = null;

            if (mise_dispo_date_effect != null
                    && !mise_dispo_date_effect.isEmpty()) { dateEffect = Date.valueOf(mise_dispo_date_effect); }

            rhRepository.updateDateMatDispo(sessionId, mise_dispo_matr, mise_dispo_cat, mise_dispo_materiel, dateEffect);

            return new ResponseEntity<>("Date effective mise à jour avec succès", HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseEntity<>("Erreur lors de la mise à jour de la date : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listCategorie")
    public ResponseEntity<List<Categorie>> onListCategorie(@RequestHeader("Authorization") String sessionId) {

        try {
            List<Categorie> categories = rhRepository.findAllListCategorie(sessionId);
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) { e.printStackTrace();return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @PostMapping("/updateDateFinCdd")
    public ResponseEntity<String> updateDateFinCdd(@RequestHeader("Authorization") String sessionId, @RequestParam String demandeCode, @RequestParam(required = false) int moisFinCDD) {
        try {

            rhRepository.updateDateFinCdd(sessionId, demandeCode, moisFinCDD);

            return new ResponseEntity<>("mise à jour avec succès", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>("Erreur lors de la mise à jour: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 /*   @GetMapping("/listParamsFormation")
    public ResponseEntity<List<List<Object>>> onListParamsFormation(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String departement_code , @RequestParam(required = false) String poste_code){
        try {
            List<List<Object>> cv = rhRepository.findAllListParamsFormation(sessionId, departement_code ,poste_code);
            if (cv.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } return new ResponseEntity<>(cv,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } */

    @GetMapping("/listParamsFormation")
    public ResponseEntity<?> onListParamsFormation(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String departement_code, @RequestParam(required = false) String poste_code) {
        try {
            Map<String, Object> result = rhRepository.findAllListParamsFormation(sessionId, departement_code, poste_code);

            if (result == null || result.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.ok(result);

        } catch (Exception e) {

            e.printStackTrace();

            String message = (e.getCause() != null)
                    ? e.getCause().getMessage()
                    : e.getMessage();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(message);
        }
    }

    @PostMapping("/insert_ParamsFormation")
    public ResponseEntity<String> insert_paramsFormation(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String form_code,@RequestParam(required = true) String code_poste,@RequestParam(required = true) String dep ) {
        try {
            int rowsAffected = rhRepository.insert_paramsFormation(sessionId,form_code,code_poste,dep);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion");
            }
        } catch (DataAccessException e) {
            // Log the exception details

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion");
        }
    }

    @DeleteMapping("/deleteParamsFormation")
    public ResponseEntity<Void> deleteParamsFormation(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String form_code,@RequestParam(required = true) String code_poste,@RequestParam(required = true) String dep) {
        rhRepository.deleteParamsFormation(sessionId,form_code,code_poste,dep);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/updateDateFormation")
    public ResponseEntity<String> updateDateFormation(@RequestHeader("Authorization") String sessionId, @RequestParam String matricule, @RequestParam String form_code, @RequestParam String libelle_form, @RequestParam(required = false) String date_reel) {

        try {
            Date date = null;
            if (date_reel != null && !date_reel.isEmpty()) { date = Date.valueOf(date_reel); }
            rhRepository.updateDateForm(sessionId, matricule, form_code, libelle_form, date);

            return new ResponseEntity<>("Date de formation mise à jour avec succès", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    "Erreur lors de la mise à jour de la date de formation : " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }







}
