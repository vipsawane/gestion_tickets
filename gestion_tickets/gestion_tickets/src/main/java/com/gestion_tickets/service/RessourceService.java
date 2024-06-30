package com.gestion_tickets.service;

import com.gestion_tickets.models.Apprenant;
import com.gestion_tickets.models.Formateur;
import com.gestion_tickets.models.Ressource;
import com.gestion_tickets.models.User;
import com.gestion_tickets.repositories.RessourceRepositorie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indique que cette classe est un service Spring
@AllArgsConstructor // Génère un constructeur avec tous les champs injectés par Spring
public class RessourceService {

    private final RessourceRepositorie ressourceRepositorie; // Injection du repository RessourceRepositorie

    // Méthode pour créer une nouvelle ressource
    public Ressource creerRessource(Ressource ressource, User formateur) {
        ressource.setFormateur((Formateur) formateur);
        ressource.getFormateur().setId(ressource.getFormateur().getId());

        return ressourceRepositorie.save(ressource); // Utilise le repository pour sauvegarder la ressource en base de données
    }

    // Méthode pour lire toutes les ressources
    public List<Ressource> lireRessources() {

        return ressourceRepositorie.findAll(); // Utilise le repository pour récupérer toutes les ressources en base de données
    }

    // Méthode pour modifier une ressource existante par son ID
    public Ressource modifierRessource(int id, Ressource ressource) {
        return ressourceRepositorie.findById(id) // Cherche la ressource par son ID
                .map(rs -> { // Si trouvée, mappe la ressource existante (rs) avec les données de la nouvelle ressource (ressource)
                    rs.setTitre(ressource.getTitre());
                    rs.setDescripcion(ressource.getDescripcion());
                    rs.setDate(ressource.getDate());
                    return ressourceRepositorie.save(rs); // Sauvegarde les modifications en base de données
                })
                .orElseThrow(() -> new RuntimeException("Ressource non trouvée !")); // Lance une exception si la ressource n'est pas trouvée
    }

    // Méthode pour supprimer une ressource par son ID
    public String supprimerRessource(int id) {
        ressourceRepositorie.deleteById(id); // Utilise le repository pour supprimer la ressource par son ID
        return "Ressource Supprimée !"; // Retourne un message indiquant que la ressource a été supprimée
    }
}
