package com.gestion_tickets.models;

import com.gestion_tickets.enume.Categorie;
import com.gestion_tickets.enume.Statut;
import com.gestion_tickets.enume.Type;
import com.gestion_tickets.enume.Urgence;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "TICKET")
@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String description;
    private Date date = new Date();
    private Type type;
    private Categorie categorie;
    private Urgence urgence;
    private Statut status= Statut.valueOf("Ouvert");

    @ManyToOne
    @JoinColumn(name = "apprenant_id", nullable = false)
    private Apprenant apprenant;

    @ManyToOne
    @JoinColumn(name = "formateur_id", nullable = false)
    private Formateur formateur;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private Reponse reponse;


}
