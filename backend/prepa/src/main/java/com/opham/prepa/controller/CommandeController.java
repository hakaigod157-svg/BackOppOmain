package com.opham.prepa.controller;

import com.opham.prepa.model.Apreparer.*;
import com.opham.prepa.model.EnPreparation.DetailBP;
import com.opham.prepa.model.EnPreparation.EnCoursCMD;
import com.opham.prepa.model.EnPreparation.EnCoursPrepBP;
import com.opham.prepa.model.EnPreparation.ListLigneBP;
import com.opham.prepa.model.Utils.Credentials;
import com.opham.prepa.model.Utils.Droit;
import com.opham.prepa.model.genererBP.ListeCmd;
import com.opham.prepa.repository.Apreparer.CommandeRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//
//
@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.130.64:3000", "http://192.168.201.13:3000","http://192.168.130.69:8081","http://192.168.201.212:3000"}, methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH})
//@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH})
@RequestMapping("/api")

public class CommandeController {

    @Autowired
    CommandeRepository commandeRepository;

    @GetMapping("/prep")
    public ResponseEntity<?> findByDate(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = false, defaultValue = "DETAIL") String groupe,
            @RequestParam(required = false, defaultValue = "")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateliv) {

        try {

            List<Commande> cmd = commandeRepository.findByDate(sessionId, groupe, dateliv);

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

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(message);
        }
    }

    @GetMapping("/lignecmd")
    public ResponseEntity<List<LigneCommande>> findLigneCmdebyCode(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String code) {
        try {
            List<LigneCommande> cmd = commandeRepository.findLigneCmdebyCode(sessionId,code);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listDetailPrepa")
    public ResponseEntity<List<DetailPrep>> listDetailPrepa(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String code) {
        try {
            List<DetailPrep> cmd = commandeRepository.listDetailBP(sessionId,code);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/infoCmd")
    public ResponseEntity<InfoCommande> getInfoCommande(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String code) {
        try {
            InfoCommande cmd = commandeRepository.plusInfoCmd(sessionId,code);

            if (cmd != null) {
                return new ResponseEntity<>(cmd, HttpStatus.OK);

            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/updateInfoCmd")
    public ResponseEntity<InfoCommande> updateInfoCmd(@RequestHeader("Authorization") String sessionId,@RequestBody InfoCommande inf) {
        try {
            int updatedRows = commandeRepository.updateInfoCmd(sessionId, inf);
            if (updatedRows > 0) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/genererBP")
    public ResponseEntity<?> genererBP(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String code) {
        try {
            List<List<Object>> cmd = commandeRepository.genererBP(sessionId,code);

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

    @PostMapping("/insertBP")
    public ResponseEntity<String> insertBP(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String ids, @RequestParam(required = true) String code_CC, @RequestParam(required = true) String depot,@RequestParam(required = true) String user_web) {
        try {
            String cmd = commandeRepository.insert_BP(sessionId,ids, code_CC, depot,user_web);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insertL6")
    public ResponseEntity<ListeCmd> updateInfoCmd(@RequestHeader("Authorization") String sessionId,@RequestBody ListeCmd lc) {
        int updatedRows = commandeRepository.insertL6(sessionId,lc);
        if (updatedRows > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


   /* @GetMapping("/new-id")
    public ResponseEntity<String> getNewId() {
        try {
            String id = commandeRepository.getId();
            if (id.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/

    @GetMapping("/check_PP")
    public ResponseEntity<Integer> check_PP(@RequestHeader("Authorization") String sessionId) {
        try {
            int id = commandeRepository.check_PP(sessionId);

            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/listPP")
    public ResponseEntity<List<pp_list>> listPP(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_deb, @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date_fin) {
        try {
            List<pp_list> cmd = commandeRepository.listPP(sessionId,date_deb ,date_fin);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listPPDet")
    public ResponseEntity<List<pp_list_det>> listPPDet(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true)  String TPPCODE) {
        try {
            List<pp_list_det> cmd = commandeRepository.listPPDet(sessionId,TPPCODE);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/printPrepa")
    public ResponseEntity<byte[]> generateReport(@RequestHeader("Authorization") String sessionId,@RequestParam String codeBP, @RequestParam Integer isDouble) {
        byte[] reportBytes = commandeRepository.generateReport(sessionId,codeBP, isDouble);

        if (reportBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "prepaA5.pdf");

            return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/atteTransfert")
    public ResponseEntity<String> atteTransfert(@RequestHeader("Authorization") String sessionId,@RequestParam String code_CC, @RequestParam String article) {
        try {
            // Call the stored procedure
            commandeRepository.atteTransfert(sessionId,code_CC, article);

            // If no exception was thrown, assume success
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Handle exceptions (e.g., SQLException, DataAccessException)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    @PutMapping("/resumeT")
    public ResponseEntity<String> updateCCResume(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String codeCC , String ip) {
        try {
            commandeRepository.updateCCResume(sessionId, codeCC ,ip);
            return ResponseEntity.status(HttpStatus.OK).body("CCRESUME updated successfully.");
        } catch (Exception e) {
            // Log the exception (optional)
            // logger.error("Error updating CCRESUME", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating CCRESUME: " + e.getMessage());
        }
    }

    /* NON UTILISER POUR L'INSTANT */
    @PutMapping("/updateFrais")
    public ResponseEntity<String> updateFrais(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String codeCC) {
        try {
            commandeRepository.updateFrais(sessionId, codeCC);
            return ResponseEntity.status(HttpStatus.OK).body("cmd frais updated successfully.");
        } catch (Exception e) {
            // Log the exception (optional)
            // logger.error("Error updating cmd frais", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating cmd frais: " + e.getMessage());
        }
    }
    /*|******************************|*/

    @GetMapping("/encoursprep")
    public ResponseEntity<List<EnCoursCMD>> listEnCoursPrepa(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) Integer prepspecif, @RequestParam(required = true) Integer isVisible, @RequestParam(required = false, defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateliv) {
        try {
            List<EnCoursCMD> cmd = commandeRepository.listEnCoursPrepa(sessionId,prepspecif, isVisible, dateliv);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        boolean isValidCredentials = commandeRepository.checkCredentials(username, password);
//
//        if (isValidCredentials) {
//            return "Connexion réussie !";
//        } else {
//            return "Échec de la connexion.";
//        }
//    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Credentials credentials) {
//        boolean isValidCredentials = commandeRepository.checkCredentials(credentials);
//
//        if (isValidCredentials) {
//            return ResponseEntity.ok("Connexion réussie !");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Échec de la connexion.");
//        }
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(HttpSession session) {
//        session.invalidate();
//        return ResponseEntity.ok("Logout successful");
//    }

   /* @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Credentials credentials) {
        Credentials authenticatedUser = commandeRepository.checkCredentials(credentials);
        if (authenticatedUser != null) {
            String sessionId = UUID.randomUUID().toString();
            activeSessions.put(sessionId, authenticatedUser);
            return ResponseEntity.ok(sessionId);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Échec de la connexion.");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String username) {
        Map<String, String> response = new HashMap<>();
        if (activeSessions.remove(sessionId) != null) {
            commandeRepository.logoutUser(username);
            activeSessions.remove(sessionId);
            response.put("message", "Déconnexion réussie !");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Échec de la déconnexion.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }*/


    @GetMapping("/listEnCoursBP")
    public ResponseEntity<List<EnCoursPrepBP>> listBPEncours(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) int etat) {
        try {
            List<EnCoursPrepBP> cmd = commandeRepository.listBPEncours(sessionId,etat);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listLigneBP")
    public ResponseEntity<List<ListLigneBP>> listLigneBP(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String BP) {
        try {
            List<ListLigneBP> cmd = commandeRepository.listLigneBP(sessionId,BP);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detailBP")
    public ResponseEntity<List<DetailBP>> detailBP(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String BP) {
        try {
            List<DetailBP> cmd = commandeRepository.detailBP(sessionId,BP);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detailBPCTRL")
    public ResponseEntity<List<DetailBP>> detailBPCTRL(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String BP) {
        try {
            List<DetailBP> cmd = commandeRepository.detailBPCTRL(sessionId,BP);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/infoEncoursBP")
    public ResponseEntity<EnCoursPrepBP> infoEncoursBP(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String BP) {
        try {
            EnCoursPrepBP cmd = commandeRepository.infoEncoursBP(sessionId,BP);

            if (cmd != null) {
                return new ResponseEntity<>(cmd, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/printPP")
    public ResponseEntity<byte[]> generateReport(@RequestHeader("Authorization") String sessionId,@RequestParam String pp) {
        byte[] reportBytes = commandeRepository.generateReport(sessionId,pp);

        if (reportBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "ppA5.pdf");

            return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
