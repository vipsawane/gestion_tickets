package com.gestion_tickets.repositories;

import com.gestion_tickets.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepositorie extends JpaRepository<Admin,Integer> {
}
