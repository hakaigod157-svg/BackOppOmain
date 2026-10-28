package com.opham.prepa.controller;

import com.opham.prepa.model.Utils.Credentials;
import com.opham.prepa.service.CredentialsCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.130.64:3000", "http://192.168.201.13:3000","http://192.168.130.69:8081","http://192.168.201.212:3000"}, methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH})
//@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH})
@RequestMapping("/api")
public class AuthController {

    private final CredentialsCheckService credentialsCheckService;

    @Autowired
    public AuthController(CredentialsCheckService credentialsCheckService) {
        this.credentialsCheckService = credentialsCheckService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Credentials credentials) throws SQLException {
        Credentials validatedCredentials = credentialsCheckService.checkCredentials(credentials);
        if (validatedCredentials != null) {
            // Générez un identifiant de session unique
            String sessionId = UUID.randomUUID().toString();
            // Liez le sessionId aux informations d'identification dans le service
            credentialsCheckService.setSessionId(validatedCredentials, sessionId);
            return ResponseEntity.ok(sessionId); // Retournez le sessionId
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String sessionId,String username, String password) {
        credentialsCheckService.logoutUser(sessionId,username,password);
        return ResponseEntity.ok().build();
    }

}
