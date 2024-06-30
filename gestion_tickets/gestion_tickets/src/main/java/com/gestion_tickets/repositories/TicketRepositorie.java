package com.gestion_tickets.repositories;

import com.gestion_tickets.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepositorie extends JpaRepository<Ticket, Integer> {
}
