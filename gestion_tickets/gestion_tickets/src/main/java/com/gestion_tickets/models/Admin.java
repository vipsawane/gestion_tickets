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
public class Admin extends User {

    @OneToMany(mappedBy = "admin")
    private List<Formateur> formateurs;

    //@ManyToOne
    //@JoinColumn(name = "role_id")
    //private Role role;

}
