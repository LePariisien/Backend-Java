package com.Auth_service.Auth_service.Controller;

import com.Auth_service.Auth_service.Entity.User;
import com.Auth_service.Auth_service.Service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;


    
    private final String SECRET_KEY = "mySecretKey";

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User loginRequest) {
        Optional<User> user = userService.findByUsername(loginRequest.getUsername());
        if (user.isPresent() && userService.checkPassword(loginRequest.getPassword(), user.get().getPassword())) {
            // Génération du token JWT
            String token = Jwts.builder()
                    .setSubject(user.get().getUsername())
                    .claim("role", user.get().getRole()) // Ajoute des informations supplémentaires (ex. rôle)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Valable 10 heures
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();

            // Retourne le token dans la réponse
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("username", user.get().getUsername());
            response.put("role", user.get().getRole());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(null); // Identifiants invalides
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
    

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Vérifie si l'email ou le nom d'utilisateur existe déjà
        Optional<User> existingUser = userService.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<User> existingEmail = userService.findByEmail(user.getEmail());
        if (existingEmail.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/users")
    public ResponseEntity<String > getUsers() {
        return ResponseEntity.ok("Liste des utilisateurs");
    }
}
