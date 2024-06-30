package com.gestion_tickets.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Formateur extends User {

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @OneToMany(mappedBy = "formateur")
    private List<Apprenant> apprenants;

    @OneToMany(mappedBy = "formateur")
    private List<Reponse> reponses;

    @OneToMany(mappedBy = "formateur")
    private List<Ressource> ressources;

    @OneToMany(mappedBy = "formateur")
    private List<Ticket> tickets;
}
