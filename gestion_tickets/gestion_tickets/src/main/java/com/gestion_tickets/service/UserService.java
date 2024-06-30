package com.gestion_tickets.service;

import com.gestion_tickets.models.User;
import com.gestion_tickets.repositories.UserRepositorie;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepositorie userRepositorie; // Injection du repository UserRepositorie
    private PasswordEncoder passwordEncoder; // Injection de PasswordEncoder pour encoder les mots de passe

    // Méthode pour créer un nouvel utilisateur
    public User creerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode le mot de passe de l'utilisateur
        return userRepositorie.save(user); // Utilise le repository pour sauvegarder l'utilisateur en base de données
    }

    // Méthode pour lire tous les utilisateurs
    public List<User> lireUsers() {
        return userRepositorie.findAll(); // Utilise le repository pour récupérer tous les utilisateurs en base de données
    }

    // Méthode pour modifier un utilisateur existant par son ID
    public User modifierUser(Long id, User user) {
        return userRepositorie.findById(id) // Cherche l'utilisateur par son ID
                .map(u -> { // Si trouvé, mappe l'utilisateur existant (u) avec les données du nouvel utilisateur (user)
                    u.setNom(user.getNom());
                    u.setPrenom(user.getPrenom());
                    u.setAdresse(user.getAdresse());
                    u.setEmail(user.getEmail());
                    u.setTelephone(user.getTelephone());
                    return userRepositorie.save(u); // Sauvegarde les modifications en base de données
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé !")); // Lance une exception si l'utilisateur n'est pas trouvé
    }

    // Méthode pour supprimer un utilisateur par son ID
    public String supprimerUser(Long id) {
        userRepositorie.deleteById(id); // Utilise le repository pour supprimer l'utilisateur par son ID
        return "Utilisateur Supprimé !"; // Retourne un message indiquant que l'utilisateur a été supprimé
    }
}
