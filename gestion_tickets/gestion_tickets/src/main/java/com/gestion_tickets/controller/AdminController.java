package com.gestion_tickets.controller;

import com.gestion_tickets.models.Admin;
import com.gestion_tickets.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/admin") // Mapping de l'URL de base pour ce contrôleur
@AllArgsConstructor // Injecte automatiquement le service AdminService via le constructeur
@Tag(name = "Gestion de Ticket", description = "API pour la gestion des tickets")
public class AdminController {

    private final AdminService adminService; // Service responsable de la logique métier des admins

    // Crée un nouvel admin à partir du corps JSON de la requête POST
    @Operation(summary = "Creer un nouveau admin")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/creer", consumes = APPLICATION_JSON_VALUE)
    public Admin creerAdmin(@RequestBody Admin admin){
        return adminService.creerAdmin(admin);
    }

    // Récupère la liste de tous les admins via une requête GET
    @Operation(summary = "Recuperer la liste des admin")
    @GetMapping("/")
    public List<Admin> lireAdmin(){
        return adminService.lireAdmin();
    }

    // Modifie un admin existant en utilisant l'ID spécifié dans le chemin de la requête PUT
    @Operation(summary = "Modifier un admin")
    @PutMapping(value = "/modifier/{id}", consumes = APPLICATION_JSON_VALUE)
    public Admin modifierAdmin(@PathVariable int id, @RequestBody Admin admin){
        return adminService.modifierAdmin(id, admin);
    }

    // Supprime un admin existant en utilisant l'ID spécifié dans le chemin de la requête DELETE
    @Operation(summary = "Supprimer un admin")
    @DeleteMapping(value = "/supprimer/{id}")
    public String supprimerApprenant(@PathVariable int id){
        return adminService.supprimerAdmin(id);
    }
}
