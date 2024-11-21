package com.example.gestionformation.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
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
public class Formateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    @Size(min = 5,max = 25)
    private String nom;

    @NotBlank
    @NotNull
    @Size(min = 5,max = 25)
    private String prenom;

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 8, max = 14)
    private String telephone;

    @Enumerated(EnumType.STRING)
    private EnumFormateur type;

    @ManyToOne
    //@Cascade(CascadeType.SAVE_UPDATE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="organisme_id")
    private Organisme organisme;


    @OneToMany(mappedBy="formateur",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Session> sessions;

    public Formateur() { }

    public Formateur(Long id, String nom, String prenom, String email, String telephone, EnumFormateur type, Organisme organisme, Set<Session> sessions) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.type = type;
        this.organisme = organisme;
        this.sessions = sessions;
    }

    public Formateur(Long id, String nom, String prenom, String email, String telephone, EnumFormateur type, Organisme organisme) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.type = type;
        this.organisme = organisme;
    }

    //les getters
    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public EnumFormateur getType() { return type; }
    public Organisme getOrganisme() { return organisme; }
    public Set<Session> getSessions() { return sessions; }

    //les setters
    public void setId(Long id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setType(EnumFormateur type) { this.type = type; }
    public void setOrganisme(Organisme organisme) { this.organisme = organisme; }
    public void setSessions(Set<Session> sessions) { this.sessions = sessions; }

    @Override
    public String toString() {
        return "Formateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", type='" + type + '\'' +
                ", organisme=" + organisme +
                ", sessions=" + sessions +
                '}';
    }

}
