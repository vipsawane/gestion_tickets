package com.gestion_tickets.controller;

import com.gestion_tickets.models.Apprenant;
import com.gestion_tickets.models.Formateur;
import com.gestion_tickets.models.User;
import com.gestion_tickets.repositories.UserRepositorie;
import com.gestion_tickets.service.ApprenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/apprenant")
@AllArgsConstructor
@Tag(name = "Gestion de Ticket", description = "API pour la gestion des tickets")
public class ApprenantController {

    private final ApprenantService apprenantService;
    private final UserRepositorie userRepositorie;

    // Crée un nouvel apprenant à partir du corps JSON de la requête POST
    @Operation(summary = "Creer un nouveau apprenant")
    @PostMapping(value = "/creer", consumes = APPLICATION_JSON_VALUE)
    public Apprenant creerApprenant(@RequestBody Apprenant apprenant, Principal principal) {
        String email = principal.getName();
        User user = userRepositorie.findByEmail(email);

        if (user instanceof Formateur) {
            return apprenantService.creerApprenant(apprenant, (Formateur) user);
        } else {
            throw new RuntimeException("User is not a Formateur");
        }
    }

    // Récupère la liste de tous les admins via une requête GET
    @Operation(summary = "Afficher la liste des apprenant ")
    @GetMapping
    public List<Apprenant> lireApprenants() {
        return apprenantService.lireApprenants();
    }

    // Modifie un admin existant en utilisant l'ID spécifié dans le chemin de la requête PUT
    @Operation(summary = "Modifier un apprenant existant")
    @PutMapping(value = "/modifier/{id}", consumes = APPLICATION_JSON_VALUE)
    public Apprenant modifierApprenant(@PathVariable int id, @RequestBody Apprenant apprenant) {
        return apprenantService.modifierApprenant(id, apprenant);
    }

    // Supprime un admin existant en utilisant l'ID spécifié dans le chemin de la requête DELETE
    @Operation(summary = "Supprimer un apprenant existant")
    @DeleteMapping(value = "/supprimer/{id}")
    public String supprimerApprenant(@PathVariable int id) {
        return apprenantService.supprimerApprenant(id);
    }
}
