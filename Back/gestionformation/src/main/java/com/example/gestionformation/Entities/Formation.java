package com.example.gestionformation.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    @Size(min = 5, max=25)
    private String titre;
    @Enumerated(EnumType.STRING)
    private EnumFormation type;
    @NotNull
    @Column(length=4)
    private Integer année;
    @NotNull
    private Integer nb_session;
    @NotNull
    private Integer duree;
    @NotNull
    private Float budget;

    //association avec la classe Domaine
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="id_domaine")
    private Domaine domain;

    // association avec la classe Session et création d'une table d'association
   /* @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Session_Formation",joinColumns = {
            @JoinColumn(name = "id_Formation") }, inverseJoinColumns = {
            @JoinColumn(name = "id_Session") })
    private Set<Session> sessions;*/
    // association avec la table session
    @OneToMany(mappedBy="formation")
    @JsonIgnore
    private Set<Session> sessions;

    // les getters
    public Long getId() { return id; }
    public String getTitre() { return titre; }
    public EnumFormation getType() { return type; }
    public Integer getAnnée() { return année; }
    public Integer getNb_session() { return nb_session; }
    public Integer getDuree() { return duree; }
    public Float getBudget() { return budget; }
    public Domaine getDomain() { return domain; }
    public Set<Session> getSessions() { return sessions; }

    // les setters
    public void setId(Long id) { this.id = id; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setType(EnumFormation type) { this.type = type; }
    public void setAnnée(Integer année) { this.année = année; }
    public void setNb_session(Integer nb_session) { this.nb_session = nb_session; }
    public void setDuree(Integer duree) { this.duree = duree; }
    public void setBudget(Float budget) { this.budget = budget; }
    public void setDomain(Domaine domain) { this.domain = domain; }
    public void setSessions(Set<Session> sessions) { this.sessions = sessions; }

    //les constructeurs

    public Formation() {  }
    public Formation(Long id, String titre, EnumFormation type, Integer année, Integer nb_session, Integer duree, Float budget, Domaine domain, Set<Session> sessions) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.année = année;
        this.nb_session = nb_session;
        this.duree = duree;
        this.budget = budget;
        this.domain = domain;
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", type='" + type + '\'' +
                ", année=" + année +
                ", duree=" + duree +
                ", budget=" + budget +
                ", sessions=" + sessions +
                ", domain=" + domain +
                '}';
    }
}
