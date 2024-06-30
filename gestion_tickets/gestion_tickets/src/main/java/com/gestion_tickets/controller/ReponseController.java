package com.gestion_tickets.controller;

import com.gestion_tickets.models.*;
import com.gestion_tickets.repositories.TicketRepositorie;
import com.gestion_tickets.repositories.UserRepositorie;
import com.gestion_tickets.service.MailService;
import com.gestion_tickets.service.ReponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/reponse")
@AllArgsConstructor
@Tag(name = "Gestion de Ticket", description = "API pour la gestion des tickets")
public class ReponseController {

    private final ReponseService reponseService;
    private final MailService mailService;
    private final UserRepositorie userRepositorie;
    private final TicketRepositorie ticketRepositorie;

    // Crée une nouvelle reponse à partir du corps JSON de la requête POST
    @Operation(summary = "Créer une reponse ")
    @PostMapping(value = "/creer", consumes = APPLICATION_JSON_VALUE)
    public Reponse creerReponse(@RequestBody Reponse reponse, Principal principal) {
        String email = principal.getName();
        User formateur = userRepositorie.findByEmail(email);

        if (formateur instanceof Formateur) {
            Reponse createdReponse = reponseService.creerReponse(reponse, (Formateur) formateur);
            Ticket ticket = ticketRepositorie.findById(reponse.getTicket().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
            Apprenant apprenant = ticket.getApprenant();
            mailService.EnvoieNotificationReponseApprenant(ticket, apprenant);
            return createdReponse;
        } else {
            throw new IllegalArgumentException("L'utilisateur authentifié n'est pas un formateur.");
        }
    }

    // Récupère la liste de toutes les reponses via une requête GET
    @Operation(summary = "Afficher la liste des reponses ")
    @GetMapping
    public List<Reponse> lireReponses() {
        return reponseService.lireReponses();
    }

    // Mofifie une reponse existante en utilisant l'ID spécifié dans le chemin de la requête PUT
    @Operation(summary = "Modifier une reponse existante ")
    @PutMapping(value = "/modifier/{id}", consumes = APPLICATION_JSON_VALUE)
    public Reponse modifierReponse(@PathVariable int id, @RequestBody Reponse reponse) {
        return reponseService.modifierReponse(id, reponse);
    }

    // Supprime une reponse existante en utilisant l'ID spécifié dans le chemin de la requête DELETE
    @Operation(summary = "Supprimer une reponse existante ")
    @DeleteMapping(value = "/supprimer/{id}")
    public String supprimerReponse(@PathVariable int id) {
        return reponseService.supprimerReponse(id);
    }
}
