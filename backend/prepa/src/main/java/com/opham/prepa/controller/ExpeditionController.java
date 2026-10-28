package com.opham.prepa.controller;

import com.opham.prepa.model.Expedition.*;
import com.opham.prepa.model.Utils.Credentials;
import com.opham.prepa.model.Utils.LoginPlus;
import com.opham.prepa.model.ValidationCommande.CommandeAValider;
import com.opham.prepa.model.ValidationCommande.Reglement;
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
public class ExpeditionController {


    @Autowired
     ExpeditionRepository expeditionRepository;


    @PostMapping("/insertTrans_facture")
    public ResponseEntity<String> insertTrans_facture(@ModelAttribute Trans_facture T,@RequestHeader("Authorization") String sessionId) {

        int rowsAffected = expeditionRepository.insertTransFacture(sessionId,T);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Trans_facture inséré avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion de l'Trans_facture");
        }
    }

    @PostMapping("/insertTrans_facture_L")
    public ResponseEntity<String> insertTrans_facture_L(@ModelAttribute Trans_facture_L T, @RequestHeader("Authorization") String sessionId) {

        int rowsAffected = expeditionRepository.insertTransFacture_L(sessionId,T);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Trans_facture_L inséré avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion de l'Trans_facture_L");
        }
    }


    @PostMapping("/updateTrans_facture")
    public ResponseEntity<String> updateTrans_facture(@ModelAttribute Trans_facture T,@RequestHeader("Authorization") String sessionId) {

        int rowsAffected = expeditionRepository.updateTransFacture(sessionId,T);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Article inséré avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion de l'article");
        }
    }


    @GetMapping("/listAttributionLv")
    public ResponseEntity<List<Trans_Attribution_Lv>> onattriblv(@RequestHeader("Authorization") String sessionId , @RequestParam(required = false) String transporteurCode , @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDeb , @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin) {
        try {
            List<Trans_Attribution_Lv> cmd = expeditionRepository.findAttribLv(sessionId,transporteurCode,dateDeb,dateFin);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listTrans_Liv_L")
    public ResponseEntity<List<Trans_facture_L>> listTrans_Liv_L(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String code ) {
        try {
            List<Trans_facture_L> cmd = expeditionRepository.listTrans_Fact_L(sessionId,code);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteFact_TransL")
    public ResponseEntity<Void> deleteFact_TransL(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String code, @RequestParam(required = true) String liv) {
        expeditionRepository.deleteTrans_Fact(sessionId,liv,code);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("test_attrib_liv")
    public ResponseEntity<Integer> onCreeTraiteADV(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String liv, @RequestParam(required = true) String transport) {
        int retour = expeditionRepository.test_Attrib_Liv(sessionId, liv, transport);
        return new ResponseEntity<>(retour, HttpStatus.OK);
    }


    @PostMapping("/updateTrans_valide")
    public ResponseEntity<String> updateTrans_valide(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String trans_code) {

        int rowsAffected = expeditionRepository.updateTransValide(sessionId,trans_code);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
        }
    }


    @GetMapping("/detail_lv")
    public ResponseEntity<List<Trans_Detail_Lv>> listDetailLv(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String newItem , @RequestParam(required = true) int type) {
        try {
            List<Trans_Detail_Lv> cmd = expeditionRepository.listDetailLv(sessionId,newItem ,type);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detail_nb_cmd")
    public ResponseEntity<List<Trans_Detail_Nb_cmd>> listDetailNbCmd(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String code_ft , @RequestParam(required = true) int type) {
        try {
            List<Trans_Detail_Nb_cmd> cmd = expeditionRepository.listDetailNbCmd(sessionId,code_ft ,type);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ajout_lv")
    public ResponseEntity<List<Trans_Detail_Lv>> ajout_lv(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String lv) {
        try {
            List<Trans_Detail_Lv> cmd = expeditionRepository.ajoutLv(sessionId,lv);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  /*  @GetMapping("/livraison")
    public ResponseEntity<List<List<Object>>> listLivraison(@RequestHeader("Authorization") String sessionId) {
        try {
            List<List<Object>> cmd = expeditionRepository.listLivraison(sessionId);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } */


    @GetMapping("/livraison")
    public ResponseEntity<List<List<Object>>> listLivraison(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = true) String modeLiv) {
        try {
            List<List<Object>> cmd = expeditionRepository.listLivraison(sessionId,modeLiv);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/ind_livraison")
    public ResponseEntity<List<List<Object>>> listIndLivraison(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_deb,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_fin,@RequestParam(required = true) String modeLiv) {
        try {
            List<List<Object>> cmd = expeditionRepository.listIndLivraison(sessionId,date_deb,date_fin,modeLiv);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/ind_chauffeur")
    public ResponseEntity<List<List<Object>>> listIndChauffeur(@RequestHeader("Authorization") String sessionId) {
        try {
            List<List<Object>> cmd = expeditionRepository.listIndChauffeur(sessionId);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edition_lv")
    public ResponseEntity<List<edition_lv>> listEditionLv(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = false) String livraison ,@RequestParam(required = false) String commande,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date ,@RequestParam(required = false) String chauf,@RequestParam(required = false) String voit,@RequestParam(required = false) Integer saisie) {
        try {
            List<edition_lv> cmd = expeditionRepository.listEditionLv(sessionId,livraison,commande,date,chauf,voit,saisie);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/printLV")
    public ResponseEntity<byte[]> generateReport(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String code) {
        byte[] reportBytes = expeditionRepository.genererLV(sessionId, code);

        if (reportBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "Fiche_LV.pdf");

            return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/params_lv")
    public ResponseEntity<List<params_liv>> listparamsLv(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String code_client) {
        try {
            List<params_liv> cmd = expeditionRepository.listparamsLv(sessionId ,code_client);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/params_lv_seq")
    public ResponseEntity<List<params_liv_adr>> listparamsLv_adr(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) int seq) {
        try {
            List<params_liv_adr> cmd = expeditionRepository.listparamsLv_adr(sessionId ,seq);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/printCourse")
    public ResponseEntity<byte[]> generateReportCourse(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String code) {
        byte[] reportBytes = expeditionRepository.genererCourse(sessionId, code);

        if (reportBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "Fiche_course.pdf");

            return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @DeleteMapping("/delete_params")
        public ResponseEntity<Void> deleteParams(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String code_client, @RequestParam(required = true) int jour ,@RequestParam(required = true) int heure) {
            expeditionRepository.deleteParams(sessionId,code_client,jour,heure);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @DeleteMapping("/delete_paramsAdr")
        public ResponseEntity<Void> deleteParamsAdr(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = true) int seq, @RequestParam(required = true) String code_client, @RequestParam(required = true) int jour ,@RequestParam(required = true) int heure) {
            expeditionRepository.deleteParamsAdr(sessionId,seq,code_client,jour,heure);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PostMapping("/insertParams")
        public ResponseEntity<String> insert_params(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String code_client, @RequestParam(required = true) int jour ,@RequestParam(required = true) int heure) {
            try {
                int rowsAffected = expeditionRepository.insert_params(sessionId,code_client,jour,heure);
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


        @PostMapping("/insertParamsAdr")
        public ResponseEntity<String> insert_paramsAdr(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) int seq, @RequestParam(required = true) String code_client, @RequestParam(required = true) int jour ,@RequestParam(required = true) int heure) {
            try {
                int rowsAffected = expeditionRepository.insert_paramsAdr(sessionId,seq,code_client,jour,heure);
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

    @GetMapping("/duree_lv")
    public ResponseEntity<List<DureeLV>> getDuree(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = true) String code) {
        try {
            List<DureeLV> cmd = expeditionRepository.getDureeLv(sessionId,code);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insert_dureeLV")
    public ResponseEntity<String> insert_dureeLV(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = true) String code,
            @RequestParam(required = true) String code_client,
            @RequestParam(required = false) String h_arrive,
            @RequestParam(required = false) String h_depart
    ) {
        try {
            Timestamp tsArrive = Timestamp.from(Instant.parse(h_arrive));
            Timestamp tsDepart = Timestamp.from(Instant.parse(h_depart));

            int rowsAffected = expeditionRepository.onInsertDuree(
                    sessionId, code, code_client, tsArrive, tsDepart
            );

            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Trans_facture_L inséré avec succès");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Échec de l'insertion de l'Trans_facture_L");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Format de date invalide : " + e.getMessage());
        }
    }

    @PostMapping("/updateSaisieLV")
    public ResponseEntity<String> onUpdateInfoSaisieLV(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String code, @RequestParam(required = true) int remplissage, @RequestParam(required = true) int cpt_arrive,@RequestParam(required = true) int cpt_depart,@RequestParam(required = true) int saisie) {

        int rowsAffected = expeditionRepository.onInfoSaisiLV(sessionId,code, remplissage, cpt_arrive,  cpt_depart,saisie);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Trans_facture_L inséré avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion de l'Trans_facture_L");
        }
    }

    @PostMapping("/onSupprLV")
    public ResponseEntity<String> onSupprLV(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String code,@RequestParam String liv,@RequestParam(required = false) String raison,@RequestParam(required = false) String user_annul
          ) {

        try {
            expeditionRepository.onSupprLV(sessionId, code,liv,raison,user_annul);
            return ResponseEntity.ok("Suppression effectuée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur : " + e.getMessage());
        }
    }

 /*   @GetMapping("/attrib_axe")
    public ResponseEntity<List<attrib_axe>> listattribAxe(@RequestHeader("Authorization") String sessionId , @RequestParam(required = false) String code_client) {
        try {
            List<attrib_axe> cmd = expeditionRepository.listattribAxe(sessionId ,code_client);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } */


    @GetMapping("/attrib_axe")
    public ResponseEntity<List<List<Object>>> listattribAxe(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = false) String code_client) {
        try {
            List<List<Object>> cmd = expeditionRepository.listattribAxe(sessionId ,code_client);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }








    @PostMapping("/updatAxeFadr")
    public ResponseEntity<String> onUpdateAxe(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) int seq ,@RequestParam(required = false) String axe) {

        int rowsAffected = expeditionRepository.onUpdateAxe(sessionId,seq,axe);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Trans_facture_L inséré avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion de l'Trans_facture_L");
        }
    }

    @PostMapping("/updatModeLivFadr")
    public ResponseEntity<String> onUpdateModeLiv(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) int seq ,@RequestParam(required = false) String mode_livraison) {

        int rowsAffected = expeditionRepository.onUpdateModeLiv(sessionId,seq,mode_livraison);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Trans_facture_L inséré avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion de l'Trans_facture_L");
        }
    }


    @GetMapping("/testAvAddLiv")
    public ResponseEntity<TestAvAddCmdLiv> onTestAvAddLiv(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = true) String code,
            @RequestParam(required = true) int isCc
    ) {
        try {
            TestAvAddCmdLiv result = expeditionRepository.ontestAvAddCmdLiv(sessionId, code, isCc);

            if (result == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add_cmd_liv")
    public ResponseEntity<Integer> onAddCmdLiv(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String cmd,
            @RequestParam(required = false) String liv,@RequestParam(required = false) String raison
    ) {
        try {
            int updated = expeditionRepository.onAddCmdLiv(sessionId, cmd, liv,raison);

            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(0);
        }
    }

    @GetMapping("/situationCmdAxe")
    public ResponseEntity<?> onSituationCmdAxe(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = false) String client) {

        try {

            List<Situation_axe> cmd =
                    expeditionRepository.situationparAxe(sessionId);

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

    @GetMapping("/listVoit")
    public ResponseEntity<?> onListVoit(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = true) String type) {

        try {

            List<Chauf> cmd = expeditionRepository.onListChauf(sessionId,type);

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

    @GetMapping("/listAtteLiv")
    public ResponseEntity<?> onLIstAtteLiv(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = true) String mode) {

        try {

            List<ListAtteLiv> cmd = expeditionRepository.onListAtteLiv(sessionId,mode);

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

    @GetMapping("/isLiv")
    public ResponseEntity<?> isLiv(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = true) String CC) {

        try {
            boolean result = expeditionRepository.isLiv(sessionId, CC);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            String message = (e.getCause() != null)
                    ? e.getCause().getMessage()
                    : e.getMessage();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(message);
        }
    }

    @GetMapping("/isAtteLiv")
    public ResponseEntity<?> isAtteLiv(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = true) String CC) {

        try {
            boolean result = expeditionRepository.isAtteLiv(sessionId, CC);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            String message = (e.getCause() != null)
                    ? e.getCause().getMessage()
                    : e.getMessage();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(message);
        }
    }

    @GetMapping("/createLv")
    public ResponseEntity<?> createLv(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String chauf,
            @RequestParam String voit,
            @RequestParam int etat,
            @RequestParam(required = false) String course,
            @RequestParam(required = false) String livreur,
            @RequestParam(required = false) String axe) {

        try {
            createlv result = expeditionRepository.onCreateLv(
                    sessionId, chauf, voit, etat, course, livreur, axe
            );

            return (result == null)
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

    @PostMapping("/updateLivCmd")
    public ResponseEntity<?> updateLivCmd(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String cmd,
            @RequestParam String code_liv) {

        try {

            int success = expeditionRepository.updateLivCmd(
                    sessionId, cmd, code_liv
            );

            return ResponseEntity.ok(success);

        } catch (Exception e) {

            e.printStackTrace(); // toujours utile en debug

            Throwable root = e;
            while (root.getCause() != null) {
                root = root.getCause();
            }

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(root.getMessage());
        }
    }

    @GetMapping("/onIndicatChauf")
    public ResponseEntity<?> onIndicatChauf(
            @RequestHeader("Authorization") String sessionId) {

        try {

            Map<String, Object> result =
                    expeditionRepository.onIndicatChauf(sessionId);

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

    @GetMapping("/listCmdASupprLiv")
    public ResponseEntity<?> onListCmdLivSuppr(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = true) String liv,@RequestParam(required = true) String client) {

        try {

            List<ListCmdLivAsuppr> cmd = expeditionRepository.onSupprLVCmd(sessionId,liv,client);

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

    @GetMapping("/listLivreurOccupe")
    public ResponseEntity<?> onLivreurOccupe(
            @RequestHeader("Authorization") String sessionId) {

        try {

            List<LoginPlus> cmd = expeditionRepository.onLivreurOccupe(sessionId);

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

    @GetMapping("/listProximite")
    public ResponseEntity<?> onlistProximite(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = true) String type) {

        try {

            List<proximite> cmd = expeditionRepository.onlistProximite(sessionId,type);

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
    @GetMapping("/detail_editionLV")
    public ResponseEntity<?> onDetailEditionLv(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = false) String mode,
            @RequestParam(required = false) String sit_com,
            @RequestParam(required = false) String axe
    ) {

        try {

            List<detail_editionLv> cmd =
                    expeditionRepository.detailEditionLv(sessionId,mode,sit_com,axe);

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

    @GetMapping("/voitureCh")
    public ResponseEntity<?> onVoitureCh(
            @RequestHeader("Authorization") String sessionId) {

        try {

            List<VoitureCh> cmd =
                    expeditionRepository.onVoitureCh(sessionId);

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

    @PostMapping("/insertVoitCh")
    public ResponseEntity<?> insertVoitCh(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String voit,
            @RequestParam String chauf) {

        try {

            int rows = expeditionRepository.onInsertVoitCh(
                    sessionId,
                    voit,
                    chauf
            );

            if (rows > 0) {
                return ResponseEntity.ok("Insertion effectuée avec succès");
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Aucune ligne insérée");
            }

        } catch (Exception e) {

            e.printStackTrace();

            // récupérer la vraie erreur
            Throwable root = e;
            while (root.getCause() != null) {
                root = root.getCause();
            }

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(root.getMessage());
        }
    }

    @PostMapping("/delVoitCh")
    public ResponseEntity<?> delVoitCh(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String seq) {

        try {

            int rows = expeditionRepository.onDelVoitCh(
                    sessionId,
                    seq
            );

            if (rows > 0) {
                return ResponseEntity.ok("Suppression effectuée avec succès");
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Aucune ligne supprimée");
            }

        } catch (Exception e) {

            e.printStackTrace();

            // récupérer la vraie erreur SQL
            Throwable root = e;
            while (root.getCause() != null) {
                root = root.getCause();
            }

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(root.getMessage());
        }
    }

    @GetMapping("/testDispo")
    public ResponseEntity<?> testDispo(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String voit,
            @RequestParam String chauf) {

        try {

            boolean dispo = expeditionRepository.onTestDispo(
                    sessionId,
                    voit,
                    chauf
            );

            return ResponseEntity.ok(dispo);

        } catch (Exception e) {

            e.printStackTrace();

            // récupérer la vraie erreur SQL
            Throwable root = e;
            while (root.getCause() != null) {
                root = root.getCause();
            }

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(root.getMessage());
        }
    }



}
