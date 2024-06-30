package com.gestion_tickets.service;

import com.gestion_tickets.models.Admin;
import com.gestion_tickets.models.Formateur;
import com.gestion_tickets.models.Role;
import com.gestion_tickets.models.User;
import com.gestion_tickets.repositories.FormateurRepositorie;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FormateurService {

    private final FormateurRepositorie formateurRepositorie;
    private PasswordEncoder passwordEncoder;// Injection du repository FormateurRepositorie

    // Méthode pour créer un nouveau formateur
    public Formateur creerFormateur(Formateur formateur, User admin) {
        formateur.setAdmin((Admin) admin);
        formateur.setPassword(passwordEncoder.encode(formateur.getPassword()));
        formateur.setRole(Role.FORMATEUR);
        return formateurRepositorie.save(formateur); // Utilise le repository pour sauvegarder le formateur en base de données
    }

    // Méthode pour lire tous les formateurs
    public List<Formateur> lireFormateurs() {
        return formateurRepositorie.findAll(); // Utilise le repository pour récupérer tous les formateurs en base de données
    }

    // Méthode pour modifier un formateur existant par son ID
    public Formateur modifierFormateur(int id, Formateur formateur) {
        return formateurRepositorie.findById(id) // Cherche le formateur par son ID
                .map(f -> { // Si trouvé, mappe le formateur existant (f) avec les données du nouveau formateur (formateur)
                    f.setNom(formateur.getNom());
                    f.setPrenom(formateur.getPrenom());
                    f.setAdresse(formateur.getAdresse());
                    f.setEmail(formateur.getEmail());
                    f.setTelephone(formateur.getTelephone());
                    return formateurRepositorie.save(f); // Sauvegarde les modifications en base de données
                })
                .orElseThrow(() -> new RuntimeException("Formateur non trouvé !")); // Lance une exception si le formateur n'est pas trouvé
    }

    // Méthode pour supprimer un formateur par son ID
    public String supprimerFormateur(int id) {
        formateurRepositorie.deleteById(id); // Utilise le repository pour supprimer le formateur par son ID
        return "Formateur Supprimé !"; // Retourne un message indiquant que le formateur a été supprimé
    }
}
