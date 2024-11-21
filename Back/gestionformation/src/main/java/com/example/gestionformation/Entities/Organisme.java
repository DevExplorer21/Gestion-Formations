package com.example.gestionformation.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Organisme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    @Size(min = 5,max = 25)
    private String libelle;

    // association avec la table formateur
    @OneToMany(mappedBy="organisme",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Formateur> formateurs;

    // association avec la table session
    @OneToMany(mappedBy="organisme",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Session> sessions;

    //les getters
    public Long getId() { return id; }
    public String getLibelle() { return libelle; }
    public Set<Formateur> getFormateurs() { return formateurs; }
    public Set<Session> getSessions() { return sessions; }

    //les setters
    public void setId(Long id) { this.id = id; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public void setFormateurs(Set<Formateur> formateurs) { this.formateurs = formateurs; }
    public void setSessions(Set<Session> sessions) { this.sessions = sessions; }

    //les constructeurs

    public Organisme() { }

    public Organisme(Long id, String libelle, Set<Formateur> formateurs, Set<Session> sessions) {
        this.id = id;
        this.libelle = libelle;
        this.formateurs = formateurs;
        this.sessions = sessions;
    }

    public Organisme(String libelle, Set<Formateur> formateurs, Set<Session> sessions) {
        this.libelle = libelle;
        this.formateurs = formateurs;
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "Organisme{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", formateurs=" + formateurs +
                ", sessions=" + sessions +
                '}';
    }
}
