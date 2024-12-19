package com.Auth_service.Service;

import com.Auth_service.Entity.User;
import com.Auth_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Enregistre un nouvel utilisateur après cryptage du mot de passe
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Recherche un utilisateur par son nom d'utilisateur
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Recherche un utilisateur par son email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Vérifie si un mot de passe brut correspond au mot de passe crypté
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
