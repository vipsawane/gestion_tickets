package com.gestion_tickets.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "RESSOURCE")
@Getter
@Setter
@NoArgsConstructor
public class Ressource  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String descripcion;
    private Date date;

    @PrePersist
    protected void onCreate() {
        date = new Date();
    }

    @ManyToOne
    @JoinColumn(name = "formateur_id", nullable = false)
    private Formateur formateur;


}

