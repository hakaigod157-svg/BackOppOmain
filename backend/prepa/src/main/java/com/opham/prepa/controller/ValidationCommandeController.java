package com.opham.prepa.controller;

import com.opham.prepa.model.ValidationCommande.*;
import com.opham.prepa.repository.ValidationCommande.CommandeAlivrerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ValidationCommandeController {

    @Autowired
    private CommandeAlivrerRepository commandealivrerrepository;

    @GetMapping("/bonalivrer")
    public ResponseEntity<List<CommandeAlivrer>> oncommandealivrer(@RequestHeader("Authorization") String sessionId) {
        try {
            List<CommandeAlivrer> cmd = commandealivrerrepository.findCommandeAlivrer(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/maj_bon")
    public ResponseEntity<?> updateFCC2(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String cc,
            @RequestParam int etat) {
        try {
            int rowsAffected =
                    commandealivrerrepository.updateFCC2(sessionId, cc, etat);

            if (rowsAffected > 0) {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Mise à jour FCC2 effectuée avec succès");
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Échec de la mise à jour");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 🔥 récupérer la vraie erreur SQL
            Throwable root = e;

            while (root.getCause() != null) {
                root = root.getCause();
            }
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(root.getMessage());
        }
    }

    @PostMapping("/update_com")
    public ResponseEntity<String> update_comADV(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) String cc,@RequestParam(required = true) String commentaire) {
        try {
            int rowsAffected = commandealivrerrepository.updateCOM_Client(sessionId, cc,commentaire);
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



   /* @GetMapping("/listavalider")
    public ResponseEntity<List<CommandeAValider>> oncommandeavalider(@RequestHeader("Authorization") String sessionId , @RequestParam(required = false) String client) {
        try {
            List<CommandeAValider> cmd = commandealivrerrepository.findCommandeAValider(sessionId ,client);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @GetMapping("/listavalider")
    public ResponseEntity<?> oncommandeavalider(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = false) String client) {

        try {

            List<CommandeAValider> cmd =
                    commandealivrerrepository.findCommandeAValider(sessionId, client);

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


    @GetMapping("/listvalider")
    /*public ResponseEntity<List<CommandeValider>> oncommandevalider(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date datedeb , @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date datefin , @RequestParam(required = false) String codeCC , @RequestParam(required = false) String client )
     */

    public ResponseEntity<List<List<Object>>> CommandeValider(@RequestHeader("Authorization") String sessionId, @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date datedeb , @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date datefin , @RequestParam(required = false) String codeCC , @RequestParam(required = false) String client ) {

        try {
            /*List<CommandeValider> cmd = commandealivrerrepository.findCommandeValider(sessionId,datedeb,datefin, codeCC,client);*/

            List<List<Object>> cmd = commandealivrerrepository.findCommandeValider(sessionId,datedeb,datefin, codeCC,client);

            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/solde")
    public ResponseEntity<List<ClientSolde>> onsoldeclient(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String codeclient ,@RequestParam(required = true) int type) {
        try {
            List<ClientSolde> cmd = commandealivrerrepository.findClientSolde(sessionId,codeclient,type);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/encours")
    public ResponseEntity<List<CommandeEncours>> oncommandeencours(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String codeclient , @RequestParam(required = true) int type) {
        try {
            List<CommandeEncours> cmd = commandealivrerrepository.findCommandeEncours(sessionId,codeclient,type);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/avance")
    public ResponseEntity<List<Avance>> onavance(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String codeclient , @RequestParam(required = true) int type) {
        try {
            List<Avance> cmd = commandealivrerrepository.findAvance(sessionId,codeclient,type);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/traite")
    public ResponseEntity<List<Traite>> ontraite(@RequestHeader("Authorization") String sessionId) {
        try {
            List<Traite> cmd = commandealivrerrepository.findTraite(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/reglement")
    public ResponseEntity<List<Reglement>> onreglement(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String codeclient , @RequestParam(required = true) int type) {
        try {
            List<Reglement> cmd = commandealivrerrepository.findReglement(sessionId,codeclient,type);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reglementBon")
    public ResponseEntity<List<ReglementBon>> onreglementbon(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String codeclient , @RequestParam(required = true) int type) {
        try {
            List<ReglementBon> cmd = commandealivrerrepository.findReglementBon(sessionId,codeclient,type);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/commande")
    public ResponseEntity<List<Commande>> oncommande(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String codeclient , @RequestParam(required = true) int type) {
        try {
            List<Commande> cmd = commandealivrerrepository.findCommande(sessionId,codeclient,type);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/onValideADV")
    public ResponseEntity<?> getCategoryId(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam String cmd,
            @RequestParam(required = false) String accord,
            @RequestParam(required = false) String comAccord,
            @RequestParam(required = false) String commClt,
            @RequestParam(required = false) String comMag,
            @RequestParam String modeNorm,
            @RequestParam(required = false) String modeEncours,
            @RequestParam(required = false) BigDecimal impaye,
            @RequestParam(required = false) BigDecimal retard,
            @RequestParam int bal,
            @RequestParam String ip) {

        try {
            List<TestApresValide> results =
                    commandealivrerrepository.onValideAdv(
                            sessionId,
                            cmd,
                            accord,
                            comAccord,
                            commClt,
                            comMag,
                            modeNorm,
                            modeEncours,
                            impaye,
                            retard,
                            bal,
                            ip
                    );

            if (results == null || results.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("Aucune donnée retournée");
            }
            return ResponseEntity.ok(results);
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


    @GetMapping("onCreeTraiteADV")
    public ResponseEntity<String> onCreeTraiteADV(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String cmd, @RequestParam(required = false) String traite, @RequestParam(required = true)  int isNew) {
        String code = commandealivrerrepository.onCreeTraiteAdv(sessionId, cmd, traite, isNew);
        return new ResponseEntity<>(code, HttpStatus.OK);
    }

    @GetMapping("/onBlockAdv")
    public ResponseEntity<?> onBlockAdv(
            @RequestHeader("Authorization") String sessionId,
            @RequestParam(required = true) String cmd,
            @RequestParam(required = true) String ip,
            @RequestParam int couleur) {
        try {
            List<InfoApresBlocage> result =
                    commandealivrerrepository.onBlockAdv(sessionId, cmd, ip, couleur);
            if (result == null || result.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("Aucune donnée retournée");
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            // 🔥 récupérer la vraie erreur SQL
            Throwable root = e;
            while (root.getCause() != null) {
                root = root.getCause();
            }
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(root.getMessage());
        }
    }

    @GetMapping("/getStade_cmd")
    public int getStade_cmd(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String cmd) {
        return commandealivrerrepository.getStade_cmd(sessionId, cmd);
    }

    @GetMapping("/getStade_bal")
    public int getStade_bal(@RequestHeader("Authorization") String sessionId , @RequestParam(required = true) String cmd) {
        return commandealivrerrepository.getSatde_bal(sessionId, cmd);
    }

    @GetMapping("/etapeAdv")
    public ResponseEntity<Etape> getEtapeAdv(@RequestHeader("Authorization") String sessionId) {
        try {
            Etape cmd = commandealivrerrepository.getEtapeAdv(sessionId);

            if (cmd != null) {
                return new ResponseEntity<>(cmd, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}