package com.example.gestionformation.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table
public class Domaine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    private String libelle;

    // association avec la table formation
    @OneToMany(mappedBy="domain")
    @JsonIgnore
    private Set<Formation> formations;

    //les getters et setters
    public Long getId() { return id; }
    public String getLibelle() { return libelle; }
    public void setId(Long id) { this.id = id; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public Set<Formation> getFormations() { return formations; }
    public void setFormations(Set<Formation> formations) {
        this.formations = formations;
    }

    //constructeurs
    public Domaine() { }
    public Domaine(String libelle) {
        this.libelle = libelle;
    }
    public Domaine(Long id, String libelle, Set<Formation> formations) {
        this.id = id;
        this.libelle = libelle;
        this.formations = formations;
    }
    @Override
    public String toString() {
        return "Domaine{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", formations=" + formations +
                '}';
    }
}
