package com.gestion_tickets.controller;

import com.gestion_tickets.models.Apprenant;
import com.gestion_tickets.models.Formateur;
import com.gestion_tickets.models.Ticket;
import com.gestion_tickets.models.User;
import com.gestion_tickets.repositories.UserRepositorie;
import com.gestion_tickets.service.MailService;
import com.gestion_tickets.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
@Tag(name = "Gestion de Ticket", description = "API pour la gestion des tickets")
public class TicketController {

    private final TicketService ticketService;
    private final MailService mailService;
    private final UserRepositorie userRepositorie;

    // Crée une nouveau ticket à partir du corps JSON de la requête POST
    @Operation(summary = "Creer un nouveau ticket")
    @PostMapping(value = "/creer", consumes = APPLICATION_JSON_VALUE)
    public Ticket creerTicket(@RequestBody Ticket ticket, Principal principal) {
        String email = principal.getName();
        User user = userRepositorie.findByEmail(email);

        if (user instanceof Apprenant) {
            Apprenant apprenant = (Apprenant) user;
            Ticket createdTicket = ticketService.creerTicket(ticket, apprenant);
            mailService.EnvoieNotificationTicketFormateur(createdTicket, apprenant.getFormateur());
            return createdTicket;
        } else {
            throw new IllegalArgumentException("L'utilisateur authentifié n'est pas un apprenant.");
        }
    }

    // Récupère la liste de tous les tickets via une requête GET
    @Operation(summary = "Afficher tous les tickets")
    @GetMapping
    public List<Ticket> lireTickets() {
        return ticketService.lireTickets();
    }

    // Supprime un ticket existant en utilisant l'ID spécifié dans le chemin de la requête DELETE
    @Operation(summary = "Modifier un ticket")
    @PutMapping(value = "/modifier/{id}", consumes = APPLICATION_JSON_VALUE)
    public Ticket modifierTicket(@PathVariable int id, @RequestBody Ticket ticket) {
        return ticketService.modifierTicket(id, ticket);
    }

    // Supprime un ticket existant en utilisant l'ID spécifié dans le chemin de la requête DELETE
    @Operation(summary = "Supprimer un ticket")
    @DeleteMapping(value = "/supprimer/{id}")
    public String supprimerTicket(@PathVariable int id) {
        return ticketService.supprimerTicket(id);
    }
}
