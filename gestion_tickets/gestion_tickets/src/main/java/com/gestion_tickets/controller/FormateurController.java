package com.gestion_tickets.controller;

import com.gestion_tickets.models.Formateur;
import com.gestion_tickets.models.User;
import com.gestion_tickets.repositories.UserRepositorie;
import com.gestion_tickets.service.FormateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/formateur") // Mapping de l'URL de base pour ce contrôleur
@AllArgsConstructor // Injecte automatiquement le service FormateurService via le constructeur
@Tag(name = "Gestion de Ticket", description = "API pour la gestion des tickets")
public class FormateurController {

    private final FormateurService formateurService; // Service responsable de la logique métier des formateurs
    private UserRepositorie userRepositorie;

    // Crée un nouveau formateur à partir du corps JSON de la requête POST
    @Operation(summary = "Creer un nouveau formateur")
    @PostMapping(value = "/creer", consumes = APPLICATION_JSON_VALUE)
    public Formateur creerFormateur(@RequestBody Formateur formateur, Principal principal) {
        String email = principal.getName();
        User admin = userRepositorie.findByEmail(email);
        return formateurService.creerFormateur(formateur,admin);
    }

    // Récupère la liste de tous les formateurs via une requête GET
    @Operation(summary = "Afficher la liste des formateurs ")
    @GetMapping
    public List<Formateur> lireFormateurs() {
        return formateurService.lireFormateurs();
    }

    // Modifie un formateur existant en utilisant l'ID spécifié dans le chemin de la requête PUT
    @Operation(summary = "Modifier un formateur existant")
    @PutMapping(value = "/modifier/{id}", consumes = APPLICATION_JSON_VALUE)
    public Formateur modifierFormateur(@PathVariable int id, @RequestBody Formateur formateur) {
        return formateurService.modifierFormateur(id, formateur);
    }

    // Supprime un formateur existant en utilisant l'ID spécifié dans le chemin de la requête DELETE
    @Operation(summary = "Supprimer un formateur existant")
    @DeleteMapping(value = "/supprimer/{id}")
    public String supprimerFormateur(@PathVariable int id) {
        return formateurService.supprimerFormateur(id);
    }
}
