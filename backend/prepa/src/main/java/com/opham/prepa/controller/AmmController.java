package com.opham.prepa.controller;

import com.opham.prepa.model.Amm.*;
import com.opham.prepa.repository.Amm.AmmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")

public class AmmController {
    @Autowired
    private AmmRepository ammRepository;

    @GetMapping("/listFabricant")
    public ResponseEntity<List<fabricant>>onfabricant(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String libelle) {
        try {
            List<fabricant> cmd = ammRepository.findFabricant(sessionId,libelle);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateFabricant")
    public ResponseEntity<String> update_Fabricant(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String libelle_update ,@RequestParam(required = true) String code_update) {

        int rowsAffected = ammRepository.updateLibelleFabricant(sessionId,libelle_update,code_update);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
        }
    }


    @PostMapping("/insert_fabricant")
    public ResponseEntity<String> insert_Fabricant(@RequestHeader("Authorization") String sessionId, @RequestParam (required = true) String codeFab,@RequestParam (required = true) String libelleFab) {
        try {
            int rowsAffected = ammRepository.insertFabricant(sessionId,codeFab,libelleFab);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion dans FABRICANT");
            }
        } catch (DataAccessException e) {
            // Log the exception details

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion de FABRICANT");
        }
    }

    @GetMapping("/listDossier")
    public ResponseEntity<List<dossier>> listDossier(@RequestHeader("Authorization") String sessionId,@RequestParam (required = false) String labo) {
        try {
            List<dossier> cmd = ammRepository.findDossier(sessionId,labo);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*Si dossier prend 2 résultats*/

 /*   @GetMapping("/listDossier")
    public ResponseEntity<List<List<Object>>> onfabricant(@RequestHeader("Authorization") String sessionId) {
        try {
            List<List<Object>> cmd = ammRepository.findDossier(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } */



    @PostMapping("/insert_dossier")
    public ResponseEntity<String> insertDossier(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String codeAmm, @RequestParam(required = false ) String codeArticle, @RequestParam(required = false) String libelle ,@RequestParam(required = false) String focode ,@RequestParam(required = false) String fab_code) {
        try {
            // Appel du repository pour insérer une ligne
            int rowsAffected = ammRepository.insertLigneDossier(sessionId, codeAmm, codeArticle, libelle, focode ,fab_code);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion dans AMM_LIGNE_DOSSIER");
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion de l'article");
        }
    }

    @PostMapping("/insertAmm_dossier")
    public ResponseEntity<String> insertDossierAmm(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String codeAmm, @RequestParam(required = false ) String responsable, @RequestParam(required = false) String type ,@RequestParam(required = false) String classification ,@RequestParam(required = false) String ref_variation) {
        try {
            // Appel du repository pour insérer une ligne
            int rowsAffected = ammRepository.insertAmmDossier(sessionId, codeAmm, responsable,type,classification,ref_variation);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Échec de l'insertion dans AMM_LIGNE_DOSSIER");
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion de l'article");
        }
    }

    @GetMapping("/listNumDossier")
    public ResponseEntity<List<List<Object>>> onlistNumDossier(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String prefixe , @RequestParam(required = true) String nom_table) {
        try {
            List<List<Object>> cmd = ammRepository.findnumdossier(sessionId,prefixe,nom_table);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listTitulaire")
    public ResponseEntity<List<ListTitulaire>>onTitulaire(@RequestHeader("Authorization") String sessionId) {
        try {
            List<ListTitulaire> cmd = ammRepository.findTitulaire(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/updateDossier")
    public ResponseEntity<String> update_Dossier(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String dossierCode,@RequestParam(required = true) String type_update ,@RequestParam(required = false) String respo_update ,@RequestParam(required = false) String class_update) {

        int rowsAffected = ammRepository.updateDossier(sessionId,dossierCode,type_update,respo_update,class_update);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
        }
    }


    @GetMapping("/listArticleDetail")
    public ResponseEntity<List<List<Object>>> onArticleDetail(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = true) String dossier) {
        try {
            List<List<Object>> cmd = ammRepository.findArticleDetail(sessionId ,dossier);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listEtape")
    public ResponseEntity<List<etapeList>>onEtape(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = true) String ammCode) {
        try {
             List<etapeList> cmd = ammRepository.findEtape(sessionId,ammCode);
             if (cmd.isEmpty()) {
                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
             }
             return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/insertAmm_dossier_etape")
    public ResponseEntity<String> insertEtapeAmm(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String codeAmm, @RequestParam(required = false ) String Etape, @RequestParam(required = false) int Etat,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateNew) {
        try {
            // Appel du repository pour insérer une ligne
            int rowsAffected = ammRepository.insertAmmEtape(sessionId, codeAmm, Etape,Etat,dateNew);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Échec de l'insertion dans AMM_DOSSIER_ETAPE");
            }

        } catch (DataAccessException e) {
                e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion");
            }
    }

    @GetMapping("/listFournir")
    public ResponseEntity<List<fournirList>>onFournir(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = true) String ammCode) {
        try {
            List<fournirList> cmd = ammRepository.findFournir(sessionId,ammCode);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @PostMapping("/insertLigneDossier")
    public ResponseEntity<String> insertFournir(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String codeAmm, @RequestParam(required = true ) String codeDossier, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_fournir) {
        try {
            // Appel du repository pour insérer une ligne
            int rowsAffected = ammRepository.insertFournir(sessionId, codeAmm, codeDossier,date_fournir);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion");
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion");
        }
    }

    @PostMapping("/updateEtape")
    public ResponseEntity<String> update_Etape(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateUpdate, @RequestParam(required = true) String ds_code,@RequestParam(required = true) String etape) {
        int rowsAffected = ammRepository.updateEtape(sessionId,dateUpdate,ds_code,etape);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
            }
    }


    @PostMapping("/insert_histo")
    public ResponseEntity<String> insertHisto(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String codeAmm, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,@RequestParam(required = false) String commentaire) {
        try {
            // Appel du repository pour insérer une ligne
            int rowsAffected = ammRepository.insertHisto(sessionId, codeAmm, date, commentaire);
                if (rowsAffected > 0) {
                    return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion dans AMM_HISTO");
                    }
        } catch (DataAccessException e) {
                e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion de l'article");
                }
    }


    @PostMapping("/updateFournir")
    public ResponseEntity<String> update_fournir(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateUpdate, @RequestParam(required = true) String ds_code,@RequestParam(required = true) String dossierLcode) {
        int rowsAffected = ammRepository.updateFournir(sessionId,dateUpdate,ds_code,dossierLcode);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
            }
    }

    @GetMapping("/allDossierList")
    public ResponseEntity<List<AllDossierList>>onlistdossierall(@RequestHeader("Authorization") String sessionId) {
        try {
            List<AllDossierList> cmd = ammRepository.findalllistdossier(sessionId);
                if (cmd.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                    return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insertDossierFournir")
    public ResponseEntity<String> insertdocfournir(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String dossier_code, @RequestParam(required = false ) String dossier_lib) {
        try {
            // Appel du repository pour insérer une ligne
            int rowsAffected = ammRepository.insertdocfournir(sessionId, dossier_code, dossier_lib);
                if (rowsAffected > 0) {
                    return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion dans AMM_LIGNE_DOSSIER");
                }
        } catch (DataAccessException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion de l'article");
        }
    }


    @PostMapping("/updateAmmDossier")
    public ResponseEntity<String> update_amm_dossier(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String ds_code,@RequestParam(required = true) int decision ,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date ,@RequestParam(required = false) String text) {
        int rowsAffected = ammRepository.updateAmmDossier(sessionId,ds_code,decision,date,text);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
            }
    }

    @PostMapping("/insert_dossier_defaut")
    public ResponseEntity<String> insertDossierDefaut(@RequestHeader("Authorization") String sessionId,@RequestParam(required = false) String type_dossier ,@RequestParam(required = false) String code_dossier,@RequestParam(required = false) String classification) {
        try {
            // Appel du repository pour insérer une ligne
            int rowsAffected = ammRepository.insertDossierDefaut(sessionId,type_dossier, code_dossier,classification);
                if (rowsAffected > 0) {
                    return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion dans AMM_LIGNE_DOSSIER");
                }
        } catch (DataAccessException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion de l'article");
        }
    }

        @GetMapping("/list_gen")
            public ResponseEntity<List<allListGen>>onlistgen(@RequestHeader("Authorization") String sessionId) {
                try {
                    List<allListGen> cmd = ammRepository.findalllistgen(sessionId);
                    if (cmd.isEmpty()) {
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                    }
                        return new ResponseEntity<>(cmd, HttpStatus.OK);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
            }

        @GetMapping("/list_spe")
            public ResponseEntity<List<allListSpe>>onlistspe(@RequestHeader("Authorization") String sessionId) {
                try {
                        List<allListSpe> cmd = ammRepository.findalllistspe(sessionId);
                        if (cmd.isEmpty()) {
                            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                        }
                            return new ResponseEntity<>(cmd, HttpStatus.OK);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        @DeleteMapping("/delete_fournir")
            public ResponseEntity<Void> deleteFournir(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String dossier_code) {
                ammRepository.deleteFournir(sessionId,dossier_code);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @DeleteMapping("/delete_dossier_defaut")
            public ResponseEntity<Void> deleteDossierDefaut(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String classification,@RequestParam(required = true) String ds_code ,@RequestParam(required = true) String type_dossier) {
                ammRepository.deleteDossierDefaut(sessionId,classification,ds_code,type_dossier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    @GetMapping("/listMotif")
    public ResponseEntity<List<motifDetail>>onMotif(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = true) String ammCode) {
        try {
            List<motifDetail> cmd = ammRepository.findMotif(sessionId,ammCode);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listNumDS")
    public ResponseEntity<List<listnumDS>>onNumDS(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = true) String dossier ,@RequestParam(required = true) int etat) {
        try {
            List<listnumDS> cmd = ammRepository.findnumDS(sessionId,dossier,etat);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listNumFB")
    public ResponseEntity<List<listnumFB>>onNumFB(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = true) String dossier ,@RequestParam(required = true) int etat) {
        try {
            List<listnumFB> cmd = ammRepository.findnumFB(sessionId,dossier,etat);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/list_type_reclam")
    public ResponseEntity<List<typeReclam>>onTypeReclam(@RequestHeader("Authorization") String sessionId) {
        try {
            List<typeReclam> cmd = ammRepository.findTypeReclam(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/insert_reclam")
    public ResponseEntity<String> insertReclam(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String code_ds, @RequestParam(required = false ) String reclamation_code, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_reclam,@RequestParam(required = false ) String nbre_reclam) {
        try {
            // Appel du repository pour insérer une ligne
            int rowsAffected = ammRepository.insertReclam(sessionId, code_ds, reclamation_code, date_reclam,nbre_reclam);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion dans AMM_LIGNE_DOSSIER");
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion de l'article");
        }
    }

    @PostMapping("/updateAmmDosReclam")
    public ResponseEntity<String> update_Amm_reclam(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String ds_code ,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_reclam) {

        int rowsAffected = ammRepository.updateammreclam(sessionId,ds_code,date_reclam);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
        }
    }

    @GetMapping("/list_reclam_detail")
    public ResponseEntity<List<reclamationDetail>>onDetailReclam(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String ammCode) {
        try {
            List<reclamationDetail> cmd = ammRepository.findDetailReclam(sessionId,ammCode);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list_amm_dossier")
    public ResponseEntity<List<list_amm_dossier>>onListAmmDossier(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String ammCode) {
        try {
            List<list_amm_dossier> cmd = ammRepository.findlistAmmDossier(sessionId,ammCode);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/updateReclam")
    public ResponseEntity<String> update_reclam(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateUpdate, @RequestParam(required = true) String ds_code,@RequestParam(required = true) String code_lib,@RequestParam(required = false)Integer annule_reclam ) {
        int rowsAffected = ammRepository.updateReclam(sessionId,dateUpdate,ds_code,code_lib,annule_reclam);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
        }
    }

    @GetMapping("/list_respo")
    public ResponseEntity<List<ListRespo>>onlistrespo(@RequestHeader("Authorization") String sessionId) {
        try {
            List<ListRespo> cmd = ammRepository.findListRespo(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateAmmLigneArticle")
    public ResponseEntity<String> update_amm_ligne_article(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String ds_code,@RequestParam(required = false) String num_Amm ,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_Amm ,@RequestParam(required = true) int seq) {
        int rowsAffected = ammRepository.updateAmmLigneArticle(sessionId,ds_code,num_Amm,date_Amm,seq);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
        }
    }

   @GetMapping("/listArticleLier")
    public ResponseEntity<List<ListArticleLier>> onListArticleLier(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String titulaire ,@RequestParam(required = true) int type) { // <- required false
        try {
            // Si "null" en chaîne, convertir en vrai null
            if ("null".equals(titulaire)) {
                titulaire = null;
            }

            List<ListArticleLier> cmd = ammRepository.findListArticleLier(sessionId, titulaire,type);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*        @GetMapping("/listArticleLier")
            public ResponseEntity<List<List<Object>>> onListArticleLier(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = false) String titulaire ,@RequestParam(required = true) int type) {
                try {

                    // Si "null" en chaîne, convertir en vrai null
                    if ("null".equals(titulaire)) {
                        titulaire = null;
                    }

                    List<List<Object>> cmd = ammRepository.findListArticleLier(sessionId ,titulaire,type);
                    if (cmd.isEmpty()) {
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                    }
                    return new ResponseEntity<>(cmd, HttpStatus.OK);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } */


    @PostMapping("/updateCodeArticleLigneArticle")
    public ResponseEntity<String> update_codeArticle(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String ds_code,@RequestParam(required = true) String codeArticle ,@RequestParam(required = true) int seq) {
        int rowsAffected = ammRepository.updateCodeArticle(sessionId,ds_code,codeArticle,seq);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
        }
    }


    @PostMapping("/insertLigneVariation")
    public ResponseEntity<String> insertLigneVariation(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String codeAmm,@RequestParam(required = false) String libelle_variation) {
        try {
            // Appel du repository pour insérer une ligne
            int rowsAffected = ammRepository.insertLigneVariation(sessionId, codeAmm, libelle_variation);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Échec de l'insertion dans AMM_LIGNE_DOSSIER");
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion de l'article");
        }
    }

    @PostMapping("/insert_variation")
    public ResponseEntity<String> insertVariation(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String codeAmm, @RequestParam(required = false) String libelle_variation) {
        try {
            // Appel du repository pour insérer une ligne
            int rowsAffected = ammRepository.insertVariation(sessionId, codeAmm, libelle_variation);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion dans AMM_VARIATION");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion de l'article");
        }
    }


    @GetMapping("/isImportMois")
    public boolean isImportMois(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) int date,@RequestParam(required = true) int mois) {
        return ammRepository.importMoisExist(sessionId,date,mois);
    }

    @PostMapping(value = "/import_midac", consumes = "application/json; charset=UTF-8")
    public ResponseEntity<String> import_midac(@RequestHeader("Authorization") String sessionId, @RequestBody Midac t) {
        try {
            int updatedRows = ammRepository.import_midac(sessionId, t);
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


  /*  @GetMapping("/list_midac")
    public ResponseEntity<List<Midac>>onListMidac(@RequestHeader("Authorization") String sessionId) {
        try {
            List<Midac> cmd = ammRepository.findlistMidac(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } */



    @GetMapping("/list_midac")
    public ResponseEntity<List<List<Object>>> onListMidac(@RequestHeader("Authorization") String sessionId) {
        try {
            List<List<Object>> cmd = ammRepository.findlistMidac(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Update FAR
    @PostMapping("/updateMidacFar")
    public ResponseEntity<String> update_codeArticle(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = true) String code_pro ,@RequestParam(required = true) String codeArticle) {
        int rowsAffected = ammRepository.updateFarMidac(sessionId,code_pro,codeArticle);
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour fait avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour");
        }
    }


    @PostMapping("/updateImage")
    public ResponseEntity<String> onInsertDemande(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) int seq,@RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        byte[] file = null;
        if (image != null && !image.isEmpty()) {
            file = image.getBytes();
        }
        int rowsAffected = ammRepository.updateImage(sessionId,seq, file );
        if (rowsAffected > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Image insérée avec succès");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec insertion");
    }



    @GetMapping("/listTitulaireAmm")
    public ResponseEntity<List<list_titulaire_amm>> onListTitulaire(@RequestHeader("Authorization") String sessionId) {
        try {

            List<list_titulaire_amm> cmd = ammRepository.findListTitulaire(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   /* @PostMapping("/updateMidacFar")
    public ResponseEntity<String> updateCodeArticle(@RequestHeader("Authorization") String sessionId, @RequestParam String code_pro, @RequestParam String codeArticle) {
        try {
            int rowsAffected = ammRepository.updateFarMidac(sessionId, code_pro, codeArticle);
            return ResponseEntity.ok("Mise à jour effectuée avec succès");
        } catch (Exception e) {
            // log l'erreur ici si tu veux
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Échec de la mise à jour : " + e.getMessage());
        }
    } */


    @PostMapping("/insertAmmReclam")
    public ResponseEntity<String> insertAmmReclam(@RequestHeader("Authorization") String sessionId, @RequestParam(required = false) String ds_code, @RequestParam(required = false ) String code_reclam ,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_reclam ,@RequestParam(required = false ) String nb_reclam) {
        try {
            // Appel du repository pour insérer une ligne
            int rowsAffected = ammRepository.insertAmmReclam(sessionId, ds_code, code_reclam,date_reclam,nb_reclam);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Insertion Done");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'insertion dans AMM_LIGNE_DOSSIER");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'insertion de l'article");
        }
    }


    @GetMapping("/listArenouveler")
    public ResponseEntity<List<amm_renouveler>> onListArenouveler(@RequestHeader("Authorization") String sessionId ,@RequestParam(required = false) int type) {
        try {

            List<amm_renouveler> cmd = ammRepository.findListArenouveler(sessionId,type);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ficheArtWork")
    public ResponseEntity<?> onFicheArtWork(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String amm_ds_code, @RequestParam(required = true) String ligne_code_article, @RequestParam(required = true) String ligne_libelle_article) {
        try {
            Object fiche = ammRepository.findFicheArtWork(sessionId, amm_ds_code, ligne_code_article, ligne_libelle_article);

            if (fiche == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(fiche, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listAbrogation")
    public ResponseEntity<List<Abrogation>> onListAbrogation(@RequestHeader("Authorization") String sessionId ) {
        try {

            List<Abrogation> cmd = ammRepository.findAbrogation(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateAbrogation")
    public ResponseEntity<String> updateAbrogation(@RequestHeader("Authorization") String sessionId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateValidation,
            @RequestParam String code) {

        int rowsAffected = ammRepository.updateDateAbrogation(sessionId, dateValidation, code);

        if (rowsAffected > 0) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Mise à jour faite avec succès");
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Échec de la mise à jour");
        }
    }






}
