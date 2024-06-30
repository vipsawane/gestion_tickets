package com.gestion_tickets.service;

import com.gestion_tickets.models.Apprenant;
import com.gestion_tickets.models.Formateur;
import com.gestion_tickets.models.Ticket;
import com.gestion_tickets.repositories.TicketRepositorie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepositorie ticketRepositorie; // Injection du repository TicketRepositorie

    // Méthode pour créer un nouveau ticket
    public Ticket creerTicket(Ticket ticket, Apprenant apprenant) {
        ticket.setApprenant(apprenant); // Associe un apprenant au ticket
        Formateur formateur = apprenant.getFormateur(); // Récupère le formateur de l'apprenant
        ticket.setFormateur(formateur); // Associe le formateur au ticket
        return ticketRepositorie.save(ticket); // Utilise le repository pour sauvegarder le ticket en base de données
    }

    // Méthode pour lire tous les tickets
    public List<Ticket> lireTickets() {
        return ticketRepositorie.findAll(); // Utilise le repository pour récupérer tous les tickets en base de données
    }

    // Méthode pour modifier un ticket existant par son ID
    public Ticket modifierTicket(int id, Ticket ticket) {
        return ticketRepositorie.findById(id) // Cherche le ticket par son ID
                .map(t -> { // Si trouvé, mappe le ticket existant (t) avec les données du nouveau ticket (ticket)
                    t.setTitre(ticket.getTitre());
                    t.setDescription(ticket.getDescription());
                    t.setDate(ticket.getDate());
                    t.setType(ticket.getType());
                    t.setCategorie(ticket.getCategorie());
                    t.setUrgence(ticket.getUrgence());
                    t.setStatus(ticket.getStatus());
                    return ticketRepositorie.save(t); // Sauvegarde les modifications en base de données
                })
                .orElseThrow(() -> new RuntimeException("Ticket non trouvé !")); // Lance une exception si le ticket n'est pas trouvé
    }

    // Méthode pour supprimer un ticket par son ID
    public String supprimerTicket(int id) {
        ticketRepositorie.deleteById(id); // Utilise le repository pour supprimer le ticket par son ID
        return "Ticket Supprimé !"; // Retourne un message indiquant que le ticket a été supprimé
    }
}
