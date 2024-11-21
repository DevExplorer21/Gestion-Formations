package com.example.gestionformation.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
public class Session {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 25)
    private String lieu;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    @NotNull
    private Integer nbrParticipants;


    //association avec la classe Organisme
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "organisme_id")
    Organisme organisme;

    //association avec la classe Formateur
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "formateur_id")
    Formateur formateur;

    //association avec la classe Formation
    // hedi twali ManyToOne
    /*@ManyToMany(mappedBy = "sessions", cascade = CascadeType.REMOVE)
    private Set<Formation> formations;*/
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="id_formation")
    private Formation formation;

    //association avec la classe participants
    @ManyToMany(mappedBy = "sessions", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Participant> participants ;

    //les constructeurs
    public Session() {
    }

    public Session(Long id, String lieu, LocalDate dateDebut, LocalDate dateFin, Integer nbrParticipants, Organisme organisme, Formateur formateur, Formation formation, Set<Participant> participants) {
        this.id = id;
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrParticipants = nbrParticipants;
        this.organisme = organisme;
        this.formateur = formateur;
        this.formation = formation;
        this.participants = participants;
    }

    public Session(String lieu, LocalDate dateDebut, LocalDate dateFin, Integer nbrParticipants, Organisme organisme, Formateur formateur, Formation formation, Set<Participant> participants) {
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrParticipants = nbrParticipants;
        this.organisme = organisme;
        this.formateur = formateur;
        this.formation = formation;
        this.participants = participants;
    }

    //les getters
    public Long getId() {
        return id;
    }
    public String getLieu() {
        return lieu;
    }
    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public LocalDate getDateFin() {
        return dateFin;
    }
    public Organisme getOrganisme() {
        return organisme;
    }
    public Formateur getFormateur() {
        return formateur;
    }
    public Formation getFormations() { return formation; }
    public Set<Participant> getParticipants() {
        return participants;
    }
    public Integer getNbrParticipants() {
        return nbrParticipants;
    }

    //les setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    public void setDateDebut(LocalDate dateDeb) {
        this.dateDebut = dateDeb;
    }
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    public void setOrganisme(Organisme organisme) {
        this.organisme = organisme;
    }
    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }
    public void setFormations(Formation formation) {
        this.formation = formation;
    }
    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }
    public void setNbrParticipants(Integer nbrParticipants) {
        this.nbrParticipants = nbrParticipants;
    }

    @Override
    public String toString() {
        return "SessionEntity{" +
                "id=" + id +
                ", lieu='" + lieu + '\'' +
                ", dateDeb=" + dateDebut +
                ", dateFin=" + dateFin +
                ", organisme=" + organisme +
                ", formateur=" + formateur +
                ", formation=" + formation +
                '}';
    }



}
