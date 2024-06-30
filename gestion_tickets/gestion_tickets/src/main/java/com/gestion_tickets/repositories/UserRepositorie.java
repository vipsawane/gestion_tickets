package com.gestion_tickets.repositories;

import com.gestion_tickets.models.User;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositorie extends JpaRepository<User, Long> {
    User findByEmail(String email);


}
