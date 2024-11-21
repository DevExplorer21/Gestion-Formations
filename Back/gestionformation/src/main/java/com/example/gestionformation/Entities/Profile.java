package com.example.gestionformation.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;

    @OneToMany(mappedBy="profil")
    @JsonIgnore
    private Set<Participant> participants;

    //les getters
    public Long getId() { return id; }
    public String getLibelle() { return libelle; }
    public Set<Participant> getParticipants() { return participants; }

    //les setters
    public void setId(Long id) { this.id = id; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public void setParticipants(Set<Participant> participants) { this.participants = participants; }

    //les constructeurs
    public Profile(){}

    public Profile(String libelle) { this.libelle = libelle; }

    public Profile(Long id, String libelle, Set<Participant> participants) {
        this.id = id;
        this.libelle = libelle;
        this.participants = participants;
    }
}
