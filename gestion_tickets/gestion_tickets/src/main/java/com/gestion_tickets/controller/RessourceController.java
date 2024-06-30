package com.gestion_tickets.controller;

import com.gestion_tickets.models.Reponse;
import com.gestion_tickets.models.Ressource;
import com.gestion_tickets.models.User;
import com.gestion_tickets.repositories.UserRepositorie;
import com.gestion_tickets.service.RessourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/ressource") // Mapping de l'URL de base pour ce contrôleur
@AllArgsConstructor // Injecte automatiquement le service RessourceService via le constructeur
@Tag(name = "Gestion de Ticket", description = "API pour la gestion des tickets")
public class RessourceController {

    private final RessourceService ressourceService; // Service responsable de la logique métier des ressources
    private final UserRepositorie userRepositorie;

    // Crée une nouvelle ressource à partir du corps JSON de la requête POST
    @Operation(summary = "Créer une nouvelle ressource ")
    @PostMapping(value = "/creer", consumes = APPLICATION_JSON_VALUE)
    public Ressource creerRessource(@RequestBody Ressource ressource, Principal principal) {
        String email = principal.getName();
        User formateur = userRepositorie.findByEmail(email);
        return ressourceService.creerRessource(ressource, formateur);
    }

    // Récupère la liste de toutes les ressources via une requête GET
    @Operation(summary = "Afficher la liste des ressources ")
    @GetMapping
    public List<Ressource> lireRessources() {
        return ressourceService.lireRessources();
    }

    // Modifie une ressource existante en utilisant l'ID spécifié dans le chemin de la requête PUT
    @Operation(summary = "Modifier une ressource")
    @PutMapping(value = "/modifier/{id}", consumes = APPLICATION_JSON_VALUE)
    public Ressource modifierRessource(@PathVariable int id, @RequestBody Ressource ressource) {
            return ressourceService.modifierRessource(id, ressource);
    }

    // Supprime une ressource existante en utilisant l'ID spécifié dans le chemin de la requête DELETE
    @Operation(summary = "Supprimer une ressource")
    @DeleteMapping(value = "/supprimer/{id}")
    public String supprimerRessource(@PathVariable int id) {
        return ressourceService.supprimerRessource(id);
    }
}
