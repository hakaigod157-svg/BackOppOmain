package com.opham.prepa.controller;

import com.opham.prepa.model.Admins.Acces;
import com.opham.prepa.model.Admins.Groupe;
import com.opham.prepa.model.Admins.Users;
import com.opham.prepa.model.Transfert.Transfert;
import com.opham.prepa.model.Utils.Droit;
import com.opham.prepa.repository.Admins.AdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminsControlleur {
    @Autowired
    AdminsRepository adminsRepository;

    @GetMapping("/listGroupe")
    public ResponseEntity<List<Groupe>> listGroupe(@RequestHeader("Authorization") String sessionId) {
        try {
            List<Groupe> cmd = adminsRepository.listGroupe(sessionId);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listUsers")
    public ResponseEntity<List<Users>> listUsers(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String groupe) {
        try {
            List<Users> cmd = adminsRepository.listUsers(sessionId,groupe);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listMembres")
    public ResponseEntity<List<Users>> listMembres(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String groupe) {
        try {
            List<Users> cmd = adminsRepository.listMembres(sessionId,groupe);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listAcces")
    public ResponseEntity<List<Acces>> listAcces(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String groupe) {
        try {
            List<Acces> cmd = adminsRepository.listAcces(sessionId,groupe);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
                e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sesDroits")
    public ResponseEntity<List<Acces>> listDroit(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String groupe) {
        try {
            List<Acces> cmd = adminsRepository.listDroit(sessionId,groupe);
            if (cmd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/insert_Membres")
    public ResponseEntity<String> insert_Membres(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String groupe,@RequestParam(required = true) String users) {
        int updatedRows = adminsRepository.insert_Membres(sessionId,groupe,users);
        if (updatedRows > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete_Membres")
    public ResponseEntity<String> delete_Membres(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String groupe,@RequestParam(required = true) String users) {
        int updatedRows = adminsRepository.delete_Membres(sessionId,groupe,users);
        if (updatedRows > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete_Droit")
    public ResponseEntity<String> delete_droit(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) String groupe,@RequestParam(required = true) String droit) {
        int updatedRows = adminsRepository.delete_droit(sessionId,groupe,droit);
        if (updatedRows > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/insert_droit")
    public ResponseEntity<Acces> insert_droit(@RequestHeader("Authorization") String sessionId,@RequestBody Acces t) {
        int updatedRows = adminsRepository.insert_droit(sessionId,t);
        if (updatedRows > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping ("/update_Droit")
    public ResponseEntity<String> update_Droit(@RequestHeader("Authorization") String sessionId,@RequestParam(required = true) boolean read,@RequestParam(required = true) boolean write,@RequestParam(required = true) String groupe,@RequestParam(required = true) String droit) {
        int updatedRows = adminsRepository.update_droit(sessionId,groupe,droit,read,write);
        if (updatedRows > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/insert_groupe")
    public ResponseEntity<Groupe> insert_groupe(@RequestHeader("Authorization") String sessionId,@RequestBody Groupe G) {
        int updatedRows = adminsRepository.insert_groupe(sessionId,G);
        if (updatedRows > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
