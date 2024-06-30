package com.gestion_tickets.service;

import com.gestion_tickets.models.Apprenant;
import com.gestion_tickets.models.Formateur;
import com.gestion_tickets.models.Role;
import com.gestion_tickets.repositories.ApprenantRepositorie;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApprenantService {

    private final ApprenantRepositorie apprenantRepositorie; // Injection du repository ApprenantRepositorie
    private final PasswordEncoder passwordEncoder; // Injection de PasswordEncoder pour encoder les mots de passe

    // Méthode pour créer un nouvel apprenant
    public Apprenant creerApprenant(Apprenant apprenant, Formateur formateur) {
        apprenant.setFormateur(formateur); // Associe un formateur à l'apprenant
        apprenant.setPassword(passwordEncoder.encode(apprenant.getPassword())); // Encode le mot de passe de l'apprenant
        apprenant.setRole(Role.APPRENANT); // Définit le rôle de l'apprenant
        return apprenantRepositorie.save(apprenant); // Utilise le repository pour sauvegarder l'apprenant en base de données
    }

    // Méthode pour lire tous les apprenants
    public List<Apprenant> lireApprenants() {
        return apprenantRepositorie.findAll(); // Utilise le repository pour récupérer tous les apprenants en base de données
    }

    // Méthode pour modifier un apprenant existant par son ID
    public Apprenant modifierApprenant(int id, Apprenant apprenant) {
        return apprenantRepositorie.findById(id) // Cherche l'apprenant par son ID
                .map(ap -> { // Si trouvé, mappe l'apprenant existant (ap) avec les données du nouvel apprenant (apprenant)
                    ap.setNom(apprenant.getNom());
                    ap.setPrenom(apprenant.getPrenom());
                    ap.setAdresse(apprenant.getAdresse());
                    ap.setEmail(apprenant.getEmail());
                    ap.setTelephone(apprenant.getTelephone());
                    return apprenantRepositorie.save(ap); // Sauvegarde les modifications en base de données
                })
                .orElseThrow(() -> new RuntimeException("Apprenant non trouvé !")); // Lance une exception si l'apprenant n'est pas trouvé
    }

    // Méthode pour supprimer un apprenant par son ID
    public String supprimerApprenant(int id) {
        apprenantRepositorie.deleteById(id); // Utilise le repository pour supprimer l'apprenant par son ID
        return "Apprenant Supprimé !"; // Retourne un message indiquant que l'apprenant a été supprimé
    }
}
