package com.example.gestionformation.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    private String nom;
    @OneToMany(mappedBy="pays")
    @JsonIgnore
    private Set<Participant> participants;

    //les getters
    public Long getId() { return id; }
    public String getNom() { return nom; }
    public Set<Participant> getParticipants() { return participants; }

    //les setters
    public void setId(Long id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setParticipants(Set<Participant> participants) { this.participants = participants; }

    //les constructeurs

    public Pays(){}

    public Pays(Long id, String nom, Set<Participant> participants) {
        this.id = id;
        this.nom = nom;
        this.participants = participants;
    }
}
