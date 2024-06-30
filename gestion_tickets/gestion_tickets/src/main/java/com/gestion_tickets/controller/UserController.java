package com.gestion_tickets.controller;

import com.gestion_tickets.models.User;
import com.gestion_tickets.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/users") // Mapping de l'URL de base pour ce contrôleur
@AllArgsConstructor // Injecte automatiquement le service UserService via le constructeur
@Tag(name = "Gestion de Ticket", description = "API pour la gestion des tickets")
public class UserController {

    private final UserService userService; // Service responsable de la logique métier des utilisateurs

    // Crée un nouvel utilisateur à partir du corps JSON de la requête POST
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public User creerUser(@RequestBody User user) {
        return userService.creerUser(user);
    }

    // Récupère la liste de tous les utilisateurs via une requête GET
    @GetMapping
    public List<User> lireUsers() {
        return userService.lireUsers();
    }

    // Modifie un utilisateur existant en utilisant l'ID spécifié dans le chemin de la requête PUT
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public User modifierUser(@PathVariable Long id, @RequestBody User user) {
        return userService.modifierUser(id, user);
    }

    // Supprime un utilisateur existant en utilisant l'ID spécifié dans le chemin de la requête DELETE
    @DeleteMapping("/{id}")
    public String supprimerUser(@PathVariable Long id) {
        return userService.supprimerUser(id);
    }
}
