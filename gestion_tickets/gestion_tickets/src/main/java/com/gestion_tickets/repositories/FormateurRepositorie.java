package com.gestion_tickets.repositories;

import com.gestion_tickets.models.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormateurRepositorie extends JpaRepository<Formateur,Integer> {
}
