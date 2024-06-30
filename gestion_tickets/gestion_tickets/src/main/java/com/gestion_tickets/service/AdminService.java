package com.gestion_tickets.service;

import com.gestion_tickets.models.Admin;
import com.gestion_tickets.repositories.AdminRepositorie;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

// Indique à Spring que cette classe est un service
@Service
// Génère automatiquement un constructeur avec tous les champs
@AllArgsConstructor
public class AdminService {

    // Injection de dépendance du repository AdminRepositorie
    private final AdminRepositorie adminRepositorie;
    private final PasswordEncoder passwordEncoder;
    // Méthode pour créer un nouvel admin
    public Admin creerAdmin(Admin admin) {

        String pass = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(pass);
        // Utilise le repository pour sauvegarder l'admin en base de données
        passwordEncoder.encode(admin.getPassword());
        return adminRepositorie.save(admin);
    }

    // Méthode pour lire tous les admins
    public List<Admin> lireAdmin() {
        // Utilise le repository pour récupérer tous les admins en base de données
        return adminRepositorie.findAll();
    }

    // Méthode pour modifier un admin existant par son ID
    public Admin modifierAdmin(int id, Admin admin) {
        // Cherche l'admin par son ID
        return adminRepositorie.findById(id)
                // Si trouvé, mappe l'admin existant (ad) avec les données du nouvel admin (admin)
                .map(ad -> {
                    ad.setNom(admin.getNom());
                    ad.setPrenom(admin.getPrenom());
                    ad.setAdresse(admin.getAdresse());
                    ad.setEmail(admin.getEmail());
                    ad.setTelephone(admin.getTelephone());
                    // Sauvegarde les modifications en base de données
                    return adminRepositorie.save(ad);
                })
                // Lance une exception si l'admin n'est pas trouvé
                .orElseThrow(() -> new RuntimeException("Admin non trouvé !"));
    }

    // Méthode pour supprimer un admin par son ID
    public String supprimerAdmin(int id) {
        // Utilise le repository pour supprimer l'admin par son ID
        adminRepositorie.deleteById(id);
        // Retourne un message indiquant que l'admin a été supprimé
        return "Admin Supprimé !";
    }
}
