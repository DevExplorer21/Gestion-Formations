package com.example.gestionformation.Entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    @Size(min =5, max=25)
    private String nom;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 25)
    private String prenom;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 14)
    private String telephone;

    @Enumerated(EnumType.STRING)
    private EnumParticipant type;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="profile_id")
    Profile profil;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="country_id")
    Pays pays;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name="participant_session",
            joinColumns = @JoinColumn(name="participant_id"), inverseJoinColumns = @JoinColumn(name="session_id"))
    private Set<Session> sessions;
    /*@ManyToMany(mappedBy = "participants", fetch = FetchType.LAZY)
    private Set<SessionFormation> sessionFormations = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "participant_session",
            joinColumns = @JoinColumn(name = "idSession"),
            inverseJoinColumns = @JoinColumn(name = "idParticipant"))
    private Set<Participant> participants = new HashSet<>();*/

    public Participant() { }

    public Participant(Long id, String nom, String prenom, String email, String telephone, EnumParticipant type, Profile profil, Set<Session> sessions) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.type = type;
        this.profil = profil;
        this.sessions = sessions;
    }

    public Participant(String nom, String prenom, String email, String telephone, EnumParticipant type, Profile profil, Pays pays, Set<Session> sessions) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.type = type;
        this.profil = profil;
        this.pays = pays;
        this.sessions = sessions;
    }

    //les getters
    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public EnumParticipant getType() { return type; }
    public Profile getProfil() { return profil; }
    public Pays getPays() { return pays; }
    public Set<Session> getSessions() { return sessions; }

    //les setters
    public void setId(Long id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setType(EnumParticipant type) { this.type = type; }
    public void setProfil(Profile profil) { this.profil = profil; }
    public void setPays(Pays pays) { this.pays = pays; }
    public void setSessions(Set<Session> sessions) { this.sessions = sessions; }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", firstName='" + nom + '\'' +
                ", lastName='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + telephone + '\'' +
                ", type='" + type + '\'' +
                ", profile=" + profil +
                ", country=" + pays +
                '}';
    }
}



