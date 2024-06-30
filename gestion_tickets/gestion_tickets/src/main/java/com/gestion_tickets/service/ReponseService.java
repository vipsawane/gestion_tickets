package com.gestion_tickets.service;

import com.gestion_tickets.enume.Statut;
import com.gestion_tickets.models.Admin;
import com.gestion_tickets.models.Formateur;
import com.gestion_tickets.models.Reponse;
import com.gestion_tickets.models.User;
import com.gestion_tickets.repositories.ReponseRepositorie;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.valueOf;

@Service // Indique que cette classe est un service Spring
@AllArgsConstructor // Génère un constructeur avec tous les champs injectés par Spring
public class ReponseService {

    private final ReponseRepositorie reponseRepositorie; // Injection du repository ReponseRepositorie

    // Méthode pour créer une nouvelle réponse
    public Reponse creerReponse(Reponse reponse, User formateur) {
        reponse.setFormateur((Formateur) formateur);
        reponse.getTicket().setTitre(reponse.getTitre());
        reponse.getTicket().setDescription(reponse.getDescription());
        reponse.getTicket().setStatus(Statut.Termine);
        return reponseRepositorie.save(reponse); // Utilise le repository pour sauvegarder la réponse en base de données
    }

    // Méthode pour lire toutes les réponses
    public List<Reponse> lireReponses() {
        return reponseRepositorie.findAll(); // Utilise le repository pour récupérer toutes les réponses en base de données
    }

    // Méthode pour modifier une réponse existante par son ID
    public Reponse modifierReponse(int id, Reponse reponse) {
        return reponseRepositorie.findById(id) // Cherche la réponse par son ID
                .map(rp -> { // Si trouvée, mappe la réponse existante (rp) avec les données de la nouvelle réponse (reponse)
                    rp.setTitre(reponse.getTitre());
                    rp.setDescription(reponse.getDescription());
                    rp.setDate(reponse.getDate());
                    return reponseRepositorie.save(rp); // Sauvegarde les modifications en base de données
                })
                .orElseThrow(() -> new RuntimeException("Réponse non trouvée !")); // Lance une exception si la réponse n'est pas trouvée
    }

    // Méthode pour supprimer une réponse par son ID
    public String supprimerReponse(int id) {
        reponseRepositorie.deleteById(id); // Utilise le repository pour supprimer la réponse par son ID
        return "Réponse Supprimée !"; // Retourne un message indiquant que la réponse a été supprimée
    }


}
